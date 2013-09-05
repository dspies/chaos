package org.chaos.core.attribute

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Language)
class LanguageTests {

    Language existingLanguage
    Language language

    @Before
    void setup(){
        existingLanguage = new Language(name:'English');

        mockForConstraintsTests(Language, [existingLanguage])

        language = new Language();
    }

    void testNullConstraints(){
        //Test for null values
        assertFalse(language.validate())
        assertEquals('nullable', language.errors['name'])
    }

    void testBlankConstraints(){
        //Test for blank values
        language.name = ''
        assertFalse(language.validate())
        assertEquals('blank', language.errors['name'])
    }

    void testUniqueConstraints(){
        //Test for unique
        language.name = 'English'
        assertFalse(language.validate())
        assertEquals('unique', language.errors['name'])
    }

    void testMaxSizeConstraints(){
        //Test maxSize
        language.name = 'NoLanguageIsThisLongSoUnlessThereIsANewOneThisIsRidiculous'
        assertFalse(language.validate())
        assertEquals('maxSize', language.errors['name'])
    }

    void testValidConstraints() {
		//Test valid values
		language.name = 'Spanish'
        assertTrue language.validate()
    }
	
	void testToString() {
		Language language = new Language(name:'English');
		assertEquals('English', language.toString())
	}
}
