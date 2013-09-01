package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * @author dspies
 */
@TestFor(Population)
class PopulationTests {

    //Create an existing population for duplicate Population checks
    Population existingPopulation
    Population newPopulation

    @Before
    void setup(){
        existingPopulation = new Population(name:"example", description:"Example Population");

        //Mock the constraints for the domain class
        mockForConstraintsTests(Population, [existingPopulation]);

        newPopulation = new Population();
    }

    void testNullConstraints(){
        //Test for null values
        assertFalse newPopulation.validate();
        assertEquals("nullable", newPopulation.errors["name"])
        assertEquals("nullable", newPopulation.errors["description"])
    }

    void testBlankConstraints(){
        //Test for blank values
        newPopulation = new Population(name:"", description:"");
        assertFalse newPopulation.validate();
        assertEquals("blank", newPopulation.errors["name"])
        assertEquals("blank", newPopulation.errors["description"])
    }

    void testMaxSizeConstraints(){
        //Test for max size values
        newPopulation = new Population(name:"exampleIsWayTooLongToUseAsAName", description:"This is a very long description that does not describe the population but tests the description length validation for the population class. Unfortunately it takes a lot of words to test the description length and therefore I must go on and on and on and on.")
        assertFalse newPopulation.validate()
        assertEquals("maxSize", newPopulation.errors["name"])
        assertEquals("maxSize", newPopulation.errors["description"])
    }

    void testUniqueConstraints(){
        //Test for unique values
        newPopulation = new Population(name:"example", description:"Something useful")
        assertFalse newPopulation.validate()
        assertEquals("unique", newPopulation.errors["name"])
    }

    void testValidConstraints() {
		//Create a valid population
		newPopulation = new Population(name:"example less than 20", description:"Some useful description")
		assertTrue newPopulation.validate()
    }

	void testToString(){
		Population population = new Population(name:"Test", description:"")
		assertEquals("Test", population.toString())
	}	
}