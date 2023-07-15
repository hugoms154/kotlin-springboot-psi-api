package com.hugoms154.psiproj.module.workplace

import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/workplace")
class WorkplaceController(
  private val repository: WorkplaceRepository
) {
  @PostMapping
  fun create(@RequestBody @Valid patient: CreateWorkplaceDto): WorkplaceModel = this.repository.save(patient.toModel())

  @GetMapping
  fun list(): List<WorkplaceModel> = this.repository.findAll()

  @GetMapping("{workplaceId}")
  fun detail(@PathVariable workplaceId: Long): WorkplaceModel? = this.repository.findByIdOrNull(workplaceId)
}