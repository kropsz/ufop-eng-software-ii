package com.kropsz.market.service

import java.util.*

interface NfeService {
    fun processNfe(clientID: UUID, nfeId: UUID): String
    fun exchangePointsForProducts(clientId: UUID, productId: UUID): Boolean
    fun verifyNfe(nfeId: UUID): Boolean
}