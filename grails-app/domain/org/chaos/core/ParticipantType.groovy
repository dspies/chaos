package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * Describes the role of the Participant in the Study 
 *
 * @author David Spies
 */
@EqualsAndHashCode
class ParticipantType {

	Study study
	String name
	String description
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		study(nullable:false)
		name(blank:false, unique:'study', maxSize:25)
		description(blank:true, nullable:false, maxSize:255)
    }
	
	String toString(){
		return name;
	}
}
