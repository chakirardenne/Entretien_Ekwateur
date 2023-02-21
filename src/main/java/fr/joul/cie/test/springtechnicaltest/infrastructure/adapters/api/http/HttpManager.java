package fr.joul.cie.test.springtechnicaltest.infrastructure.adapters.api.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpManager {
    private HttpManager() {}

    private static HttpResponse<String> sendRequest(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static URI createUri(String apiUri) {
        return URI.create(apiUri);
    }

    private static HttpClient createHttpClient() {
        return HttpClient.newHttpClient();
    }

    private static HttpRequest createHttpRequest(URI uri) {
        return HttpRequest
                .newBuilder()
                .uri(uri)
                .header("accept", "application/json")
                .GET()
                .build();
    }

    public static HttpResponse<String> makeHttpCall(String apiUri) throws IOException, InterruptedException {
        URI uri = createUri(apiUri);
        HttpClient client = HttpManager.createHttpClient();
        HttpRequest request = HttpManager.createHttpRequest(uri);
        return HttpManager.sendRequest(client, request);
    }
}
