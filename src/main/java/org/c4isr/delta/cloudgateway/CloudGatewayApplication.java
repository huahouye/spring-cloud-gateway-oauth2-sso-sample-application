package org.c4isr.delta.cloudgateway;

import org.c4isr.delta.cloudgateway.jwt.JwtOAuth2AuthenticationTokenConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
public class CloudGatewayApplication {

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    // @formatter:off
    http.authorizeExchange()
      .anyExchange().authenticated().and()
      .oauth2Login().and()
      .oauth2ResourceServer().jwt()
      .jwtAuthenticationConverter(new JwtOAuth2AuthenticationTokenConverter());
    // @formatter:on
    return http.build();
  }

  public static void main(String[] args) {
    SpringApplication.run(CloudGatewayApplication.class, args);
  }
}
