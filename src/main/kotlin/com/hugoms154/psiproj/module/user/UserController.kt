package com.hugoms154.psiproj.module.user

import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
  private val repository: UserRepository,
) {
  @PostMapping
  fun create(@RequestBody @Valid user: CreateUserDto): UserModel = this.repository.save(user.toModel())

  @GetMapping
  fun list(): Iterable<UserModel> = this.repository.findAll()

  @GetMapping("{userId}")
  fun detail(@PathVariable userId: Long): UserModel? = this.repository.findByIdOrNull(userId)
}