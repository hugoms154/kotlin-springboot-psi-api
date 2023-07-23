package com.hugoms154.psiproj.module.authentication

import com.hugoms154.psiproj.configuration.JwtService
import com.hugoms154.psiproj.module.user.CreateUserDto
import com.hugoms154.psiproj.module.user.UserModel
import com.hugoms154.psiproj.module.user.UserRepository
import com.hugoms154.psiproj.module.user.toModel
import jakarta.validation.Valid
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
  private val authenticationManager: AuthenticationManager,
  private val jwtService: JwtService,
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder
) {

  @PostMapping("/register")
  fun create(@RequestBody @Valid user: CreateUserDto): UserModel =
    this.userRepository.save(user.toModel(passwordEncoder.encode(user.password)))

  @PostMapping("/login")
  fun login(@RequestBody @Valid credentials: AuthenticationDto): AuthenticationResponseDto {
    val (username, password) = credentials
    authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))

    val user = userRepository.findByEmail(username) ?: throw UnsupportedOperationException(
      "User not found"
    )
    val token = jwtService.generateToken(user)

    return AuthenticationResponseDto(token)
  }
}