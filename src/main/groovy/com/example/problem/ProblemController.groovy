package com.example.problem

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.problem.HttpStatusType
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.zalando.problem.Problem
import reactor.core.publisher.Mono

@Controller("/problem")
class ProblemController {
    @Inject
    ProblemService problemService

    @Get("/")
    Mono<Map> getProblem(HttpRequest request) {
        return problemService.getProblem(request)
    }
}

@Singleton
class ProblemService {
    Mono<Map> getProblem(HttpRequest request) {
        def time = new Date().time
        if (time % 2 == 0) {
            throw Problem.builder()
                .withType(URI.create("https://hbr.org/time-is-even"))
                .withTitle("Time is Even")
                .withStatus(new HttpStatusType(HttpStatus.BAD_REQUEST))
                .withDetail("Current time (long) is EVEN")
                .with("time", time)
                .withInstance(request.uri)
                .build()
        } else {
            return Mono.just([
                "time": time,
                "even": false
            ])
        }
    }
}
