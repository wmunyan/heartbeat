package com.example.discord

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class DiscordControllerTest extends Specification {
    @Inject
    @Client("/")
    HttpClient client

    void "test controller from discord thread"() {
        when:
        HttpResponse<Map<CarType, List<String>>> rez =
            client.toBlocking().exchange(
                HttpRequest.GET('/discord'),
                Map<CarType, List<String>>)
        then:
        rez.status() == HttpStatus.OK
        when:
        Map<CarType, List<String>> body = rez.body()
        then:
        body."SEDAN" == []
    }
}
