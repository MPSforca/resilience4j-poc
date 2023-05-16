package com.sforca.failureservice.web.controller

import com.sforca.failureservice.web.api.FailureRateResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import kotlin.random.Random

@RestController
@RequestMapping("/test")
class TestEndpoint {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun testFailureRate(
        @RequestParam(
            name = "failureRate",
            defaultValue = "0.5"
        ) failureRate: Double,
        @RequestParam(
            name = "errorStatusCode",
            defaultValue = "500"
        ) errorStatusCode: Int
    ): FailureRateResponse {
        if (failureRate < 0 || failureRate > 1) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Failure rate must be between 0 and 1"
            )
        }
        logger.info(
            "Received a new request with failureRate {} and error status code {}",
            failureRate,
            errorStatusCode
        )
        val random = Random.nextDouble(0.0, 1.0)
        if (random > failureRate) {
            logger.info("Success response")
            return FailureRateResponse("Success!")
        }
        logger.info("Error response")
        throw ResponseStatusException(
            HttpStatus.valueOf(errorStatusCode),
            "Randomized a failure response!"
        )
    }
}