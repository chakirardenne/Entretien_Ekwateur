package fr.joul.cie.test.springtechnicaltest.application.service;

import fr.joul.cie.test.springtechnicaltest.application.converter.MapperTool;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferRequest;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;
import fr.joul.cie.test.springtechnicaltest.application.exception.NoCompatibleOfferException;
import fr.joul.cie.test.springtechnicaltest.application.exception.NotValidCodeException;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.domain.ports.in.GetCompatibleOfferUseCase;
import fr.joul.cie.test.springtechnicaltest.domain.ports.out.CodePort;
import fr.joul.cie.test.springtechnicaltest.domain.ports.out.OfferPort;
import fr.joul.cie.test.springtechnicaltest.domain.validators.impl.DefaultCodeValidator;
import fr.joul.cie.test.springtechnicaltest.domain.validators.intf.CodeValidator;

import java.util.List;

public class GetCompatibleOfferService implements GetCompatibleOfferUseCase {
    private final OfferPort offerPort;
    private final CodePort codePort;
    private CodeValidator codeValidator;
    private final MapperTool mapperTool;

    public GetCompatibleOfferService(OfferPort offerPort, CodePort codePort, MapperTool mapperTool) {
        this.offerPort = offerPort;
        this.codePort = codePort;
        this.mapperTool = mapperTool;
        this.codeValidator = new DefaultCodeValidator();
    }

    @Override
    public GetCompatibleOfferResponse getOffers(GetCompatibleOfferRequest getCompatibleOfferRequest) {
        List<Offer> allOffers = offerPort.getAll();
        Code promoCode = getPromoCode(getCompatibleOfferRequest.getCode());
        List<Offer> compatibleOffers = getCompatibleOffers(allOffers, promoCode);
        if(compatibleOffers.isEmpty())
            throw new NoCompatibleOfferException("No compatible offers found for given promo code");
        GetCompatibleOfferResponse response = mapperTool.codeToGetCompatibleOfferResponse(promoCode);
        response.setCompatibleOfferDtos(compatibleOffers.stream()
                .map(mapperTool::offerToCompatibleOfferDto)
                .toList());
        return response;
    }

    private Code getPromoCode(String requestCode) {
        return codePort.getAll().stream().filter(c -> c.code().equals(requestCode))
                .findFirst()
                .orElseThrow(() -> new NotValidCodeException("Not valid code"));
    }

    private List<Offer> getCompatibleOffers(List<Offer> allOffers, Code promoCode) {
        return allOffers.stream().filter(offer -> codeValidator.validate(offer.getValidPromoCodeList(), promoCode)).toList();
    }

    @Override
    public void setOfferValidationStrategy(CodeValidator validator) {
        codeValidator = validator;
    }
}
