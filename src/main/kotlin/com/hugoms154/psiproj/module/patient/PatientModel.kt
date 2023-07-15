package com.hugoms154.psiproj.module.patient

import com.hugoms154.psiproj.module.workplace.WorkplaceModel
import jakarta.persistence.*
import java.time.LocalTime

@Entity
@Table(name = "\"patient\"")
data class PatientModel(
  @Column var name: String,

  @Column var surname: String,

  @Column var scheduleTime: LocalTime,

  @Column var price: Long,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workplace_id")
  var workplace: WorkplaceModel,

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
