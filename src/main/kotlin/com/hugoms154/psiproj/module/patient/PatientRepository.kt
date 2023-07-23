package com.hugoms154.psiproj.module.patient

import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository : JpaRepository<PatientModel, Long>

