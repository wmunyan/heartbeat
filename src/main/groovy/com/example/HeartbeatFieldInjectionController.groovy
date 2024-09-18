package com.example

import io.micronaut.http.HttpResponse
import io.micronaut.management.endpoint.annotation.Endpoint
import io.micronaut.management.endpoint.annotation.Read
import jakarta.annotation.PostConstruct
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Endpoint(id = "heartbeat1", defaultEnabled = true, defaultSensitive = false)
class HeartbeatFieldInjectionController {
    private final Logger log = LoggerFactory.getLogger(HeartbeatFieldInjectionController.class)

    @Inject
    HeartbeatService heartbeatService

    @PostConstruct
    def init() {
        log.info "POST CONSTRUCT: ${heartbeatService ? 'NOT NULL': 'NULL'}"
    }

    @Read
    HttpResponse<?> heartbeat() {
        log.info "[START] Heartbeat (Field Injection) Controller"
        def response = heartbeatService.heartbeat()
        log.info "[ END ] Heartbeat (Field Injection) Controller"
        return HttpResponse.ok(response)
    }
}
