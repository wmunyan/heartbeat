package com.example

import io.micronaut.http.HttpResponse
import io.micronaut.management.endpoint.annotation.Endpoint
import io.micronaut.management.endpoint.annotation.Read
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Endpoint(id = "heartbeat", prefix = "custom", defaultEnabled = true, defaultSensitive = false)
class HeartbeatController {
    private final Logger log = LoggerFactory.getLogger(HeartbeatController.class)

    @Inject
    HeartbeatService heartbeatService

    @Read
    HttpResponse<?> heartbeat() {
        def response = heartbeatService.heartbeat()
        return HttpResponse.ok(response)
    }
}
