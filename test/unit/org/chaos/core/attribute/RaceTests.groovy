package org.chaos.core.attribute

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Race)
class RaceTests {

    Race existing
    Race race

    @Before
    void setup(){
        //Create existing Race
        existing = new Race(name:'Race')

        mockForConstraintsTests(Race, [existing])

        //Test for null Race name
        race = new Race()

    }

    void testNullConstraints(){
        assertFalse race.validate()
        assertEquals "nullable", race.errors['name']
    }

    void testBlankConstraints(){
        //Test for blank Race name
        race.name = ''
        assertFalse race.validate()
        assertEquals 'blank', race.errors['name']
    }

    void testUniqueConstraints(){
        //Test for Duplicate Race name
        race.name = 'Race'
        assertFalse race.validate()
        assertEquals 'unique', race.errors['name']
    }

    void testMaxSizeConstraints(){
        //Test for Race name greater than 15 characters
        race.name = 'ThisIsGreaterThan15Characters'
        assertFalse race.validate()
        assertEquals "maxSize", race.errors['name']
    }


    void testValidConstraints() {
    	//Test for valid Race name
		race.name = 'Other'
		assertTrue race.validate()
    }
	
	void testToString(){
		Race race = new Race(name:'Race')
		assertEquals "Race", race.toString()
	}
}
