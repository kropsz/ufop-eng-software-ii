package com.kropsz.market.service

import com.kropsz.market.constants.ClientConstants
import com.kropsz.market.domain.repository.ClientRepository
import com.kropsz.market.service.impl.ClientServiceImpl
import com.kropsz.market.utils.mapper.impl.ClientMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ClientServiceTest {

    @Mock
    lateinit var clientRepository: ClientRepository

    @Mock
    lateinit var clientMapper: ClientMapper

    @InjectMocks
    lateinit var clientService: ClientServiceImpl

    private val constants = ClientConstants()



    @Test
    fun `should create a client and return status code 200`() {

        val clientDto = constants.CLIENT_DTO
        val client = constants.CLIENT

        `when`(clientMapper.toEntity(clientDto)).thenReturn(client)
        `when`(clientRepository.save(client)).thenReturn(client)

        val createdClient = clientService.create(clientDto)

        assertEquals(client, createdClient)
        assertEquals(clientDto.name, createdClient.name)
        assertEquals(clientDto.password, createdClient.password)
        assertEquals(clientDto.email, createdClient.email)
        assertEquals(clientDto.phone, createdClient.phone)
        assertEquals(clientDto.address, createdClient.address)
        assertEquals(clientDto.cpf, createdClient.cpf)
    }

    @Test
    fun `should login a client and return status code 200`() {

        val clientLogin = constants.CLIENT_LOGIN
        val client = constants.CLIENT

        `when`(clientRepository.findByEmailAndPassword(clientLogin.email, clientLogin.password)).thenReturn(java.util.Optional.of(client))

        val loggedClient = clientService.login(clientLogin)

        assertEquals(client, loggedClient)
        assertEquals(clientLogin.email, loggedClient.email)
        assertEquals(clientLogin.password, loggedClient.password)
    }

    @Test
    fun `should return exception when user not found or login invalid`() {

        val clientLogin = constants.CLIENT_LOGIN

        `when`(clientRepository.findByEmailAndPassword(clientLogin.email, clientLogin.password)).thenReturn(java.util.Optional.empty())

        try {
            clientService.login(clientLogin)
        } catch (e: Exception) {
            assertEquals("Client not found", e.message)
        }

    }

    @Test
    fun `should return a client by id`() {

        val client = constants.CLIENT

        `when`(clientRepository.findById(client.id!!)).thenReturn(java.util.Optional.of(client))

        val foundClient = clientService.findById(client.id!!)

        assertEquals(client, foundClient)
        assertEquals(client.id, foundClient.id)
    }

    @Test
    fun `should return exception when user not found`() {

            val client = constants.CLIENT

            `when`(clientRepository.findById(client.id!!)).thenReturn(java.util.Optional.empty())

            try {
                clientService.findById(client.id!!)
            } catch (e: Exception) {
                assertEquals("Client not found", e.message)
            }

    }

}