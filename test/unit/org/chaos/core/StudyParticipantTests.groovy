package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

@TestFor(StudyParticipant)
class StudyParticipantTests {

	Population population
	Study study
	StudyParticipant studyParticipant
	PopulationParticipant populationParticipant
    StudyParticipant existingStudyParticipant
    PopulationParticipant existingPopulationParticipant
    String existingStudyParticipantId

	@Before
	void setup(){
		population = new Population(name:'population', description: '')
		study = new Study(population:population, name:'Study1', description:'')
        populationParticipant = new PopulationParticipant(populationParticipantId: '10000')
        existingPopulationParticipant = new PopulationParticipant(populationParticipantId: '10001')
        existingStudyParticipantId = '1000'
        existingStudyParticipant = new StudyParticipant(study:study,
			populationParticipant: existingPopulationParticipant,
            studyParticipantId:existingStudyParticipantId)

		mockForConstraintsTests(StudyParticipant, [existingStudyParticipant])

        def lastUpdated = new Date()
        def dateCreated = lastUpdated

        studyParticipant = new StudyParticipant(lastUpdated: lastUpdated, dateCreated: dateCreated)
	}

	void testNullConstraints(){
		assertFalse studyParticipant.validate()
		assertEquals 3, studyParticipant.errors.errorCount
		assertEquals 'nullable', studyParticipant.errors['study']
		assertEquals 'nullable', studyParticipant.errors['populationParticipant']
		assertEquals 'nullable', studyParticipant.errors['studyParticipantId']
	}

	void testBlankConstraints(){
        setDefaultProperties()
        studyParticipant.studyParticipantId = ''
		assertFalse studyParticipant.validate()
		assertEquals 1, studyParticipant.errors.errorCount
		assertEquals 'blank', studyParticipant.errors['studyParticipantId']
	}

    void testMaxSizeConstraints(){
        //Test for studyParticipantId less than 15
        setDefaultProperties()
        studyParticipant.studyParticipantId = 'Lorem ipsum dolo'

        assertFalse studyParticipant.validate()
        assertEquals 1, studyParticipant.errors.errorCount
        assertEquals 'maxSize', studyParticipant.errors['studyParticipantId']
    }

    void testUniquePopulationParticipantAndStudyConstraint(){
        //Test that a population participant exists only once in a study
        studyParticipant.populationParticipant = existingPopulationParticipant
        studyParticipant.study = study
        studyParticipant.studyParticipantId = '2000'

        assertFalse studyParticipant.validate()
        assertEquals 1, studyParticipant.errors.errorCount
        assertEquals 'unique', studyParticipant.errors['populationParticipant']
    }

    void testUniqueStudyAndStudyParticipantIdConstraint(){
        //Test that a Study participant id exists only once in a study
        studyParticipant.populationParticipant = populationParticipant
        studyParticipant.study = study
        studyParticipant.studyParticipantId = existingStudyParticipantId

        assertFalse studyParticipant.validate()
        assertEquals 1, studyParticipant.errors.errorCount
        assertEquals 'unique', studyParticipant.errors['studyParticipantId']
    }

    void testToString(){
        studyParticipant.studyParticipantId = '2000'
        assertEquals '2000', studyParticipant.toString()

    }

    void setDefaultProperties(){
        studyParticipant.populationParticipant = populationParticipant
        studyParticipant.study = study
    }
}
