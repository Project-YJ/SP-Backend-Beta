package com.project.yjshop.service.board;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.security.auth.AuthDetails;
import com.project.yjshop.web.payload.request.board.BoardProductRequest;
import com.project.yjshop.web.payload.response.board.BoardProductResponse;
import com.project.yjshop.web.payload.response.board.BoardCategoryResponse;
import com.project.yjshop.web.payload.response.board.CategoryResponse;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    BoardProductResponse posting(BoardProductRequest boardProductRequest,
                                 BindingResult bindingResult,
                                 AuthDetails authDetails) throws IOException;
    BoardProductResponse deleting(Integer boardId, AuthDetails authDetails);
    List<Board> findAll();
    CategoryResponse sortedCategoryList();
    BoardCategoryResponse categoryList(Integer categoryId);

}
