package com.example.discord

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonUnwrapped
import groovy.json.JsonOutput
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/discord")
class DiscordController {


    @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
    def get() {
    //Map<String, List<String>> get() {

        Map<CarType, List<String>> response = [(CarType.SEDAN): []]
        //def response = new ResponseBody(foo: [(CarType.SEDAN): []])
        println JsonOutput.prettyPrint(JsonOutput.toJson(response))
        return HttpResponse.ok(response)//Map.of(CarType.SEDAN, [])
    }
}

class ResponseBody {
    @JsonUnwrapped
    @JsonInclude
    Map<CarType, List<String>> foo
}

enum CarType {
    SEDAN
}