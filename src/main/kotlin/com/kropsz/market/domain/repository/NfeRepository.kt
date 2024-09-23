package com.kropsz.market.domain.repository

import com.kropsz.market.domain.model.NFE
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NfeRepository: JpaRepository<NFE, UUID> {
}