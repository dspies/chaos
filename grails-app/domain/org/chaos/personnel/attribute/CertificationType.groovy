package org.chaos.personnel.attribute


import groovy.transform.EqualsAndHashCode

/**
 * @author David Spies
 */
@EqualsAndHashCode
class CertificationType {

    String name
    String description
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank:false, maxSize: 35, unique: true
        description blank: false, maxSize: 255
    }

    String toString() {
        return name
    }
}
