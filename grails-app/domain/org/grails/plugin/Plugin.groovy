package org.grails.plugin

import org.grails.wiki.WikiPage
import org.grails.comment.Comment
/*
 * author: Matthew Taylor
 */
class Plugin {

    static final def WIKIS = ['description','installation','faq','screenshots']

    String name
    String title
    WikiPage description
    WikiPage installation
    WikiPage faq
    WikiPage screenshots
    String author
    String authorEmail
    String currentRelease
    String documentationUrl
    String downloadUrl
    String grailsVersion        // version it was developed against
    Boolean official = false    // specifies SpringSource support
    Number avgRating

    static hasMany = [comments:Comment, tags:Tag, ratings:Rating]

    static transients = ['avgRating']

    static constraints = {
        name(nullable: false, unique:true)
        description(nullable: true)
        installation(nullable: true)
        faq(nullable: true)
        screenshots(nullable: true)
        author(nullable: true)
        grailsVersion(nullable:true, blank:false, maxLength:16)
    }

    def getAvgRating() {
        if (!ratings || !ratings.size()) return null // for no ratings, return null
        ratings*.stars.sum() / ratings.size()
    }

    String toString() {
        "$name : $title"
    }
}