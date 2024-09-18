package com.example.info

import com.example.feed.Feed
import com.example.feed.FeederClient
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture
import java.util.stream.Stream

@Controller("/")
class InfoController {
    private final Logger log = LoggerFactory.getLogger(InfoController.class)

    @Inject
    FeederClient feederClient

    @Inject
    VersionService versionService

    @Get("/info-blocking")
    @ExecuteOn(TaskExecutors.IO)
    VersionInfo getB() {
        def start = System.currentTimeMillis()
        VersionInfo rez = new VersionInfo(
            bffMobileInfo: feederClient.getBffMobileInfo1().body()."build"."version",
            feedsInfo: feederClient.getFeedsInfo1().body()."build"."version",
            msContentInfo: feederClient.getContentInfo1().body()."build"."version"
        )
        def end = System.currentTimeMillis()
        log.info "Time Diff: ${end - start}"
        return rez
    }

    @Get("/info-mono")
    @ExecuteOn(TaskExecutors.IO)
    Mono<VersionInfo> getM() {
        def start = System.currentTimeMillis()

        Mono<VersionInfo> rez = versionService.get2()

        def end = System.currentTimeMillis()
        log.info "Time Diff: ${end - start}"
        return rez
    }
}

@Singleton
class VersionService {

    @Inject
    FeederClient feederClient

    Mono<VersionInfo> get() {
        feederClient.getBffMobileInfo()
            .log()
            .zipWith(feederClient.getBffMobileInfo(), { m1, m2 ->
                return new VersionInfo(
                    bffMobileInfo1: m1.body()."build"."version",
                    bffMobileInfo2: m2.body()."build"."version"
                )
            })
            .log()
            .zipWith(feederClient.getContentInfo(), { vi, m3 ->
                vi.msContentInfo = m3.body()."build"."version"
                return vi
            })
            .log()
    }

    Mono<VersionInfo> get2() {
        Mono<HttpResponse<Map>> c1 = feederClient.getBffMobileInfo()
        Mono<HttpResponse<Map>> c2 = feederClient.getFeedsInfo()
        Mono<HttpResponse<Map>> c3 = feederClient.getContentInfo()

        c1.zipWith(c2, { m1, m2 ->
            return [
                bffMobileInfo: m1.body()."build"."version",
                feedsInfo: m2.body()."build"."version"
            ]
        })
        .log()
        .zipWith(c3, {map, m3 ->
            map["msContentInfo"] = m3.body()."build"."version"
            map
        })
        .log()
        .map(m -> new VersionInfo(m))
        .log()
    }
}

class VersionInfo {
    @JsonProperty("bff-mobile")
    String bffMobileInfo
    @JsonProperty("ms-content")
    String msContentInfo
    @JsonProperty("ms-feeds")
    String feedsInfo

    @Override
    String toString() {
        return "mob: ${bffMobileInfo}; cont: ${msContentInfo}; feeds: ${feedsInfo}"
    }
}