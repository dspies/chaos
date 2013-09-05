package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * Participant characteristics that do not change from study to study
 * @author David Spies
 */
@EqualsAndHashCode
class PopulationParticipant {

	Population population
	Person person
	String populationParticipantId
	Date lastUpdated
	Date dateCreated

    static constraints = {
    	populationParticipantId blank:false, maxSize:30, unique: 'population'
        person unique: 'population'
    }

    String toString(){
    	return populationParticipantId;
    }
}
