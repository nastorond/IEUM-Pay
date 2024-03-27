package com.ieum.common.dto.feign.pay.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardOcrResponseDTO {
    String cardNumber;
    String validThru;
}
