package com.hugoms154.psiproj.configuration

import com.hugoms154.psiproj.module.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig(val userRepository: UserRepository) {
  @Bean
  fun userDetailsService(): UserDetailsService {
    return UserDetailsService { username ->
      userRepository.findByEmail(username) ?: throw UnsupportedOperationException(
        "User not found"
      )
    }
  }

  @Bean
  fun authenticationProvider(): AuthenticationProvider {
    val authProvider = DaoAuthenticationProvider()
    authProvider.setUserDetailsService(this.userDetailsService())
    authProvider.setPasswordEncoder(this.passwordEncoder())
    return authProvider
  }

  @Bean
  fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
    return config.authenticationManager
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

}