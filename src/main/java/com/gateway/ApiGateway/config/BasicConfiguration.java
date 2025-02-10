package com.gateway.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class BasicConfiguration {

    //Route locator can be configured via yml file also using spring.cloud.gateway.routes config
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("USER-SERVICE",r -> r.path("/users/**").uri("lb://USERSERVICE"))
                .route("HOTEL-SERVICE",r->r.path("/hotels/**").uri("lb://HOTELSERVICE"))
                .route("RATING-SERVICE",r->r.path("/ratings/**").uri("lb://RATINGSERVICE"))
                .route("TESTING-SERVICE",r->r.path("/test/**")
                        .filters(f -> f.addRequestHeader("custom-header","custom-value")
                                .modifyRequestBody(String.class,String.class,(exchange,original)-> {
                                    String modifiedBody = original+" has been modified";
                                    return Mono.just(modifiedBody);
                                }))
                        .uri("lb://TESTSERVICE"))
                .build();
    }

//    @Bean
//    public SecurityFilterChain getFilter(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(request ->
//                        request.anyRequest().authenticated())
//                .csrf(customizer -> customizer.disable())
//                .oauth2Login(customizer -> customizer.)
//                .build();
//    }

    @Bean
    public SecurityWebFilterChain getWebFilterChain(ServerHttpSecurity http){
        return http.csrf(customizer -> customizer.disable())
                .authorizeExchange(request -> request.anyExchange().authenticated())
                .oauth2ResourceServer(customizer -> customizer.jwt(Customizer.withDefaults()))
                .oauth2Client(Customizer.withDefaults())
                .build();
    }
}
