package org.chaos.core.attribute
import groovy.transform.EqualsAndHashCode
/**
 * Spoken Language of an individual.
 * @author David Spies
 */
@EqualsAndHashCode
class Language {

	String name
	Date dateCreated
	Date lastUpdated

    static constraints = {
		name(blank:false, unique:true, maxSize:25)
    }
	
	String toString() {
		return name;
	}
}
