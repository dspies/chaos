package org.chaos.personnel.attribute

import grails.test.mixin.TestFor
import org.junit.Before

@TestFor(Title)
class TitleTests {

    Title existing
    Title title

    @Before
    void setup(){
        //Create existing Title
        existing = new Title(name:'Existing')

        mockForConstraintsTests(Title, [existing])

        //Test for null title
        title = new Title()
    }

    void testNullConstraints(){
        assertFalse title.validate()
        assertEquals "nullable", title.errors['name']
    }

    void testBlankConstraints(){
        //Test for blank title
        title.name = ''
        assertFalse title.validate()
        assertEquals "blank", title.errors['name']
    }

    void testUniqueConstraints(){
        //Test for unique name
        title.name = 'Existing'
        assertFalse title.validate()
        assertEquals "unique", title.errors['name']
    }

    void testMaxSizeConstraints(){
        //Test for title name greater than 25
        title.name = 'ThisNameIsLongerThanTwentyFiveCharacters'
        assertFalse title.validate()
        assertEquals "maxSize", title.errors['name']
    }

    void testValidConstraints() {
        //Test for valid title
        title.name = 'Director'
        assertTrue title.validate()
    }

    void testToString() {
        Title title = new Title(name:"Director")
        assertEquals "Director", title.toString()
    }

}