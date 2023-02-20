package fr.joul.cie.test.springtechnicaltest.application.service;

import fr.joul.cie.test.springtechnicaltest.application.converter.MapperTool;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferRequest;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;
import fr.joul.cie.test.springtechnicaltest.application.exception.NoCompatibleOfferException;
import fr.joul.cie.test.springtechnicaltest.application.exception.CodeNotFound;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.domain.exception.InvalidValidatorException;
import fr.joul.cie.test.springtechnicaltest.domain.ports.in.GetCompatibleOfferUseCase;
import fr.joul.cie.test.springtechnicaltest.domain.ports.out.EkwateurApiPort;
import fr.joul.cie.test.springtechnicaltest.domain.validators.factories.ValidatorFactory;
import fr.joul.cie.test.springtechnicaltest.domain.validators.factories.ValidatorFactoryImpl;
import fr.joul.cie.test.springtechnicaltest.domain.validators.impl.DefaultCodeValidator;
import fr.joul.cie.test.springtechnicaltest.domain.validators.impl.ValidatorType;
import fr.joul.cie.test.springtechnicaltest.domain.validators.intf.CodeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCompatibleOfferService implements GetCompatibleOfferUseCase {
    private final EkwateurApiPort apiPort;
    private CodeValidator codeValidator = new DefaultCodeValidator();
    private final MapperTool mapperTool;

    @Override
    public GetCompatibleOfferResponse getOffers(GetCompatibleOfferRequest getCompatibleOfferRequest) {
        List<Offer> allOffers = apiPort.getAllOffers();
        Code promoCode = getPromoCode(getCompatibleOfferRequest.getCode());
        List<Offer> compatibleOffers = getCompatibleOffers(allOffers, promoCode);
        if(compatibleOffers.isEmpty())
            throw new NoCompatibleOfferException("Invalid code or no compatible offers found for given promo code");
        GetCompatibleOfferResponse response = mapperTool.codeToGetCompatibleOfferResponse(promoCode);
        response.setCompatibleOfferDtos(compatibleOffers.stream()
                .map(mapperTool::offerToCompatibleOfferDto)
                .toList());
        return response;
    }

    private Code getPromoCode(String requestCode) {
        return apiPort.getAllCodes().stream().filter(c -> c.code().equals(requestCode))
                .findFirst()
                .orElseThrow(() -> new CodeNotFound("Not found code"));
    }

    private List<Offer> getCompatibleOffers(List<Offer> allOffers, Code promoCode) {
        return allOffers.stream().filter(offer -> codeValidator.validate(offer.getValidPromoCodeList(), promoCode)).toList();
    }

    @Override
    public void setOfferValidationStrategy(String validator) {
        ValidatorFactory factory = new ValidatorFactoryImpl();
        try {
            codeValidator = factory.createValidator(ValidatorType.valueOf(validator));
        } catch (InvalidValidatorException e) {
            System.out.println(e.getMessage());
        }
    }
}
