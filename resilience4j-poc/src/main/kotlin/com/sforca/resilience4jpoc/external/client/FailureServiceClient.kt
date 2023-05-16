package com.sforca.resilience4jpoc.external.client

import com.sforca.resilience4jpoc.external.representation.TestResponse
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "failure-service-client",
    url = "http://localhost:8091/test"
)
interface FailureServiceClient {

    @GetMapping
    @Retry(name = "failure-endpoint")
    fun testRetry(
        @RequestParam("failureRate") failureRate: Double? = null,
        @RequestParam("errorStatusCode") errorStatusCode: Int? = null
    ): TestResponse

    @GetMapping
    @CircuitBreaker(name = "failure-endpoint")
    fun testCircuitBreaker(
        @RequestParam("failureRate") failureRate: Double? = null,
        @RequestParam("errorStatusCode") errorStatusCode: Int? = null
    ): TestResponse
}