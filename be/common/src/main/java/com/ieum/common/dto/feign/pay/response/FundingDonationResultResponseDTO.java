package com.ieum.common.dto.feign.pay.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundingDonationResultResponseDTO {
    Long fundingId;
    int donationAmount;
}
