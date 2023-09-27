package com.qkrtprjs.happyexercise.controller;

import com.qkrtprjs.happyexercise.dto.PaymentCancelDto;
import com.qkrtprjs.happyexercise.dto.PaymentVerificationDto;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.POST;

import java.io.IOException;

@RestController
public class PaymentApiController {

//    @Value("${iamport.apiKey}")
//    private String apiKey;
//
//    @Value("${iamport.apiSecret}")
//    private String apiSecret;

    private IamportClient iamportClient;



    ///IamportClient 객체 생성
    public PaymentApiController() {

        this.iamportClient = new IamportClient("4485726770034855", "13ir1LNySDUG95vc595hVx7lAHiGJzsINwV8w1LuthWzloDesrHkQvJhr849aK0dA9A8ASsvf1KDtlVd");
    }

    @PostMapping("/payment/verification")
    private IamportResponse<Payment> paymentByImpUid(@RequestBody PaymentVerificationDto paymentVerificationDto) throws IamportResponseException, IOException {
        //paymentByImpUid를 사용하기 위해서는 토큰 발급이 필요하고, 토큰 발급을 하기 위해서는 위에 복사해 두었던 REST API 키 와 REST API secret 가 필요합니다.
        return iamportClient.paymentByImpUid(paymentVerificationDto.getImp_uid());
    }

    @PostMapping("/payment/cancel")
    private IamportResponse<Payment> cancelPaymentByImpUid(@RequestBody PaymentCancelDto paymentCancelDto) throws IamportResponseException, IOException {
        String impUid = paymentCancelDto.getImp_uid();
        return iamportClient.cancelPaymentByImpUid(new CancelData(impUid, true));
    }
}
