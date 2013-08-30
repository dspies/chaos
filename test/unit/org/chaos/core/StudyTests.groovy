package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * @author dspies
 */
@TestFor(Study)
class StudyTests {

    Study existingStudy
    Study newStudy
    Population population

    @Before
    void setup(){
        existingStudy = new Study(name:"example", description:"Something useful");

        mockForConstraintsTests(Study, [existingStudy])

        population = new Population(name:"example", description:"");
        newStudy = new Study()
    }

    void testNullConstraints(){
        //Create an empty Study and validate that it cannot be null
        assertFalse newStudy.validate()
        assertEquals("nullable", newStudy.errors["name"]);
        assertEquals("nullable", newStudy.errors["description"])
        assertEquals("nullable", newStudy.errors["population"])
    }

    void testBlankConstraints(){
        //Create a Study with empty strings and validate it cannot be blank
        newStudy.name = ""
        newStudy.description = ""
        newStudy.population = population

        assertFalse("Study should errors with blank values", newStudy.validate())
        assertEquals("blank", newStudy.errors["name"]);
        assertEquals("blank", newStudy.errors["description"])
    }

    void testUniqueConstraints(){
        //Create a study to test uniqueness of the name
        newStudy.name = "example"
        newStudy.description = "Something else"
        newStudy.population = population

        assertFalse newStudy.validate()
        assertEquals("unique", newStudy.errors["name"])
    }

    void testMaxSizeConstraints(){
        //Create a study to test the maxSize of the fields
        newStudy.name = "exampleIsWayTooLongToUseAsAName"
        newStudy.description = "This is a very long description that does not describe the population but tests the description length validation for the population class. Unfortunately it takes a lot of words to test the description length and therefore I must go on and on and on and on."
        newStudy.population = population

        assertFalse newStudy.validate()
        assertEquals("maxSize", newStudy.errors["name"])
        assertEquals("maxSize", newStudy.errors["description"])
    }

    void testValidConstraints() {
		//Create a valid Study
		newStudy = new Study(name:"New Study", description:"Simple Example", population:population)
		assertTrue newStudy.validate()
    }

	void testToString() {
		Study study = new Study(name:"Test");
		assertEquals("Test", study.toString())
	}
}