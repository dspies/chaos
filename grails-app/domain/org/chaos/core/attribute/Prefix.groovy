package org.chaos.core.attribute

import java.util.Date;

import groovy.transform.EqualsAndHashCode;

/**
 * Name prefix of an individual.
 * @author David Spies
 */
@EqualsAndHashCode
class Prefix {

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
