package com.hugoms154.psiproj.module.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class CreateUserDto(
  @field:NotEmpty
  var name: String,

  @field:NotEmpty
  var surname: String,

  @field:NotEmpty
  @field:Size(min = 6, max = 256)
  var password: String,

  @field:NotEmpty
  @field:Email
  var email: String,
)

fun CreateUserDto.toModel() = UserModel(
  this.name, this.surname, this.password, this.email
)

data class UpdateUserDto(
  @field:NotEmpty
  var name: String,

  @field:NotEmpty
  var surname: String,

  @field:NotEmpty
  @field:Size(min = 6, max = 256)
  var password: String,

  @field:NotEmpty
  @field:Email
  var email: String,

  @field:NotNull
  val id: Long
)

fun UpdateUserDto.toModel() = UserModel(
  this.name, this.surname, this.password, this.email, this.id
)