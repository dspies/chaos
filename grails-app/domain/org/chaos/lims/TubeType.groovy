package org.chaos.lims

import groovy.transform.EqualsAndHashCode

/**
 * Type of container holding the sample's material
 *
 * @David Spies
 */
@EqualsAndHashCode
class TubeType {

    //TODO Determine whether to change the class name to something more general such as Container

    String name
    String description
    Double maximumVolumeInMilliliters
    Boolean permitsLabel
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, maxSize: 30, unique: true
        description blank: false, maxSize: 255
        maximumVolumeInMilliliters min: 0D, notEqual: 0D  //TODO Determine whether to allow 0 volume for slide 'tubes'
    }

    String toString() {
        return name
    }
}
