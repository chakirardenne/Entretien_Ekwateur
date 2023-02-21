package fr.joul.cie.test.springtechnicaltest.infrastructure.adapters;

import fr.joul.cie.test.springtechnicaltest.application.converter.MapperTool;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.infrastructure.adapters.api.EkwateurApiAdpapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EkwateurApiAdpapterTest {
    @InjectMocks
    EkwateurApiAdpapter apiAdpapter;
    @Mock
    MapperTool mapperTool = Mappers.getMapper(MapperTool.class);

    @Test
    void whenOfferListIsRetrieved_thenRetrievedResourceIsCorrect()
            throws IOException, InterruptedException {
        // When
        List<Offer> offers = apiAdpapter.getAllOffers();
        // Then
        assertEquals("EKWAG2000", offers.get(0).getOfferName());
    }

    @Test
    void whenCodeListIsRetrieved_thenRetrievedResourceIsCorrect()
            throws IOException, InterruptedException {
        // When
        List<Code> codes = apiAdpapter.getAllCodes();
        // Then
        assertEquals("EKWA_WELCOME", codes.get(0).code());
    }
}