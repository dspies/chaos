package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * A scheduled or unscheduled collection activity outlined for a Study, executed
 * by a a staff member on a single Study Participant
 *
 * @author David Spies
 */
@EqualsAndHashCode
class EventType {

	Study study
	String name
	String description
	Integer displayOrder
	Date dateCreated
	Date lastUpdated

	static mapping = {
		sort displayOrder:"asc"
	}
	
    static constraints = {
		study(nullable:false)
		name(blank:false, unique: 'study', maxSize:25)
		description(nullable:false, blank:true, maxSize:255)
		displayOrder(nullable:false)
    }
	
	String toString() {
		return name;
	}
}
