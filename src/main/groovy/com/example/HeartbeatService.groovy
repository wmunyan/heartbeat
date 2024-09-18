package com.example

import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton

@Singleton
class HeartbeatService {

    def heartbeat() {
        def rez = [:]

        rez["APP"] = "UP"
        return rez
    }
}
