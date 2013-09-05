package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * Collection of Participants recruited for a common
 * purpose, and usually under a common consent
 *   
 * @author David Spies
 */
@EqualsAndHashCode
class Population {
	
	List<Study> studies
    String name
	String description
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [studies:Study]
	
    static constraints = {
		name(blank:false, maxSize:20, unique:true)
		description(blank:false, maxSize:255)
    }

	String toString(){
		return name;
	}
}
