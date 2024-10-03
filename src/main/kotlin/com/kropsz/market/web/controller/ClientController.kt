package com.kropsz.market.web.controller

import com.kropsz.market.domain.model.PointsHistory
import com.kropsz.market.service.ClientService
import com.kropsz.market.utils.mapper.impl.ClientMapper
import com.kropsz.market.web.dto.ClientDto
import com.kropsz.market.web.dto.ClientLogin
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/client")
@RestController
class ClientController (val clientService: ClientService,
    val clientMapper: ClientMapper) {

    @PostMapping("/register")
    fun registerClient(@RequestBody clientDto: ClientDto): ResponseEntity<ClientDto> {
        return try {
            val response = clientMapper.toDto(clientService.create(clientDto))
            ResponseEntity.status(201).body(response)
        } catch (e: Exception) {
            ResponseEntity.status(400).build()
        }
    }

    @PostMapping("/login")
    fun loginClient(@RequestBody clientLogin: ClientLogin): ResponseEntity<ClientDto> {
        return try {
            val response = clientMapper.toDto(clientService.login(clientLogin))
            ResponseEntity.status(200).body(response)
        } catch (e: Exception) {
            ResponseEntity.status(400).build()
        }
    }

    @GetMapping("/{id}")
    fun getClient(@PathVariable id: UUID): ResponseEntity<ClientDto> {
        return try {
            val response = clientMapper.toDto(clientService.findById(id))
            ResponseEntity.status(200).body(response)
        } catch (e: Exception) {
            ResponseEntity.status(400).build()
        }
    }

    @GetMapping("/points-history/{id}")
    fun getPointsHistory(@PathVariable id: UUID): ResponseEntity<List<PointsHistory>> {
        return try {
            val response = clientService.getPointsHistory(id)
            ResponseEntity.status(200).body(response)
        } catch (e: Exception) {
            ResponseEntity.status(400).build()
        }
    }

    @GetMapping("/points/{id}")
    fun getPointsByUser(@PathVariable id: UUID): ResponseEntity<Int> {
        return try {
            val response = clientService.getPointsByUser(id)
            ResponseEntity.status(200).body(response)
        } catch (e: Exception) {
            ResponseEntity.status(400).build()
        }
    }

}