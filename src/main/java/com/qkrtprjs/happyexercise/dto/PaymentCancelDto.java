package com.qkrtprjs.happyexercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentCancelDto {
    private String imp_uid;
    private String reason;
    private int checksum;

    @Builder
    public PaymentCancelDto(String imp_uid, String reason, int checksum) {
        this.imp_uid = imp_uid;
        this.reason = reason;
        this.checksum = checksum;
    }
}
