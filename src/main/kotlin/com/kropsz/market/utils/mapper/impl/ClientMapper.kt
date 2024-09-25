package com.kropsz.market.utils.mapper.impl

import com.kropsz.market.domain.model.Client
import com.kropsz.market.utils.mapper.Mapper
import com.kropsz.market.web.dto.ClientDto
import org.springframework.stereotype.Component

@Component
class ClientMapper: Mapper<ClientDto, Client> {
    override fun toEntity(dto: ClientDto): Client {
        return Client(
            name = dto.name!!,
            password = dto.password!!,
            email = dto.email!!,
            phone = dto.phone!!,
            cpf = dto.cpf!!,
            points = 0,
            rewards = mutableListOf()
        )
    }

    override fun toDto(entity: Client): ClientDto {
        return ClientDto(
            entity.name,
            entity.email,
            entity.phone,
            entity.cpf
        )
    }
}