package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

@TestFor(Person)
class PersonTests {

    Person person

    @Before
    void setup(){
        mockForConstraintsTests Person

        //Test for null fields
        person = new Person()
    }

    void testNullConstraints(){
        assertFalse person.validate()
        assertEquals 'nullable', person.errors['firstName']
        assertEquals 'nullable', person.errors['lastName']
        assertEquals 'nullable', person.errors['middleName']
        assertEquals 'nullable', person.errors['maidenName']
        assertEquals 'nullable', person.errors['deceased']
        assertEquals 'nullable', person.errors['comments']
    }

    void testBlankConstraints(){
        //Test for blank fields
        person.firstName = ''
        person.lastName = ''
        person.middleName = ''
        person.preferredName = ''
        person.maidenName = ''
        person.comments = ''
        person.deceased = false

        assertFalse person.validate()
        assertEquals 'blank', person.errors['firstName']
        assertEquals 'blank', person.errors['lastName']    }

    void testMaxSizeConstraints(){
        //Test for maxSize of firstName(50), lastName(50), middleName(50), maidenName(50), comments(255)
        person.firstName = 'Lorem ipsum dolor sit amet, consectetur massa nunc.'
        person.lastName = 'Lorem ipsum dolor sit amet, consectetur massa nunc.'
        person.middleName = 'Lorem ipsum dolor sit amet, consectetur massa nunc.'
        person.maidenName = 'Lorem ipsum dolor sit amet, consectetur massa nunc.'
        person.comments = """Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vitae pharetra urna.
                    Fusce sem ante, tincidunt vitae velit nec, varius fringilla libero. Lorem ipsum dolor sit amet,
                    consectetur adipiscing elit. Maecenas quis enim nisl. Suspendisse massa nunc."""
        person.deceased = false

        assertFalse person.validate()
        assertEquals 'maxSize', person.errors['firstName']
        assertEquals 'maxSize', person.errors['lastName']
        assertEquals 'maxSize', person.errors['middleName']
        assertEquals 'maxSize', person.errors['maidenName']
        assertEquals 'maxSize', person.errors['comments']
    }

    void testMaxConstraints(){
        //Test for max of birthDate, equal or less than today
        person.firstName = 'Tom'
        person.lastName = 'Jones'
        person.preferredName = ''
        person.middleName = ''
        person.maidenName = ''
        person.comments = ''
        person.birthDate = new Date() + 1
        person.deceased = false

        assertFalse person.validate()
        assertEquals 'max', person.errors['birthDate']
    }

    void testValidConstraints() {
        //Test for good values
        person.firstName = 'Tom'
        person.lastName = 'Jones'
        person.preferredName = ''
        person.middleName = ''
        person.maidenName = ''
        person.comments = ''
        person.deceased = false
        assertTrue person.validate()
    }

    void testFullCommonName(){
        Person person = new Person(firstName:'Tom', lastName:'Smith', preferredName: 'Timothy')
        assertEquals "Timothy Smith", person.fullCommonName
    }

    void testFullLegalName(){
        Person person = new Person(firstName: 'Tom', lastName: 'Smith', preferredName: 'Timothy')
        assertEquals 'Tom Smith', person.fullLegalName
    }

    void testToString() {
        Person person = new Person(firstName: 'Tom', lastName: 'Smith', preferredName: 'Timothy')
        assertEquals 'Timothy Smith', person.toString()
    }

}