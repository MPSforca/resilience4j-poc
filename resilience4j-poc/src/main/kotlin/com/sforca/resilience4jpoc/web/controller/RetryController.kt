package com.sforca.resilience4jpoc.web.controller

import com.sforca.resilience4jpoc.external.client.FailureServiceClient
import com.sforca.resilience4jpoc.web.api.DefaultResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/retry")
class RetryController {

    @Autowired
    private lateinit var failureServiceClient: FailureServiceClient

    @GetMapping
    fun retry(): DefaultResponse =
        DefaultResponse(failureServiceClient.testRetry(failureRate = 0.7).response)
}