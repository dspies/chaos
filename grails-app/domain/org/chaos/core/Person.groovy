package org.chaos.core

import groovy.transform.EqualsAndHashCode
import org.chaos.core.attribute.*

/**
 * Individuals related to a population.  These individuals
 * may or may not be participants in the population.
 * @author David Spies
 */
@EqualsAndHashCode
class Person {

    static transients = ['fullCommonName', 'fullLegalName']

    Prefix prefix
    String firstName
    String preferredName
    String middleName
    String lastName
    String maidenName
    Suffix suffix
    Date birthDate
    MaritalStatus maritalStatus
    Race race
    Ethnicity ethnicity
    Sex sex
    Boolean deceased
    String comments
    Person biologicalMother
    Person biologicalFather
    Date dateCreated
    Date lastUpdated

    static hasMany = [languages:Language]

    static constraints = {
        prefix nullable: true
        firstName blank:false, maxSize: 50
        lastName blank:false, maxSize: 50
        middleName maxSize: 50
        maidenName maxSize: 50
        suffix nullable: true
        maritalStatus nullable: true
        race nullable: true
        ethnicity nullable: true
        sex nullable: true
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