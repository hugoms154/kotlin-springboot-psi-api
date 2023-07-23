package com.hugoms154.psiproj.module.authentication

import jakarta.validation.constraints.NotEmpty

data class AuthenticationDto(
  @field:NotEmpty
  val username: String,

  @field:NotEmpty
  val password: String
)

data class AuthenticationResponseDto(
  val token: String
)