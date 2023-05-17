package com.sforca.resilience4jpoc.web.controller

import com.sforca.resilience4jpoc.external.client.FailureServiceClient
import com.sforca.resilience4jpoc.web.api.DefaultResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bulkhead")
class BulkheadController {

    @Autowired
    private lateinit var failureServiceClient: FailureServiceClient

    @GetMapping("/short-request")
    fun shortRequest(): DefaultResponse = DefaultResponse(
        failureServiceClient.testShortResponseTimeWithoutBulkhead().message
    )

    @GetMapping("/long-request-without-bulkhead")
    fun longRequestWithoutBulkhead(): DefaultResponse = DefaultResponse(
        failureServiceClient.testLongResponseTimeWithoutBulkhead().message
    )

    @GetMapping("/long-request-with-bulkhead")
    fun longRequestWithBulkhead(): DefaultResponse = DefaultResponse(
        failureServiceClient.testLongResponseTimeWithBulkhead().message
    )
}