package com.qkrtprjs.happyexercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PaymentVerificationDto {

    private String imp_uid;
    private String merchant_uid;
    private String amount;

    @Builder
    public PaymentVerificationDto(String imp_uid) {
        this.imp_uid = imp_uid;
    }
}
