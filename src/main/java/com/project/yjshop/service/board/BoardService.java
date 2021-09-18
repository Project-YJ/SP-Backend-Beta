package com.project.yjshop.service.board;

import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.web.payload.request.board.ProductRequest;
import com.project.yjshop.web.payload.response.board.ProductResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BoardService {
    ProductResponse posting(ProductRequest productRequest,
                            BindingResult bindingResult,
                            PrincipalDetails principalDetails) throws IOException;
    ProductResponse deleting(Long boardId, PrincipalDetails principalDetails);
}
