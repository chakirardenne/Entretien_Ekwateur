package fr.joul.cie.test.springtechnicaltest.application.service;

import fr.joul.cie.test.springtechnicaltest.application.converter.MapperTool;
import fr.joul.cie.test.springtechnicaltest.application.dto.CompatibleOfferDto;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferRequest;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;
import fr.joul.cie.test.springtechnicaltest.application.exception.CodeNotFoundException;
import fr.joul.cie.test.springtechnicaltest.application.exception.NoCompatibleOfferException;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.domain.entities.OfferType;
import fr.joul.cie.test.springtechnicaltest.domain.ports.out.EkwateurApiPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetCompatibleOfferServiceTest {
    @Mock
    EkwateurApiPort apiPort;
    @InjectMocks
    GetCompatibleOfferService service;
    @Spy
    MapperTool mapperTool = Mappers.getMapper(MapperTool.class);
    static List<Offer> mockOffers;
    static List<Code> mockCodes;

    @BeforeAll
    static void setUp() {
        mockOffers = List.of(
                new Offer(OfferType.GAS, "test1", "une offre test"),
                new Offer(OfferType.WOOD, "test2", "une offre test 2"),
                new Offer(OfferType.ELECTRICITY, "test3", "une offre test 3")
        );
        mockCodes = List.of(
                new Code("code_test_1", 2.0, "2019-10-04"),
                new Code("code_test_2", 1.5, "2023-03-05"),
                new Code("code_test_3", 3.75, "2023-09-05"),
                new Code("code_test_4", 1.25, "2027-12-05"),
                new Code("code_test_5", 6.32, "2018-09-05"),
                new Code("code_test_6", 8.63, "2004-09-05")
        );
        mockOffers.get(0).getValidPromoCodeList().add(mockCodes.get(0).code());
        mockOffers.get(0).getValidPromoCodeList().add(mockCodes.get(1).code());
        mockOffers.get(0).getValidPromoCodeList().add(mockCodes.get(2).code());

        mockOffers.get(1).getValidPromoCodeList().add(mockCodes.get(2).code());
        mockOffers.get(1).getValidPromoCodeList().add(mockCodes.get(3).code());
        mockOffers.get(1).getValidPromoCodeList().add(mockCodes.get(4).code());

        mockOffers.get(2).getValidPromoCodeList().add(mockCodes.get(2).code());
    }

    public static Stream<Arguments> providerForShouldThrowNoCompatibleOfferException() {
        GetCompatibleOfferRequest code = new GetCompatibleOfferRequest();
        GetCompatibleOfferRequest code_1 = new GetCompatibleOfferRequest();
        code.setCode(mockCodes.get(0).code());
        code_1.setCode(mockCodes.get(5).code());
        return Stream.of(
                Arguments.of(code),
                Arguments.of(code_1)
        );
    }

    public static Stream<Arguments> providerForShouldThrowCodeNotFoundException() {
        GetCompatibleOfferRequest code = new GetCompatibleOfferRequest();
        GetCompatibleOfferRequest code_1 = new GetCompatibleOfferRequest();
        GetCompatibleOfferRequest code_2 = new GetCompatibleOfferRequest();
        GetCompatibleOfferRequest code_3 = new GetCompatibleOfferRequest();
        code.setCode("");
        code_1.setCode("10");
        code_2.setCode("test");
        code_2.setCode(null);

        return Stream.of(
                Arguments.of(code),
                Arguments.of(code_1),
                Arguments.of(code_2),
                Arguments.of(code_3)
        );
    }

    @Test
    void shouldReturnCompatibleOffersForValidCode() {
        GetCompatibleOfferResponse expected = new GetCompatibleOfferResponse();
        expected.setPromoCode(mockCodes.get(2).code());
        expected.setDiscountValue(mockCodes.get(2).discountValue());
        expected.setEndDate(mockCodes.get(2).endDate());
        List<CompatibleOfferDto> compatibleOfferDtos = List.of(
                new CompatibleOfferDto(),
                new CompatibleOfferDto(),
                new CompatibleOfferDto()
        );
        compatibleOfferDtos.get(0).setName(
                mockOffers.get(0).getOfferName()
        );
        compatibleOfferDtos.get(0).setType(
                mockOffers.get(0).getOfferType().name()
        );
        compatibleOfferDtos.get(1).setName(
                mockOffers.get(1).getOfferName()
        );
        compatibleOfferDtos.get(1).setType(
                mockOffers.get(1).getOfferType().name()
        );
        compatibleOfferDtos.get(2).setName(
                mockOffers.get(2).getOfferName()
        );
        compatibleOfferDtos.get(2).setType(
                mockOffers.get(2).getOfferType().name()
        );
        expected.setCompatibleOfferDtos(compatibleOfferDtos);

        GetCompatibleOfferRequest request = new GetCompatibleOfferRequest();
        request.setCode("code_test_3");
        when(apiPort.getAllOffers()).thenReturn(mockOffers);
        when(apiPort.getAllCodes()).thenReturn(mockCodes);
        GetCompatibleOfferResponse response = service.getOffers(request);

        assertEquals(expected, response);
    }

    @ParameterizedTest
    @MethodSource("providerForShouldThrowNoCompatibleOfferException")
    void shouldThrowNoCompatibleOfferException(GetCompatibleOfferRequest request) {
        when(apiPort.getAllOffers()).thenReturn(mockOffers);
        when(apiPort.getAllCodes()).thenReturn(mockCodes);

        Assertions.assertThrows(NoCompatibleOfferException.class , () -> {
            GetCompatibleOfferResponse response = service.getOffers(request);
        });
    }

    @ParameterizedTest
    @MethodSource("providerForShouldThrowCodeNotFoundException")
    void shouldThrowCodeNotFoundException(GetCompatibleOfferRequest request) {
        when(apiPort.getAllOffers()).thenReturn(mockOffers);
        when(apiPort.getAllCodes()).thenReturn(mockCodes);

        Assertions.assertThrows(CodeNotFoundException.class , () -> {
            GetCompatibleOfferResponse response = service.getOffers(request);
        });
    }
}