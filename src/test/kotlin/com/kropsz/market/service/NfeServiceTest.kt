package com.kropsz.market.service

import com.kropsz.market.constants.Constants
import com.kropsz.market.domain.model.Reward
import com.kropsz.market.domain.repository.ClientRepository
import com.kropsz.market.domain.repository.NfeRepository
import com.kropsz.market.domain.repository.ProductRepository
import com.kropsz.market.domain.repository.RewardRepository
import com.kropsz.market.service.impl.NfeServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class NfeServiceTest {

    @Mock
    lateinit var clientRepository: ClientRepository

    @Mock
    lateinit var nfeRepository: NfeRepository

    @Mock
    lateinit var productRepository: ProductRepository

    @Mock
    lateinit var rewardRepository: RewardRepository

    @InjectMocks
    lateinit var nfeService: NfeServiceImpl

    private val constants = Constants()


    @Test
    fun `should process NFE and add points to client`() {
        val client = constants.CLIENT
        val nfe = constants.NFE_CONSTANT

        `when`(clientRepository.findById(client.id!!)).thenReturn(Optional.of(client))
        `when`(nfeRepository.findById(nfe.id!!)).thenReturn(Optional.of(nfe))

        val result = nfeService.processNfe(client.id!!, nfe.id!!)

        assertEquals("Points added successfully", result)
        assertEquals(110, client.points)
        assertTrue(nfe.isUsed)
        verify(clientRepository).save(client)
        verify(nfeRepository).save(nfe)
    }

    @Test
    fun `should not process NFE if it is not valid or already used`() {
        val client = constants.CLIENT
        val nfe = constants.NFE_CONSTANT
        nfe.isUsed = true
        `when`(clientRepository.findById(client.id!!)).thenReturn(Optional.of(client))
        `when`(nfeRepository.findById(nfe.id!!)).thenReturn(Optional.of(nfe))

        val result = nfeService.processNfe(client.id!!, nfe.id!!)

        assertEquals("NFE is not valid or is used", result)
        assertEquals(100, client.points)
        assertTrue(nfe.isUsed)
        verify(clientRepository, never()).save(client)
        verify(nfeRepository, never()).save(nfe)
    }

    @Test
    fun `should exchange points for products`() {
        val client = constants.CLIENT
        val product = constants.PRODUCT
        val productsId = mutableListOf(product.id!!)

        `when`(clientRepository.findById(client.id!!)).thenReturn(Optional.of(client))
        `when`(productRepository.findById(product.id!!)).thenReturn(Optional.of(product))

        val result = nfeService.exchangePointsForProducts(client.id!!, productsId)

        assertTrue(result)
        assertEquals(50, client.points)
        assertEquals(9, product.stock)
        verify(clientRepository).save(client)
        verify(productRepository).save(product)
        verify(rewardRepository).save(any(Reward::class.java))
    }

    @Test
    fun `should not exchange points for products if points are insufficient or stock is zero`() {
        val client = constants.CLIENT
        val product = constants.PRODUCT
        val productsId = mutableListOf(product.id!!)

        client.points = 0
        product.stock = 0
        `when`(clientRepository.findById(client.id!!)).thenReturn(Optional.of(client))
        `when`(productRepository.findById(product.id!!)).thenReturn(Optional.of(product))

        val result = nfeService.exchangePointsForProducts(client.id!!, productsId)

        assertFalse(result)
        assertEquals(0, client.points)
        assertEquals(0, product.stock)
        verify(clientRepository, never()).save(client)
        verify(productRepository, never()).save(product)
        verify(rewardRepository, never()).save(any(Reward::class.java))
    }

    @Test
    fun `should verify NFE validity`() {
        val nfe = constants.NFE_CONSTANT

        `when`(nfeRepository.findById(nfe.id!!)).thenReturn(Optional.of(nfe))

        val result = nfeService.verifyNfe(nfe.id!!)

        assertTrue(result)
    }

    @Test
    fun `should not verify NFE if it is older than 7 days or already used`() {
        val nfe = constants.NFE_CONSTANT
        nfe.isUsed = true
        `when`(nfeRepository.findById(nfe.id!!)).thenReturn(Optional.of(nfe))

        val result = nfeService.verifyNfe(nfe.id!!)

        assertFalse(result)
    }
}