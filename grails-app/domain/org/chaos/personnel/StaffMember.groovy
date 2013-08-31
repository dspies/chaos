package org.chaos.personnel

import org.chaos.core.attribute.Ethnicity;
import org.chaos.core.attribute.Race;
import org.chaos.core.attribute.Sex;

import org.chaos.personnel.attribute.Title;

import groovy.transform.EqualsAndHashCode

/**
 * Individuals involved in the execution of one or more populations/studies
 * @author David Spies
 */
@EqualsAndHashCode
class StaffMember {


    String firstName
    String lastName
    String preferredName
    Date birthDate
    Date hireDate
    /**
     * Date the staff member began participating in the populations.
     * This can be the same as the hire date
     * */
    Date startDate
    /** Date the staff member retired, quit, or terminated */
    Date endDate
    Ethnicity ethnicity
    Race race
    Sex sex
    Title title
    String generalComments
    Date dateCreated
    Date lastUpdated

    static transients = [ "fullName" ]

    static constraints = {
        firstName(blank:false, maxSize:40)
        lastName(blank:false, maxSize:40)
        preferredName(blank:true, nullable:false, maxSize:40)
        birthDate(nullable:true)
        hireDate(nullable:true)
        startDate(nullable:true)
        endDate(nullable:true)
        ethnicity(nullable:true)
        race(nullable:true)
        sex(nullable:true)
        title(nullable:true)
        generalComments(blank:true, nullable:false, maxSize:255)
    }

    String getFullName(){
        return (preferredName ?: firstName ) + " " + lastName
    }

    String toString(){
        return fullName
    }
}