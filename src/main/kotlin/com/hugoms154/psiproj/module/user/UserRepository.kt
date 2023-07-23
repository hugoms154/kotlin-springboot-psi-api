package com.hugoms154.psiproj.module.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserModel, Long> {
  fun findByEmail(email: String): UserModel?
}

