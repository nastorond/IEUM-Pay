package com.ieum.common.feign;

import com.ieum.common.dto.feign.funding.request.FundingDonationRequestDTO;
import com.ieum.common.dto.feign.funding.request.FundingLinkRequestDTO;
import com.ieum.common.dto.feign.funding.response.AutoFundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingDetailResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingDonationResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingInfoResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingSummaryResponseDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "donation", url = "${gateway.donation}")
public interface FundingFeignClient {

    // 펀딩 상세 조회
    @GetMapping(value = "/funding/{fundingId}/{memberId}/detail")
    FundingDetailResponseDTO getFundingDetail(@PathVariable("fundingId") Long fundingId,
        @PathVariable("memberId") Long memberId);

    // 펀딩 진행중 목록 조회
    @GetMapping(value = "/funding/list/ongoing")
    List<FundingSummaryResponseDTO> getFundingOngoingList();

    // 펀딩 완료 목록 조회
    @GetMapping(value = "/funding/list/complete")
    List<FundingSummaryResponseDTO> getFundingCompleteList();

    // 펀딩 연동
    @PostMapping(value = "/funding/linkup")
    Boolean linkup(@RequestBody FundingLinkRequestDTO request);

    // 펀딩 연동 해제
    @PostMapping(value = "/funding/unlink")
    Boolean unlink(@RequestBody FundingLinkRequestDTO request);

    // 직접 기부 결제 정보 요청
    @GetMapping(value = "/funding/info/directly/{fundingId}")
    FundingInfoResponseDTO getDirectlyFundingInfo(@PathVariable String fundingId);

    // 자동 기부 결제 정보 요청
    @GetMapping(value = "/funding/info/directly/{memberId}")
    FundingInfoResponseDTO getAutoFundingInfo(@PathVariable String memberId);

    // 직접 기부
    @PostMapping(value = "/funding/auto")
    FundingDonationResponseDTO donationDirectly(@RequestBody FundingDonationRequestDTO request);

    // 자동 기부
    @PostMapping(value = "/funding/auto")
    AutoFundingResultResponseDTO donationAuto(@RequestBody FundingDonationRequestDTO request);

    @GetMapping(value = "/funding/result/{fundingId}")
    FundingResultResponseDTO getPaymentResult(@PathVariable Long fundingId);
}