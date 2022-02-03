package net.intuit.assignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

@Configuration
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !(response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        LOGGER.error("HttpStatus Code = {}, StatusText = {}", response.getStatusCode() ,response.getStatusText());
    }
}
