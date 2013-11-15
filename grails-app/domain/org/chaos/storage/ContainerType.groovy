package org.chaos.storage


import groovy.transform.EqualsAndHashCode

/**
 * Used to represent the most generic forms of containers, such as Freezer, Stack, Box, Rack.
 *
 * @author David Spies
 */
@EqualsAndHashCode
class ContainerType {

    String name
    String description
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, maxSize: 30, unique: true
        description blank: false, maxSize: 255
    }

    String toString() {
        return name
    }
}
