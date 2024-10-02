package com.kropsz.market.web.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import java.io.Serializable
import java.util.UUID

/**
 * DTO for {@link com.kropsz.market.domain.model.Client}
 */
data class ClientDto(

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: UUID? = null,

    val name: String? = null,
    val password: String? = null,

    @NotNull(message = "Email is required")
    val email: String? = null,
    val phone: String? = null,

    @NotNull(message = "CPF is required")
    val cpf: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val points: Int? = null

) : Serializable