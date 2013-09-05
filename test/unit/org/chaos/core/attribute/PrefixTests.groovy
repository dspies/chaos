package org.chaos.core.attribute

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Prefix)
class PrefixTests {

    Prefix existingPrefix
    Prefix prefix

    @Before
    void setup(){
        existingPrefix = new Prefix(name: 'Mr')

        mockForConstraintsTests(Prefix, [existingPrefix])

        prefix = new Prefix();
    }

    void testNullConstraints(){
        assertFalse(prefix.validate())
        assertEquals('nullable', prefix.errors['name'])
    }

    void testBlankConstraints(){
        //Test for blank values
        prefix.name = ''
        assertFalse(prefix.validate())
        assertEquals('blank', prefix.errors['name'])
    }

    void testUniqueConstraints(){
        //Test for uniqueness
        prefix.name = 'Mr'
        assertFalse(prefix.validate())
        assertEquals('unique', prefix.errors['name'])
    }

    void testMaxSizeConstraints(){
        //Test maxSize of name
        prefix.name = 'ThisIsAnExtremelyLongValueForAPrefix'
        assertFalse(prefix.validate())
        assertEquals('maxSize', prefix.errors['name'])
    }


    void testValidConstraints() {
        //Test valid prefix
		prefix.name = 'Mrs'
		assertTrue(prefix.validate())
    }
	
	void testToString() {
		Prefix prefix = new Prefix(name:'test');
		assertEquals('test', prefix.toString());
	}
	
}