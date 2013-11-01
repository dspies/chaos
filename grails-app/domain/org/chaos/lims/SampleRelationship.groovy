package org.chaos.lims

import groovy.transform.EqualsAndHashCode

/**
 * Provides basic information about the relationship between two samples.
 *
 * @David Spies
 */
@EqualsAndHashCode
class SampleRelationship {

    Sample parentSample
    Sample childSample
    Date dateCreated
    Date lastUpdated

    /**
     * Possible Enum?
     * relationship type - aliquot, derived
     *
     * getChildSamples
     * getParentSamples
     * create(parentSample, childSample)
     * get(parentSampleId, childSampleId)
     *
     * mapping = {
     *   id composite: ['parent', 'child']
     *   version false
     * }
     *
     */

    static constraints = {
        parentSample unique: ['childSample']
    }

    String toString() {
        return 'Sample Relationship'
    }
}