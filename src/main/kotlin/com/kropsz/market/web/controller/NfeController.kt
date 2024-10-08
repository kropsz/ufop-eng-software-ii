package com.kropsz.market.web.controller

import com.kropsz.market.service.NfeService
import com.kropsz.market.web.dto.ProductDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/nfe")
class NfeController (val nfeService: NfeService) {

    @PostMapping("/process/{clientID}/{nfeId}")
    fun processNfe(@PathVariable clientID: UUID, @PathVariable nfeId: UUID): ResponseEntity<String> {
        val result = nfeService.processNfe(clientID, nfeId)
        return if (result) ResponseEntity.ok("NFE processed successfully")
        else ResponseEntity.badRequest().body("NFE already used")
    }

    @PostMapping("/exchange/{clientId}")
    fun exchangePointsForProducts(@PathVariable clientId: UUID, @RequestBody productDto: ProductDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(nfeService.exchangePointsForProducts(clientId, productDto.products))
    }

}