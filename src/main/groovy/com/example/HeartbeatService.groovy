package com.example

import jakarta.inject.Singleton

@Singleton
class HeartbeatService {
    def heartbeat() {
        def rez = [:]

        rez["BFF"] = "UP"
        return rez
    }
}
