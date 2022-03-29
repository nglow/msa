package com.example.msa.ecommerce.gateway.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment environment;

    public AuthorizationHeaderFilter(Environment environment) {
        super(Config.class);
        this.environment = environment;
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom pre filter
        return ((exchange, chain) -> {
            var request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);

            var authorizationHeaderValue = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            var jwt = authorizationHeaderValue.replace("Bearer ", "");
            if (!isJwtValid(jwt))
                return onError(exchange, "Jwt token is not valid", HttpStatus.UNAUTHORIZED);

            return chain.filter(exchange);
        });
    }

    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;
        String subject = null;

        try {
            subject = Jwts.parser().setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();

        } catch (Exception exception) {
            returnValue = false;
        }

        if (subject == null || subject.isEmpty()) {
            returnValue = false;
        }

        return returnValue;
    }

    // Mono, Flux -> Spring WebFlux
    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage, HttpStatus httpStatus) {
        var response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(errorMessage);

        return response.setComplete();
    }

    public static class Config {
    }

}
