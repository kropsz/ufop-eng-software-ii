package com.kropsz.market.utils.mapper

interface Mapper<D, E> {

    fun toEntity(dto: D): E

    fun toDto(entity: E): D
}