package org.chaos.lims

import groovy.transform.EqualsAndHashCode

/**
 * Type of sample collected, processed, or assayed in the lab.  Examples include Whole Blood, Saliva, Sputum.
 *
 * @author David Spies
 */
@EqualsAndHashCode
class Material {

    String name
    String description
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, maxSize: 35, unique: true
        description blank: false, maxSize: 255
    }

    String toString(){
        return name
    }
}
