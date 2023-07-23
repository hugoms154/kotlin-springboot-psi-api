package com.hugoms154.psiproj.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
  val jwtAuthFilter: JwtAuthenticationFilter,
  val authenticationProvider: AuthenticationProvider
) {
  @Bean
  fun apiFilterChain(http: HttpSecurity): DefaultSecurityFilterChain {
    http.invoke {
      csrf { disable() }
      authorizeRequests {
        authorize("/api/auth/**", permitAll)
        authorize("/api/**", authenticated)
      }
      sessionManagement {
        sessionCreationPolicy = SessionCreationPolicy.STATELESS
      }
    }
    http.authenticationProvider(authenticationProvider)
    http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
    return http.build()
  }

}