package com.project.yjshop.service.board;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.board.BoardRepository;
import com.project.yjshop.domain.board.category.Category;
import com.project.yjshop.domain.board.category.CategoryRepository;
import com.project.yjshop.domain.image.ImageRepository;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.service.image.ImageServiceImpl;
import com.project.yjshop.service.image.S3Service;
import com.project.yjshop.web.payload.request.board.BoardProductRequest;
import com.project.yjshop.web.payload.response.board.BoardProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final ImageServiceImpl imageService;
    private final S3Service s3Service;

    @Override
    @Transactional
    public BoardProductResponse posting(BoardProductRequest boardProductRequest,
                                        BindingResult bindingResult,
                                        PrincipalDetails principalDetails) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw new CustomException(ErrorCode.POSTING_FAILED, errorMap);
        } else {
            String categoryName = boardProductRequest.getCategory();

            Board saveBoard = boardRepository.save(Board.builder()
                    .title(boardProductRequest.getTitle())
                    .titleImage(imageRepository.findByImagePath(imageService
                            .imageUpload(s3Service.upload(boardProductRequest.getTitleImage()))).get())
                    .price(boardProductRequest.getPrice())
                    .count(boardProductRequest.getCount())
                    .user(principalDetails.getUser())
                    .totalRevenue(0L)
                    .category(categoryRepository.existsByName(categoryName) ? categoryRepository.save(
                            Category.builder()
                                    .name(categoryName)
                                    .count(0)
                                    .build()) : categoryRepository.findByName(categoryName)
                            .map(Category::upCount).get())
                    .build());

            return BoardProductResponse.builder()
                    .message("Post success")
                    .product(BoardProductResponse.Product.builder()
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
    public BoardProductResponse deleting(Long boardId, PrincipalDetails principalDetails) {

        Board delBoard = boardRepository.findById(boardId).orElseThrow(()->new CustomException(ErrorCode.BOARD_NOT_FOUND));

        if(principalDetails.getUser().getId() != delBoard.getUser().getId()) {
            throw new CustomException(ErrorCode.USER_NOT_MATCH);
        }

        s3Service.delete(delBoard.getTitleImage().getImagePath());
        boardRepository.delete(delBoard);

        return BoardProductResponse.builder()
                .message("Delete success")
                .product(BoardProductResponse.Product.builder()
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
