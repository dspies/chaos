package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * @author dspies
 */
@TestFor(EventType)
class EventTypeTests {

    Study study
    StudyArm studyArm
    EventType existingEventType
    EventType eventType

    @Before
    void setup(){
        study = new Study(name:'study', description:'')
        studyArm = new StudyArm(name: 'studyArm', study:study)
        existingEventType = new EventType(name:'test', description:'simple description', studyArm:studyArm)

        mockForConstraintsTests(EventType, [existingEventType])

        eventType = new EventType();
    }

    void testNullConstraints(){
        //Test for null values
        assertFalse(eventType.validate())
        assertEquals('nullable', eventType.errors['name']);
        assertEquals('nullable', eventType.errors['description'])
        assertEquals('nullable', eventType.errors['studyArm'])
        assertEquals('nullable', eventType.errors['displayOrder'])
    }

    void testBlankConstraints(){
        //Test for blank
        eventType.name = ''
        eventType.description = ''
        eventType.studyArm = studyArm
        eventType.displayOrder = 0

        assertFalse(eventType.validate())
        assertEquals('blank', eventType.errors['name'])
    }

    void testMaxSizeConstraints(){
        //Test for max size values
        eventType.name = 'exampleIsWayTooLongToUseAsAName'
        eventType.description = """This is a very long description that does not describe the event type 
            but tests the description length validation for the population class. Unfortunately it takes 
            a lot of words to test the description length and therefore I must go on and on and on and on."""
        eventType.studyArm = studyArm
        eventType.displayOrder = 0

        assertFalse(eventType.validate())
        assertEquals("maxSize", eventType.errors['name'])
        assertEquals('maxSize', eventType.errors['description'])
    }

    void testUniqueConstraints(){
        //Test for unique values
        eventType.name = 'test'
        eventType.description = 'some description'
        eventType.studyArm = studyArm
        eventType.displayOrder = 0

        assertFalse(eventType.validate())
        assertEquals('unique', eventType.errors['name'])
    }

    void testValidConstraints() {
		//Create a valid event type
		eventType.name = 'enrollment'
        eventType.description = 'Enrollment Visit'
        eventType.studyArm = studyArm
        eventType.displayOrder = 1

		assertTrue(eventType.validate())
    }
	
	void testToString() {
		EventType eventType = new EventType(name:'Enrollment')
		assertEquals('Enrollment', eventType.toString())
	}
}