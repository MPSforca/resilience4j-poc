package com.sforca.resilience4jpoc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class Resilience4jPocApplication

fun main(args: Array<String>) {
    runApplication<Resilience4jPocApplication>(*args)
}
