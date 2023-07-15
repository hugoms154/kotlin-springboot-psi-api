package com.hugoms154.psiproj.module.user


import jakarta.persistence.*


@Entity
@Table(
  name = "\"user\"", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])]
)
data class UserModel(
  @Column var name: String,

  @Column var surname: String,

  @Column var password: String,

  @Column var email: String,

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
