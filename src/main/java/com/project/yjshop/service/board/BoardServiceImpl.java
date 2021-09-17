package com.project.yjshop.service.board;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.board.BoardRepository;
import com.project.yjshop.domain.image.ImageRepository;
import com.project.yjshop.service.image.ImageServiceImpl;
import com.project.yjshop.service.image.S3Service;
import com.project.yjshop.web.payload.request.board.ProductRequest;
import com.project.yjshop.web.payload.response.board.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final ImageServiceImpl imageService;
    private final S3Service s3Service;

    @Override
    public ProductResponse posting(ProductRequest productRequest) throws IOException {

        Board saveBoard = boardRepository.save(Board.builder()
                .title(productRequest.getTitle())
                .titleImage(imageRepository.findByImagePath(imageService.imageUpload(s3Service.upload(productRequest.getTitleImage()))).get())
                .price(productRequest.getPrice())
                .build());

        return ProductResponse.builder()
                .message("Post success")
                .product(ProductResponse.Product.builder()
                        .boardId(saveBoard.getId())
                        .title(saveBoard.getTitle())
                        .titleImage(saveBoard.getTitleImage().getImageFullPath())
                        .price(saveBoard.getPrice())
                        .build())
                .build();
    }

    @Override
    public ProductResponse deleting(String boardId) {
        return null;
    }
}
