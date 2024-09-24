package com.kropsz.market.web.controller

import com.kropsz.market.service.NfeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/nfe")
class NfeController (val nfeService: NfeService) {

    @PostMapping("/process/{clientID}/{nfeId}")
    fun processNfe(@PathVariable clientID: UUID, @PathVariable nfeId: UUID): ResponseEntity<String> {
        return ResponseEntity.ok(nfeService.processNfe(clientID, nfeId))
    }

    @PostMapping("/exchange/{clientId}/{productId}")
    fun exchangePointsForProducts(@PathVariable clientId: UUID, @PathVariable productId: UUID): ResponseEntity<Boolean> {
        return ResponseEntity.ok(nfeService.exchangePointsForProducts(clientId, productId))
    }

}