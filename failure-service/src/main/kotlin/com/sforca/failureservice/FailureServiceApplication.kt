package com.sforca.failureservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FailureServiceApplication

fun main(args: Array<String>) {
    runApplication<FailureServiceApplication>(*args)
}
