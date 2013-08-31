package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * Describes the event types that a Study Participant 
 * will or has participated in during the course of the study.
 * @author David Spies
 */
@EqualsAndHashCode
class StudyArm {

	String name
	String description
	Study study
	Date dateCreated
	Date lastUpdated

	static hasMany = [eventTypes:EventType]
	
	static constraints = {
	    name blank:false, maxSize:25, unique:['study']
        description blank:false, maxSize:255
        study nullable:false
	}

	String toString(){
        return name
	}
}