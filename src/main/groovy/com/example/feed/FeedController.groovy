package com.example.feed

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject

@Controller("/feed")
class FeedController {

    @Inject
    FeederClient feederClient

    @Get("/")
    Feed get() {
        return feederClient.getFeed()
    }

    @Operation(summary = "Greets a person",
        description = "A friendly greeting is returned"
    )
    @ApiResponse(
        content = @Content(mediaType = "text/plain",
            schema = @Schema(type="string"))
    )
    @ApiResponse(responseCode = "400", description = "Invalid Name Supplied")
    @ApiResponse(responseCode = "404", description = "Person not found")
    @Tag(name = "greeting")
    @Get(uri = "/xml", produces = MediaType.APPLICATION_XML)
    Feed getXml() {
        return feederClient.getFeed()
    }
}
