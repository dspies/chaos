package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * @author dspies
 */
@TestFor(ParticipantType)
class ParticipantTypeTests {

    Population population
    Study study
    ParticipantType existingParticipantType
    ParticipantType pt

    @Before
    void setup(){
        population = new Population(name:'population', description:'')
        study = new Study(name:'study', description:'', population:population)
        existingParticipantType = new ParticipantType(name:'Index', description:'Child being observed', study:study)

        mockForConstraintsTests(ParticipantType, [existingParticipantType])

        pt = new ParticipantType()
    }

    void testNullConstraints(){
        assertFalse(pt.validate())
        assertEquals('nullable', pt.errors['name'])
        assertEquals('nullable', pt.errors['description'])
        assertEquals('nullable', pt.errors['study'])
    }

    void testBlankConstraints(){
        //Check for blank values
        pt.name = ''
        pt.description = ''
        pt.study = study

        assertFalse(pt.validate())
        assertEquals('blank', pt.errors['name'])
    }

    void testMaxSizeConstraints(){
        //Check for long values
        pt.name = 'exampleIsWayTooLongToUseAsAName'
        pt.description = """This is a very long description that does not describe the participant types but tests 
            the description length validation for the population class. Unfortunately it takes a lot of words to test 
            the description length and therefore I must go on and on and on and on."""
        pt.study = study

        assertFalse(pt.validate())
        assertEquals('maxSize', pt.errors['name'])
        assertEquals('maxSize', pt.errors['description'])
    }

    void testUniqueConstraints(){
        //Check for duplicate values
        pt.name = 'Index'
        pt.description = ''
        pt.study = study

        assertFalse(pt.validate())
        assertEquals('unique', pt.errors['name'])
    }

    void testValidConstraints() {
		//Check for valid values
		pt.name = 'Second Enrolled'
        pt.description = ''
        pt.study = study

		assertTrue(pt.validate())
    }
	
	void testToString(){
		ParticipantType pt = new ParticipantType(name:'Index')
		assertEquals('Index', pt.toString())
	}
}