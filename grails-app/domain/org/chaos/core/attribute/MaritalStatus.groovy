package org.chaos.core.attribute
import groovy.transform.EqualsAndHashCode
/**
 * Marital status of an individual.
 * @author David Spies
 */
@EqualsAndHashCode
class MaritalStatus {

	String abbreviation
	String name
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		abbreviation(blank:false, unique:true, maxSize:3)
		name(blank:false, unique:true, maxSize:15)
	}
	
	String toString() {
		return name;
	}
}
