package com.project.yjshop.service.board;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.board.BoardRepository;
import com.project.yjshop.domain.image.ImageRepository;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.service.image.ImageServiceImpl;
import com.project.yjshop.service.image.S3Service;
import com.project.yjshop.web.payload.request.board.ProductRequest;
import com.project.yjshop.web.payload.response.board.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final ImageServiceImpl imageService;
    private final S3Service s3Service;

    @Override
    @Transactional
    public ProductResponse posting(ProductRequest productRequest,
                                   BindingResult bindingResult,
                                   PrincipalDetails principalDetails) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomException(ErrorCode.POSTING_FAILED, errorMap);
        } else {

            Board saveBoard = boardRepository.save(Board.builder()
                    .title(productRequest.getTitle())
                    .titleImage(imageRepository.findByImagePath(imageService.imageUpload(s3Service.upload(productRequest.getTitleImage()))).get())
                    .price(productRequest.getPrice())
                    .count(productRequest.getCount())
                    .user(principalDetails.getUser())
                    .totalRevenue(0L)
                    .build());

            return ProductResponse.builder()
                    .message("Post success")
                    .product(ProductResponse.Product.builder()
                            .boardId(saveBoard.getId())
                            .title(saveBoard.getTitle())
                            .titleImage(saveBoard.getTitleImage().getImageFullPath())
                            .price(saveBoard.getPrice())
                            .count(saveBoard.getCount())
                            .build())
                    .build();
        }
    }

    @Override
    @Transactional
    public ProductResponse deleting(Long boardId, PrincipalDetails principalDetails) {

        Board delBoard = boardRepository.findById(boardId).orElseThrow(()->new CustomException(ErrorCode.BOARD_NOT_FOUND));

        if(principalDetails.getUser().getId() != delBoard.getUser().getId()) {
            throw new CustomException(ErrorCode.USER_NOT_MATCH);
        }

        s3Service.delete(delBoard.getTitleImage().getImagePath());
        boardRepository.delete(delBoard);

        return ProductResponse.builder()
                .message("Delete success")
                .product(ProductResponse.Product.builder()
                        .boardId(delBoard.getId())
                        .title(delBoard.getTitle())
                        .titleImage(delBoard.getTitleImage().getImageFullPath())
                        .price(delBoard.getPrice())
                        .count(delBoard.getCount())
                        .build())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
