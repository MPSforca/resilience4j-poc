package com.sforca.resilience4jpoc.external.client

import com.sforca.resilience4jpoc.external.representation.TestFailureRateResponse
import com.sforca.resilience4jpoc.external.representation.TestResponseTimeResponse
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "failure-service-client", url = "http://localhost:8091/test"
)
interface FailureServiceClient {

    // Retry
    @GetMapping("/failure-rate")
    @Retry(name = "failure-endpoint")
    fun testRetry(
        @RequestParam("failureRate") failureRate: Double? = null,
        @RequestParam("errorStatusCode") errorStatusCode: Int? = null
    ): TestFailureRateResponse

    // Circuit Breaker
    @GetMapping("/failure-rate")
    @CircuitBreaker(name = "failure-endpoint")
    fun testCircuitBreaker(
        @RequestParam("failureRate") failureRate: Double? = null,
        @RequestParam("errorStatusCode") errorStatusCode: Int? = null
    ): TestFailureRateResponse

    // Bulkhead
    @GetMapping("/response-time")
    @Bulkhead(name = "response-time-endpoint", type = Bulkhead.Type.SEMAPHORE)
    fun testLongResponseTimeWithBulkhead(
        @RequestParam("responseTime") responseTime: Long = 5000
    ): TestResponseTimeResponse

    @GetMapping("/response-time")
    fun testLongResponseTimeWithoutBulkhead(
        @RequestParam("responseTime") responseTime: Long = 5000
    ): TestResponseTimeResponse

    @GetMapping("/response-time")
    fun testShortResponseTimeWithoutBulkhead(
        @RequestParam("responseTime") responseTime: Long = 500
    ): TestResponseTimeResponse

    // Rate Limiter
    @GetMapping("/failure-rate")
    @RateLimiter(name = "test-service")
    fun testRateLimiter(
        @RequestParam("failureRate") failureRate: Double? = null,
        @RequestParam("errorStatusCode") errorStatusCode: Int? = null
    ): TestFailureRateResponse
}