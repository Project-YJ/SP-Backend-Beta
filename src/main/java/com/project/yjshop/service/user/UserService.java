package com.project.yjshop.service.user;

import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.web.payload.request.user.PurchaseRequest;
import com.project.yjshop.web.payload.response.user.PurchaseResponse;

public interface UserService {
    PurchaseResponse purchase(PurchaseRequest purchaseRequest, PrincipalDetails principalDetails);
}
