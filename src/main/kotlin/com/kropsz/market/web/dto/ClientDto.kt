package com.kropsz.market.web.dto

import jakarta.validation.constraints.NotNull
import java.io.Serializable

/**
 * DTO for {@link com.kropsz.market.domain.model.Client}
 */
data class ClientDto(
    val name: String? = null,
    val password: String? = null,

    @NotNull(message = "Email is required")
    val email: String? = null,
    val phone: String? = null,

    @NotNull(message = "CPF is required")
    val cpf: String? = null
) : Serializable