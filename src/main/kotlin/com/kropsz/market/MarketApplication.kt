package com.kropsz.market

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarketApplication

fun main(args: Array<String>) {
    runApplication<MarketApplication>(*args)
}
