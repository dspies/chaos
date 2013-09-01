package org.chaos.core

import groovy.transform.EqualsAndHashCode

/**
 * Individuals related to a population.  These individuals
 * may or may not be participants in the population.
 * @author David Spies
 */
@EqualsAndHashCode
class Person {

    static transients = ['fullCommonName', 'fullLegalName']

    String firstName
    String preferredName
    String middleName
    String lastName
    String maidenName
    Date birthDate
    Boolean deceased
    String comments
    Person biologicalMother
    Person biologicalFather
    Date dateCreated
    Date lastUpdated

    static constraints = {
        firstName blank:false, maxSize: 50
        lastName blank:false, maxSize: 50
        middleName maxSize: 50
        maidenName maxSize: 50
        comments maxSize: 255
        birthDate nullable:true, max: new Date()
        biologicalFather nullable:true
        biologicalMother nullable:true
    }

    /**
     * Returns the preferred name if it exists, and the last name.  If the preferred name does not exist,
     * it defaults to the first and last name
     */
    String getFullCommonName(){
        return "${(preferredName ?: firstName)} ${lastName}"
    }

    /**
     * Returns the first and last name, regardless of whether the person has a preferred name
     */
    String getFullLegalName(){
        return "${firstName} ${lastName}"
    }

    String toString(){
        return this.fullCommonName
    }
}