package org.grails.plugin

import org.grails.wiki.WikiPage
import org.grails.content.Version
/*
 * author: Matthew Taylor
 */
class Plugin extends WikiPage {
    String name
    String description
    String installation
    String faq
    String author
    String authorEmail
    String screenshots
    String currentRelease
    String documentationUrl
    String downloadUrl
    String grailsVersion        // version it was developed against
    Boolean official = false    // specifies SpringSource support
    Number avgRating

    static hasMany = [tags:Tag, ratings:Rating]

    static transients = ['avgRating']

    static constraints = {
        name(nullable: true, unique:false)
        // overriding the WikiPage matches constraint for title beause we won't use it for a URL
        title(nullable:false, blank:false, matches:/.*/) 
        description(nullable: true)
        installation(nullable: true)
        faq(nullable: true)
        screenshots(nullable: true)
        author(nullable: true)
        grailsVersion(nullable:true, blank:false, maxLength:16)
    }

//    Version createVersion() {
//        def verObject = super.createVersion()
//        verObject.description = description
//        verObject.installation = installation
//        verObject.faq = faq
//        verObject.screenshots = screenshots
//        verObject.stats = stats
//        verObject.tags = tags
//        verObject.ratings = ratings
//        verObject.currentRelease = currentRelease
//    }

    def getAvgRating() {
        if (!ratings || !ratings.size()) return null // for no ratings, return null
        ratings*.stars.sum() / ratings.size()
    }

    String toString() {
        "$name : $title"
    }
}