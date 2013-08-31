package org.chaos.core.attribute

import java.util.Date;

import groovy.transform.EqualsAndHashCode;

/**
 * Race of an individual.  Should correspond to the 
 * <a href="http://grants.nih.gov/grants/guide/notice-files/not-od-01-053.html">NIH's definition of ethnicity</a>
 * @author David Spies
 */
@EqualsAndHashCode
class Race {

	String name
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name(blank:false, maxSize:15, unique:true)
    }
	
	String toString(){
		return name
	}
}