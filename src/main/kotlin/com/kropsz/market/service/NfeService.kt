package com.kropsz.market.service

import com.kropsz.market.domain.model.NFE
import java.util.*

interface NfeService {
    fun processNfe(clientID: UUID, nfe: NFE): NFE
    fun exchangePointsForProducts(clientId: UUID, productId: UUID): Boolean
}