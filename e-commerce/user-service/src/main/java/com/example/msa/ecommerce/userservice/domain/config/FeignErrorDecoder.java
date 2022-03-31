package com.example.msa.ecommerce.userservice.domain.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404:
                if (methodKey.contains("findOrdersError")) {
                    var errorMessage = methodKey + " - API not found";
                    log.error(errorMessage);
                    log.error(response.reason());
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), errorMessage);
                }
                break;
            default:
                return new Exception(response.reason());
        }

        return null;
    }
}
