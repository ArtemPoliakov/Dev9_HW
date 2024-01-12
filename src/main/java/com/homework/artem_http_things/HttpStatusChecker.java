package com.homework.artem_http_things;

import com.homework.exceptions.ImageNotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HttpStatusChecker {
    private static final String CAT_URL_TEMPLATE = "https://http.cat/%d.jpg";
    public String getStatusImage(int code) throws ImageNotFoundException {
        String url = String.format(CAT_URL_TEMPLATE, code);
        try{
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .timeout(Duration.of(5, ChronoUnit.SECONDS))
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .connectTimeout(Duration.of(5, ChronoUnit.SECONDS))
                    .build();
            HttpResponse<Void> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.discarding());
            if(response.statusCode() == 404){
                throw new ImageNotFoundException();
            }
        } catch(URISyntaxException | IOException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return url;
    }
}
