package com.kropsz.market.constants

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kropsz.market.domain.model.Client
import com.kropsz.market.domain.model.NFE
import com.kropsz.market.domain.model.PointsHistory
import com.kropsz.market.domain.model.Product
import com.kropsz.market.web.dto.ClientDto
import com.kropsz.market.web.dto.ClientLogin
import java.time.LocalDateTime
import java.util.*

class Constants {

    val CLIENT_DTO = ClientDto(
        name = "John Doe",
        password = "123456",
        email = "john@email.com",
        phone = "1234567890",
        cpf = "12345678901"
    )

    val CLIENT = Client(
        id = UUID.randomUUID(),
        name = "John Doe",
        password = "123456",
        email = "john@email.com",
        phone = "1234567890",
        cpf = "12345678901",
        points = 100,
        rewards = mutableListOf(),
        pointsHistory = mutableListOf()
    )

    val CLIENT_LOGIN = ClientLogin(
        email = "john@email.com",
        password = "123456")


    val NFE_CONSTANT = NFE(
        id = UUID.randomUUID(),
        value = 100.0,
        date = LocalDateTime.now(),
        isUsed = false,
        store = 1,
        paymentMethod = NFE.PaymentMethod.CREDIT_CARD
    )

    val PRODUCT = Product(
        id = UUID.randomUUID(),
        name = "Product",
        description = "Product description",
        priceInPoints = 50,
        stock = 10
    )

    val CLIENT_DTO_JSON: String = jacksonObjectMapper().writeValueAsString(CLIENT_DTO)
    val CLIENT_LOGIN_JSON: String = jacksonObjectMapper().writeValueAsString(CLIENT_LOGIN)

    val POINTS_HISTORY = PointsHistory(
        pointsAdded = 100,
        nfeId = UUID.randomUUID(),
        date = LocalDateTime.now()
    )

}