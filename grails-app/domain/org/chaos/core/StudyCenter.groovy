package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * Location participating in a Study
 *   
 * @author David Spies
 */
@EqualsAndHashCode
class StudyCenter {

	String name
	String description
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name(blank:false, unique:true, maxSize:30)
		description(blank:false, maxSize:255)
    }
	
	String toString(){
		return name
	}
}
