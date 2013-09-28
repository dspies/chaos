package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * A collection of Participants and EventTypes for the
 * purpose of researching one or more outcomes
 * 
 * @author David Spies
 */
@EqualsAndHashCode
class Study {

	Population population
	String name
	String description
	Date dateCreated
	Date lastUpdated

    static belongsTo = [StudyCenter]

    static hasMany = [eventTypes:EventType, studyCenters: StudyCenter]
	
    static constraints = {
		name(blank:false, maxSize:20, unique:true)
		description(blank:false, maxSize:255)
		population(nullable:false)
    }
	
	String toString(){
		return name;
	}
}
