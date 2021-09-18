package com.project.yjshop.web.api;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.service.board.BoardServiceImpl;
import com.project.yjshop.service.image.ImageService;
import com.project.yjshop.service.image.ImageServiceImpl;
import com.project.yjshop.web.payload.request.board.ProductRequest;
import com.project.yjshop.web.payload.response.board.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {

    private final BoardServiceImpl boardService;

    @PostMapping("/")
    public ProductResponse postProduct(@ModelAttribute @Valid ProductRequest product,
                                       BindingResult bindingResult,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        return boardService.posting(product, bindingResult, principalDetails);
    }

    @DeleteMapping("/{boardId}")
    public ProductResponse delProduct(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return boardService.deleting(boardId, principalDetails);
    }
}
