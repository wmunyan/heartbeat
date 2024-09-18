package com.example

import io.micronaut.http.HttpResponse
import io.micronaut.management.endpoint.annotation.Endpoint
import io.micronaut.management.endpoint.annotation.Read
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Endpoint(id = "heartbeat2", defaultEnabled = true, defaultSensitive = false)
class HeartbeatConstructorInjectionController {
    private final Logger log = LoggerFactory.getLogger(HeartbeatConstructorInjectionController.class)

    final HeartbeatService heartbeatService

    HeartbeatConstructorInjectionController(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService
    }

    @Read
    HttpResponse<?> heartbeat() {
        log.info "[START] Heartbeat (Constructor Injection) Controller"
        def response = heartbeatService.heartbeat()
        log.info "[ END ] Heartbeat (Constructor Injection) Controller"
        return HttpResponse.ok(response)
    }
}
