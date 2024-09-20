package com.kropsz.market.domain.repository

import com.kropsz.market.domain.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository: JpaRepository<Client, UUID> {
}