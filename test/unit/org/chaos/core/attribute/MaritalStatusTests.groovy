package org.chaos.core.attribute

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(MaritalStatus)
class MaritalStatusTests {

    MaritalStatus existingMaritalStatus
    MaritalStatus maritalStatus

    @Before
    void setup(){
        existingMaritalStatus = new MaritalStatus(name:'Single', abbreviation:'S')

        mockForConstraintsTests(MaritalStatus, [existingMaritalStatus])

        //Test for null values
        maritalStatus = new MaritalStatus();
    }

    void testNullConstraints(){
        assertFalse(maritalStatus.validate())
        assertEquals('nullable', maritalStatus.errors['name'])
        assertEquals('nullable', maritalStatus.errors['abbreviation'])
    }

    void testBlankConstraints(){
        //Test for blank values
        maritalStatus.name = ''
        maritalStatus.abbreviation = ''
        assertFalse(maritalStatus.validate())
        assertEquals('blank', maritalStatus.errors['name'])
        assertEquals('blank', maritalStatus.errors['abbreviation'])
    }

    void testUniqueConstraints(){
        //Test for uniqueness
        maritalStatus.name = 'Single'
        maritalStatus.abbreviation = 'S'
        assertFalse(maritalStatus.validate())
        assertEquals('unique', maritalStatus.errors['name'])
        assertEquals('unique', maritalStatus.errors['abbreviation'])
    }

    void testMaxSizeConstraints(){
        //Test maxSize of name
        maritalStatus.name = 'ThisIsAnExtremelyLongValueForAMaritalStatus'
        maritalStatus.abbreviation = 'Single'
        assertFalse(maritalStatus.validate())
        assertEquals('maxSize', maritalStatus.errors['name'])
        assertEquals('maxSize', maritalStatus.errors['abbreviation'])
    }


	void testValidConstraints() {
        //Test valid MaritalStatus
		maritalStatus.name = 'Married'
        maritalStatus.abbreviation = 'M'
		assertTrue(maritalStatus.validate())
	}
	
	void testToString() {
		MaritalStatus maritalStatus = new MaritalStatus(name:'test');
		assertEquals('test', maritalStatus.toString());
	}
}
