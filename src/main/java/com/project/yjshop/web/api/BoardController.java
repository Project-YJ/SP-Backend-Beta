package com.project.yjshop.web.api;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.service.board.BoardServiceImpl;
import com.project.yjshop.service.image.ImageService;
import com.project.yjshop.service.image.ImageServiceImpl;
import com.project.yjshop.web.payload.request.board.ProductRequest;
import com.project.yjshop.web.payload.response.board.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {

    private final BoardServiceImpl boardService;

    @PostMapping("/")
    public ProductResponse postProduct(@ModelAttribute ProductRequest product) throws IOException {
        return boardService.posting(product);
    }

    @DeleteMapping("/{boardId}")
    public String delProduct(@PathVariable int boardId) {
        return boardService
    }
}
