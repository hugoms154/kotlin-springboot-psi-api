package com.hugoms154.psiproj.module.patient

import com.hugoms154.psiproj.module.workplace.WorkplaceModel
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalTime

data class CreatePatientDto(
  @field:NotEmpty
  var name: String,

  @field:NotEmpty
  var surname: String,

  @field:NotNull
  var scheduleTime: LocalTime,

  @field:Min(1)
  var price: Long,

  @field:NotNull
  var workplaceId: Long,
)

fun CreatePatientDto.toModel(workplaceModel: WorkplaceModel) = PatientModel(
  this.name, this.surname, this.scheduleTime, this.price, workplaceModel
)

