package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * Participant characteristics that change from study to study within a
 * given Population
 * @author David Spies
 */
@EqualsAndHashCode
class StudyParticipant {

	Study study
	PopulationParticipant populationParticipant
	String studyParticipantId
	Date dateCreated
	Date lastUpdated

    static constraints = {
        studyParticipantId blank: false, maxSize: 15, unique: 'study'
        populationParticipant unique: 'study'
    }

    String toString(){
        return studyParticipantId
    }
}
