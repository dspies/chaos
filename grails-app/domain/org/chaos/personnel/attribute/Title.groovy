package org.chaos.personnel.attribute

import groovy.transform.EqualsAndHashCode
/**
 * Title of the staff member.
 * @author David Spies
 */
@EqualsAndHashCode
class Title {

    String name
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(blank:false, maxSize:25, unique:true)
    }

    String toString(){
        return name;
    }
}
