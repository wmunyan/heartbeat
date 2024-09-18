package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class HeartbeatControllerTest extends Specification {
    @Inject
    EmbeddedServer embeddedServer

    @Inject
    @Client("/")
    HttpClient client

    void "test heartbeat endpoint with field injection"() {
        when:
        HttpResponse<Map<String, Object>> rez =
            client.toBlocking().exchange(
                HttpRequest.GET('/heartbeat1'),
                Map<String, Object>)
        then:
        rez.status() == HttpStatus.OK
        when:
        Map<String, Object> body = rez.body()
        then:
        body."APP" == "UP"
    }

    void "test heartbeat endpoint with constructor injection"() {
        when:
        HttpResponse<Map<String, Object>> rez =
            client.toBlocking().exchange(
                HttpRequest.GET('/heartbeat2'),
                Map<String, Object>)
        then:
        rez.status() == HttpStatus.OK
        when:
        Map<String, Object> body = rez.body()
        then:
        body."APP" == "UP"
    }
}
