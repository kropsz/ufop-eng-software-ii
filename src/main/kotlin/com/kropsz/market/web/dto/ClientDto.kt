package com.kropsz.market.web.dto

import java.io.Serializable

/**
 * DTO for {@link com.kropsz.market.domain.model.Client}
 */
data class ClientDto(
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val address: String? = null,
    val cpf: String? = null
) : Serializable