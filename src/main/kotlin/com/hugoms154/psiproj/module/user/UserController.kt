package com.hugoms154.psiproj.module.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
  private val repository: UserRepository,
) {

  @GetMapping
  fun list(): List<UserModel> = this.repository.findAll()

  @GetMapping("{userId}")
  fun detail(@PathVariable userId: Long): UserModel? = this.repository.findByIdOrNull(userId)
}