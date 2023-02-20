package fr.joul.cie.test.springtechnicaltest.application.converter;

import fr.joul.cie.test.springtechnicaltest.application.dto.CompatibleOfferDto;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

@Mapper
public interface MapperTool {

    @ObjectFactory
    default CompatibleOfferDto create(Offer offer) {
        return new CompatibleOfferDto();
    }
    @Mapping(target = "type", source = "offerType")
    @Mapping(target = "name", source = "offerName")
    CompatibleOfferDto offerToCompatibleOfferDto(Offer offer);
    @Mapping(target = "compatibleOfferDtos", ignore = true)
    @Mapping(target = "promoCode", source = "code")
    GetCompatibleOfferResponse codeToGetCompatibleOfferResponse(Code code);
}
