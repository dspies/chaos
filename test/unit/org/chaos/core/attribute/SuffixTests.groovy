package org.chaos.core.attribute

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Suffix)
class SuffixTests {

    Suffix existingSuffix
    Suffix suffix

    @Before
    void setup(){
        existingSuffix = new Suffix(name:'II')

        mockForConstraintsTests(Suffix, [existingSuffix])

        //Test for null values
        suffix = new Suffix();
    }

    void testNullConstraints(){
        assertFalse(suffix.validate())
        assertEquals('nullable', suffix.errors['name'])
    }

    void testBlankConstraints(){
        //Test for blank values
        suffix.name = ''
        assertFalse(suffix.validate())
        assertEquals('blank', suffix.errors['name'])
    }

    void testUniqueConstraints(){
        //Test for uniqueness
        suffix.name = 'II'
        assertFalse(suffix.validate())
        assertEquals('unique', suffix.errors['name'])
    }

    void testMaxSizeConstraints(){
        //Test maxSize of name
        suffix.name = 'ThisIsAnExtremelyLongValueForAsuffix'
        assertFalse(suffix.validate())
        assertEquals('maxSize', suffix.errors['name'])
    }

    void testValidConstraints() {
		//Test valid suffix
		suffix.name = 'Jr.'
		assertTrue(suffix.validate())
	}
	
	void testToString() {
		Suffix suffix = new Suffix(name:'test');
		assertEquals('test', suffix.toString());
	}
}
