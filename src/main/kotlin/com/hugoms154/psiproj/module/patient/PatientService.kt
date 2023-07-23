package com.hugoms154.psiproj.module.patient

import com.hugoms154.psiproj.module.workplace.WorkplaceRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientService(
  @Autowired
  private val workplaceRepository: WorkplaceRepository,

  @Autowired
  private val patientRepository: PatientRepository,
) {

  fun create(createPatientDto: CreatePatientDto): PatientModel {
    val workplaceModel =
      workplaceRepository.findByIdOrNull(createPatientDto.workplaceId) ?: throw Exception("Workplace not found")
    val patientModel = createPatientDto.toModel(workplaceModel)
    return patientRepository.save(patientModel)
  }
}