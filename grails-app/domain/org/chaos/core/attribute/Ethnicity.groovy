package org.chaos.core.attribute

import groovy.transform.EqualsAndHashCode
/**
 * Ethnicity of an individual.  Should correspond to the 
 * <a href="http://grants.nih.gov/grants/guide/notice-files/not-od-01-053.html">NIH's definition of ethnicity</a>
 * @author David Spies
 */
@EqualsAndHashCode
class Ethnicity {

	String name
	Date dateCreated
	Date lastUpdated

	static constraints = {
		name(blank:false, unique:true, maxSize:15)
	}
	
	String toString() {
		return name;
	}
}
