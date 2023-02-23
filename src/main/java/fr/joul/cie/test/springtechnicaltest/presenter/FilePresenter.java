package fr.joul.cie.test.springtechnicaltest.presenter;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferRequest;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;
import fr.joul.cie.test.springtechnicaltest.application.exception.NoCompatibleOfferException;
import fr.joul.cie.test.springtechnicaltest.application.exception.CodeNotFoundException;
import fr.joul.cie.test.springtechnicaltest.application.service.GetCompatibleOfferService;
import fr.joul.cie.test.springtechnicaltest.domain.ports.in.GetCompatibleOfferUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FilePresenter {
    private final GetCompatibleOfferUseCase service;

    public void testPromoCode(String code) {
        GetCompatibleOfferRequest request = new GetCompatibleOfferRequest();
        request.setCode(code);
        GetCompatibleOfferResponse response;

        try {
            service.setOfferValidationStrategy("DEFAULT");
            response = service.getOffers(request);
            new ObjectMapper().writeValue(new File("compatible_offers.json"), response);
            System.out.println("File created at root");
        }
        catch (CodeNotFoundException | NoCompatibleOfferException | IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
