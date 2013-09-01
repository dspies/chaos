package org.chaos.core.attribute

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Sex)
class SexTests {

    Sex existingSex
    Sex sex
    
    @Before
    void setup(){
        existingSex = new Sex(name:'Male', abbreviation:'M')

        mockForConstraintsTests(Sex, [existingSex])

        //Test for null values
        sex = new Sex();
    }

    void testNullConstraints(){
        assertFalse(sex.validate())
        assertEquals('nullable', sex.errors['name'])
        assertEquals('nullable', sex.errors['abbreviation'])
    }

    void testBlankConstraints(){
        //Test for blank values
        sex.name = ''
        sex.abbreviation = ''

        assertFalse(sex.validate())
        assertEquals('blank', sex.errors['name'])
        assertEquals('blank', sex.errors['abbreviation'])
    }

    void testUniqueConstraints(){
        //Test for uniqueness
        sex.name = 'Male'
        sex.abbreviation = 'M'
        assertFalse(sex.validate())
        assertEquals('unique', sex.errors['name'])
        assertEquals('unique', sex.errors['abbreviation'])
    }

    void testMaxSizeConstraints(){
        //Test maxSize of name
        sex.name = 'ThisIsAnExtremelyLongValueForASex'
        sex.abbreviation = 'Male'
        assertFalse(sex.validate())
        assertEquals('maxSize', sex.errors['name'])
        assertEquals('maxSize', sex.errors['abbreviation'])
    }

    void testValidConstraints() {
		//Test valid sex
		sex.name = 'Female'
        sex.abbreviation = 'F'
		assertTrue(sex.validate())
	}
	
	void testToString() {
		Sex stringSex = new Sex(name:'test');
        assertEquals('test', stringSex.toString());
	}
	
}
