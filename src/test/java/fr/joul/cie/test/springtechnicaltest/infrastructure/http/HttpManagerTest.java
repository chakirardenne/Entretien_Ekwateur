package fr.joul.cie.test.springtechnicaltest.infrastructure.http;

import fr.joul.cie.test.springtechnicaltest.infrastructure.adapters.api.http.ApiURL;
import fr.joul.cie.test.springtechnicaltest.infrastructure.adapters.api.http.HttpManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HttpManagerTest {
    @ParameterizedTest
    @MethodSource("providerForTestUrl")
    void givenURL_whenOfferListIsRetrieved_then200IsReceived(String uriToTest)
            throws IOException, InterruptedException {
        // When
        HttpResponse<String>  httpResponse = HttpManager.makeHttpCall(uriToTest);
        // Then
        assertEquals(HttpURLConnection.HTTP_OK, httpResponse.statusCode());
    }

    public static Stream<Arguments> providerForTestUrl() {
        return Stream.of(
                Arguments.of(ApiURL.OFFERS_URL),
                Arguments.of(ApiURL.CODES_URL)
        );
    }
}