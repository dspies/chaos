package org.chaos.core.attribute

import groovy.transform.EqualsAndHashCode;

/**
 * Name suffix of an individual.
 * @author David Spies
 */
@EqualsAndHashCode
class Suffix {

	String name
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name(blank:false, unique:true, maxSize:15)
    }
	
	String toString() {
		return name;
	}
}