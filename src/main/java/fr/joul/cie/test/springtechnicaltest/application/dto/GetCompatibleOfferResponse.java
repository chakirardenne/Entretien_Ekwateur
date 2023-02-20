package fr.joul.cie.test.springtechnicaltest.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetCompatibleOfferResponse {
    private String promoCode;
    private String endDate;
    private Double discountValue;
    private List<CompatibleOfferDto> compatibleOfferDtos;
}
