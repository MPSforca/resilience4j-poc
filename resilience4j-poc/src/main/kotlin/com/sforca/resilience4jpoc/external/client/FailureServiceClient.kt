package com.sforca.resilience4jpoc.external.client

import com.sforca.resilience4jpoc.external.representation.TestResponse
import feign.RequestLine
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "failure-service-client",
    url = "http://localhost:8091/"
)
interface FailureServiceClient {

    @GetMapping("/test")
    @Retry(name = "failure-endpoint")
    fun test(
        @RequestParam("failureRate") failureRate: Double? = null,
        @RequestParam("errorStatusCode") errorStatusCode: Int? = null
    ): TestResponse
}