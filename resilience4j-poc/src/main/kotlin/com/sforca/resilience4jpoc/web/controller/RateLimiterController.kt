package com.sforca.resilience4jpoc.web.controller

import com.sforca.resilience4jpoc.external.client.FailureServiceClient
import com.sforca.resilience4jpoc.web.api.DefaultResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rate-limiter")
class RateLimiterController {

    @Autowired
    private lateinit var failureServiceClient: FailureServiceClient

    @GetMapping
    fun rateLimiter(): DefaultResponse =
        DefaultResponse(failureServiceClient.testRateLimiter(failureRate = 0.0).response)
}