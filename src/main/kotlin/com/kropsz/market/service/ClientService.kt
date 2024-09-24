package com.kropsz.market.service

import com.kropsz.market.domain.model.Client
import com.kropsz.market.domain.model.PointsHistory
import com.kropsz.market.web.dto.ClientDto
import com.kropsz.market.web.dto.ClientLogin
import java.util.*

interface ClientService {

    fun create(clientDto: ClientDto): Client

    fun login(clientLogin: ClientLogin): Client

    fun findById(id: UUID): Client

    fun getPointsHistory(id: UUID): List<PointsHistory>
}