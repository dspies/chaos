package org.chaos.core.attribute

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Ethnicity)
class EthnicityTests {

    Ethnicity ethnicity
    Ethnicity existingEthnicity

    @Before
    void setup(){
        existingEthnicity = new Ethnicity(name:'White')

        mockForConstraintsTests(Ethnicity, [existingEthnicity])

        ethnicity = new Ethnicity();
    }

    void testNullConstraints(){
        //Test for null values
        assertFalse(ethnicity.validate())
        assertEquals('nullable', ethnicity.errors['name'])
    }

    void testBlankConstraints(){
        //Test for blank values
        ethnicity.name = ''
        assertFalse(ethnicity.validate())
        assertEquals('blank', ethnicity.errors['name'])
    }

    void testUniqueConstraints(){
        //Test for uniqueness
        ethnicity.name = 'White'
        assertFalse(ethnicity.validate())
        assertEquals('unique', ethnicity.errors['name'])
    }

    void testMaxSizeConstraints(){
        //Test maxSize of name
        ethnicity.name = 'ThisIsAnExtremelyLongValueForAEthnicity'
        assertFalse(ethnicity.validate())
        assertEquals('maxSize', ethnicity.errors['name'])
    }

	void testValidConstraints() {
		//Test valid Ethnicity
		ethnicity.name = 'Hispanic'
		assertTrue(ethnicity.validate())
	}
	
	void testToString() {
		Ethnicity Ethnicity = new Ethnicity(name:'test');
		assertEquals('test', Ethnicity.toString());
	}
	
}
