package com.hugoms154.psiproj.module.workplace

import jakarta.persistence.*

@Entity
@Table(name = "\"workplace\"")
data class WorkplaceModel(
  @Column var name: String,

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {
  constructor(id: Long) : this("")
}
