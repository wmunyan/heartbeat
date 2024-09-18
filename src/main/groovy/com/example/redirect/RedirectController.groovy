package com.example.redirect

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.RequestFilter
import io.micronaut.http.annotation.ServerFilter
import io.micronaut.http.filter.FilterContinuation
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/redirect")
class RedirectController {

    @Get("/root")
    def root() {
        "Root"
    }

    @Get("/source")
    def source() {
        "Source"
    }

    @Get("/dest")
    def dest() {
        "Dest"
    }
}


@ServerFilter("/**")
class Redirector {
    @RequestFilter
    @ExecuteOn(TaskExecutors.BLOCKING) // (3)
    HttpResponse redirectRequest(HttpRequest<?> request, FilterContinuation<HttpResponse> continuation) {
        if (request.uri.toString() == "/redirect/root") {
            println "FILTER MATCHED /redirect/root"
            HttpResponse.redirect(URI.create("/redirect/dest"))
        } else {
            continuation.proceed()
        }
    }

}