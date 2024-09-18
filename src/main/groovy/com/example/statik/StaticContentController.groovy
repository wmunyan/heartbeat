package com.example.statik

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Inject
import reactor.core.publisher.Mono

@Controller("/")
class StaticContentController {
    @Inject
    StaticContentClient staticContentClient

    @Get("/resources{/resourcePath:.*}")
    def get(@PathVariable String resourcePath) {
        return "hit /resourcePath:.*"//staticContentClient.getStaticResource(resourcePath);
    }

    @Get("/resources/xml/atom/{name}")
    def atom(@PathVariable String name) {
        return "hit /resources/xml/atom/${name}"
    }
}

interface StaticContent {
    Object getStaticResource()
}

@Client
interface StaticContentClient extends StaticContent {
    @Get("https://hbr.org/resources/{resourcePath}")
    Object getStaticResource(@PathVariable String resourcePath)

}
