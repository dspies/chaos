package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

@TestFor(PopulationParticipant)
class PopulationParticipantTests {

    //Population used in all tests
    Population population

    //Dependencies for the existing domain objects
    PopulationParticipant existingPopulationParticipant
    Person existingPerson

    //Dependencies for new domain objects
    PopulationParticipant populationParticipant
    Person person

    @Before
    void setup() {
        population = new Population(name: 'population', description:'')
        existingPerson = new Person(firstName:'Person', lastName:'One')
        existingPopulationParticipant = new PopulationParticipant(
                population:population,
                person:existingPerson,
                populationParticipantId:'100101')

        person = new Person(firstName: 'Person', lastName: 'Two')

        mockForConstraintsTests(PopulationParticipant, [existingPopulationParticipant])

        populationParticipant = new PopulationParticipant()
    }

    void populatePersonAndPopulation(){
        populationParticipant.person = person
        populationParticipant.population = population
    }

    void testNullConstraints(){
        //Test null constraints
        assertFalse populationParticipant.validate()
        assertEquals 3, populationParticipant.errors.errorCount
        assertEquals 'nullable', populationParticipant.errors['population']
        assertEquals 'nullable', populationParticipant.errors['person']
        assertEquals 'nullable', populationParticipant.errors['populationParticipantId']
    }

    void testBlankConstraints(){
        //Test blank constraints
        populatePersonAndPopulation()
        populationParticipant.populationParticipantId = ''

        assertFalse populationParticipant.validate()
        assertEquals 1, populationParticipant.errors.errorCount
        assertEquals 'blank', populationParticipant.errors['populationParticipantId']
    }

    void testMaxSizeConstraints(){
        //Test maxSize populationParticipantId(20)
        populatePersonAndPopulation()
        populationParticipant.populationParticipantId = 'Lorem ipsum dolor sit amet, con'
        assertFalse populationParticipant.validate()
        assertEquals 1, populationParticipant.errors.errorCount
        assertEquals 'maxSize', populationParticipant.errors['populationParticipantId']
    }

    void testUniquePersonAndPopulationConstraints(){
        populationParticipant.population = population
        populationParticipant.person = existingPerson
        populationParticipant.populationParticipantId = '100201'

        assertFalse populationParticipant.validate()
        assertEquals 1, populationParticipant.errors.errorCount
        assertEquals 'unique', populationParticipant.errors['person']
    }

    void testUniqueParticipantIdAndPopulation(){
        populatePersonAndPopulation()
        populationParticipant.populationParticipantId = '100101'

        assertFalse populationParticipant.validate()
        assertEquals 1, populationParticipant.errors.errorCount
        assertEquals 'unique', populationParticipant.errors['populationParticipantId']
    }

    void testValidConstraints(){
        populatePersonAndPopulation()
        populationParticipant.populationParticipantId = '100201'

        assertTrue populationParticipant.validate()
    }

    void testToString(){
        PopulationParticipant populationParticipant = new PopulationParticipant(population:population,
            person:person, populationParticipantId:'100101')

        assertEquals '100101', populationParticipant.toString()
    }
}