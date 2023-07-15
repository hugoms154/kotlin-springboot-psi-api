package com.hugoms154.psiproj.module.patient

import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/patient")
class PatientController(
  private val repository: PatientRepository,
  private val patientService: PatientService
) {
  @PostMapping
  fun create(@RequestBody @Valid patient: CreatePatientDto): PatientModel = this.patientService.create(patient)

  @GetMapping
  fun list(): Iterable<PatientModel> = this.repository.findAll()

  @GetMapping("{patientId}")
  fun detail(@PathVariable patientId: Long): PatientModel? = this.repository.findByIdOrNull(patientId)
}