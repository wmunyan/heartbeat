package com.example.feed

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import jakarta.annotation.PostConstruct
import reactor.core.publisher.Mono

interface Feeder {
    Feed getFeed()
    HttpResponse<?> getFeedXml()
    Mono<HttpResponse<Map>> getContentInfo()
    Mono<HttpResponse<Map>> getBffMobileInfo()

    HttpResponse<Map> getContentInfo1()
    HttpResponse<Map> getBffMobileInfo1()
}

@Client
interface FeederClient extends Feeder {
    @Get("https://hbr.org/resources/xml/atom/latest-hbr.xml")
    Feed getFeed()
    @Get("https://hbr.org/resources/xml/atom/latest-hbr.xml")
    HttpResponse<?> getFeedXml()

    @Get("https://platform.qa.hbsp.harvard.edu/hbr/api/content/info")
    Mono<HttpResponse<Map>> getContentInfo()
    @Get("https://platform.qa.hbsp.harvard.edu/hbr/bff/mobile/info")
    Mono<HttpResponse<Map>> getBffMobileInfo()
    @Get("https://platform.qa.hbsp.harvard.edu/hbr/api/feeds/info")
    Mono<HttpResponse<Map>> getFeedsInfo()

    @Get("https://platform.qa.hbsp.harvard.edu/hbr/api/content/info")
    HttpResponse<Map> getContentInfo1()
    @Get("https://platform.qa.hbsp.harvard.edu/hbr/bff/mobile/info")
    HttpResponse<Map> getBffMobileInfo1()
    @Get("https://platform.qa.hbsp.harvard.edu/hbr/api/feeds/info")
    HttpResponse<Map> getFeedsInfo1()
}

@JsonPropertyOrder([ "title", "id", "links", "updated", "number-of-entries", "entries" ])
@Introspected
@JacksonXmlRootElement(localName = "feed")
class Feed {
    String title
    String id
    @JacksonXmlProperty(localName = "link")
    @JacksonXmlElementWrapper(useWrapping = false, localName = "link")
    List<Link> links

    String updated

    @JacksonXmlProperty(localName = "entry")
    @JacksonXmlElementWrapper(useWrapping = false, localName = "entry")
    List<Entry> entries

    @JsonProperty("number-of-entries")
    int numberOfEntries() {
        entries.size()
    }
}

@Introspected
class Entry {
    String title
    String id
    @JacksonXmlProperty(localName = "link")
    @JacksonXmlElementWrapper(useWrapping = false, localName = "link")
    List<Link> links

    String updated
    String published

    @JacksonXmlCData
    String summary

    @JacksonXmlProperty(localName = "author")
    @JacksonXmlElementWrapper(useWrapping = false, localName = "author")
    List<Person> authors

    @JacksonXmlProperty(localName = "series-label")
    @JacksonXmlCData
    @JsonProperty("series-label")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String seriesLabel

    @JacksonXmlProperty(localName = "feature-image-uri")
    @JsonProperty("feature-image-uri")
    Image featureImageUri

    @JacksonXmlProperty(localName = "feature-image-title")
    @JsonProperty("feature-image-title")
    String featureImageTitle

    @JacksonXmlProperty(localName = "category")
    @JacksonXmlElementWrapper(useWrapping = false, localName = "category")
    List<Category> categories

    @JacksonXmlCData
    String content
}

@Introspected
class Link {
    @JacksonXmlProperty(isAttribute = true)
    String href
    @JacksonXmlProperty(isAttribute = true)
    String rel
    @JacksonXmlProperty(isAttribute = true)
    String type
}

@Introspected
class Image {
    @JacksonXmlProperty(isAttribute = true)
    String sizes
    @JacksonXmlProperty(isAttribute = true)
    String srcset
    @JacksonXmlText
    String value
}

@Introspected
class Person {
    String name

    @JacksonXmlCData
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String bio

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String uri

    @JacksonXmlProperty(localName = "twitter-handle")
    @JsonProperty("twitter-handle")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String twitter
}

@Introspected
class Category {
    @JacksonXmlProperty(isAttribute = true)
    String term
    @JacksonXmlProperty(isAttribute = true)
    String scheme
    @JacksonXmlProperty(isAttribute = true)
    String label
}