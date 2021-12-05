package com.project.yjshop.web.api;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.security.auth.AuthDetails;
import com.project.yjshop.service.board.BoardService;
import com.project.yjshop.web.payload.request.board.BoardCategoryRequest;
import com.project.yjshop.web.payload.request.board.BoardProductRequest;
import com.project.yjshop.web.payload.response.board.BoardProductResponse;
import com.project.yjshop.web.payload.response.board.BoardCategoryResponse;
import com.project.yjshop.web.payload.response.board.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/product/")
    public BoardProductResponse postProduct(@ModelAttribute @Valid BoardProductRequest product,
                                            @AuthenticationPrincipal AuthDetails authDetails) throws IOException {
        System.out.println("dkslanjspe");
        return boardService.posting(product, authDetails);
    }

    @DeleteMapping("/product/{boardId}")
    public BoardProductResponse delProduct(@PathVariable Integer boardId, @AuthenticationPrincipal AuthDetails authDetails) {
        return boardService.deleting(boardId, authDetails);
    }

    @GetMapping("/product/")
    public List<Board> allProduct() {
        return boardService.findAll();
    }

    @GetMapping("/category/product")
    public BoardCategoryResponse categoryList(@RequestBody BoardCategoryRequest boardCategoryRequest) {
        return boardService.categoryList(boardCategoryRequest.getCategoryId());
    }

    @GetMapping("/category/")
    public CategoryResponse sortedCategoryList() {
        return boardService.sortedCategoryList();
    }
}
