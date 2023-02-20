package fr.joul.cie.test.springtechnicaltest.presenter;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferRequest;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;
import fr.joul.cie.test.springtechnicaltest.application.exception.NoCompatibleOfferException;
import fr.joul.cie.test.springtechnicaltest.application.exception.CodeNotFound;
import fr.joul.cie.test.springtechnicaltest.application.service.GetCompatibleOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FilePresenter {
    private final GetCompatibleOfferService service;

    public void testPromoCode(String code) {
        GetCompatibleOfferRequest request = new GetCompatibleOfferRequest();
        request.setCode(code);
        GetCompatibleOfferResponse response;

        try {
            service.setOfferValidationStrategy("DEFAULT");
            response = service.getOffers(request);
            new ObjectMapper().writeValue(new File("test.json"), response);
        }
        catch (CodeNotFound | NoCompatibleOfferException | IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
