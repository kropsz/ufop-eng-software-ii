package com.kropsz.market.service.impl

import com.kropsz.market.domain.model.Client
import com.kropsz.market.domain.model.PointsHistory
import com.kropsz.market.domain.repository.ClientRepository
import com.kropsz.market.service.ClientService
import com.kropsz.market.utils.mapper.impl.ClientMapper
import com.kropsz.market.web.dto.ClientDto
import com.kropsz.market.web.dto.ClientLogin
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientServiceImpl (
    val clientRepository: ClientRepository,
    val clientMapper: ClientMapper
): ClientService {

    override fun create(clientDto: ClientDto): Client {
        val client = clientMapper.toEntity(clientDto)
        return clientRepository.save(client)
    }

    override fun login(clientLogin: ClientLogin): Client {
        return clientRepository.findByEmailAndPassword(clientLogin.email, clientLogin.password)
            .orElseThrow { throw EntityNotFoundException("Client not found") }
    }

    override fun findById(id: UUID): Client {
        return clientRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("Client not found") }
    }

    override fun getPointsHistory(id: UUID): List<PointsHistory> {
        return clientRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("Client not found") }
            .pointsHistory
    }
}

