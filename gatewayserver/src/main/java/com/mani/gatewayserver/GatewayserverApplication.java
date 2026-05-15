package com.mani.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/mybank/accounts/**")
                        .filters(f -> f.rewritePath("/mybank/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p.path("/mybank/loans/**")
                        .filters(f -> f.rewritePath("/mybank/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOANS"))
                .route(p -> p.path("/mybank/cards/**")
                        .filters(f -> f.rewritePath("/mybank/cards/(?<segment>.*)", "/${segment}"))
                        .uri("lb://CARDS")).build();
    }

}
