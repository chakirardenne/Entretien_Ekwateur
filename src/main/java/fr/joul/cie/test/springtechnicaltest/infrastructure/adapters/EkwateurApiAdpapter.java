package fr.joul.cie.test.springtechnicaltest.infrastructure.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.joul.cie.test.springtechnicaltest.application.converter.MapperTool;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.domain.ports.out.EkwateurApiPort;
import fr.joul.cie.test.springtechnicaltest.infrastructure.http.ApiURL;
import fr.joul.cie.test.springtechnicaltest.infrastructure.http.HttpManager;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
public class EkwateurApiAdpapter implements EkwateurApiPort {
    private final MapperTool mapperTool;

    @Override
    public List<Code> getAllCodes() {
        List<Code> codes = Collections.emptyList();
        try {
            codes = new ObjectMapper().readValue(HttpManager.makeHttpCall(ApiURL.CODE_URL).body(), new TypeReference<List<Code>>() {});
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return codes;
    }

    @Override
    public List<Offer> getAllOffers() {
        List<Offer> offers = Collections.emptyList();
        try {
            offers = new ObjectMapper().readValue(HttpManager.makeHttpCall(ApiURL.CODE_URL).body(), new TypeReference<List<Offer>>() {});
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return offers;
    }
}