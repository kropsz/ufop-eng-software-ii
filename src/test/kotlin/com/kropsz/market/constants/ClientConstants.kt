package com.kropsz.market.constants

import com.kropsz.market.domain.model.Client
import com.kropsz.market.web.dto.ClientDto
import com.kropsz.market.web.dto.ClientLogin
import java.util.*

class ClientConstants {

    val CLIENT_DTO = ClientDto(
        name = "John Doe",
        password = "123456",
        email = "john@email.com",
        phone = "1234567890",
        address = "123 Main St",
        cpf = "12345678901"
    )

    val CLIENT = Client(
        id = UUID.randomUUID(),
        name = "John Doe",
        password = "123456",
        email = "john@email.com",
        phone = "1234567890",
        address = "123 Main St",
        cpf = "12345678901",
        points = 0,
        rewards = mutableListOf(),
        pointsHistory = mutableListOf()
    )

    val CLIENT_LOGIN = ClientLogin(
        email = "john@email.com",
        password = "123456")
}