package com.hugoms154.psiproj.module.workplace

import jakarta.validation.constraints.NotEmpty

data class CreateWorkplaceDto(
  @field:NotEmpty
  var name: String,
)

fun CreateWorkplaceDto.toModel() = WorkplaceModel(this.name)

