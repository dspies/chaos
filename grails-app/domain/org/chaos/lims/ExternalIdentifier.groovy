package org.chaos.lims

import groovy.transform.EqualsAndHashCode

/**
 * An alternative value, generally a barcode, associated with a Sample from an outside source
 *
 * @David Spies
 */
@EqualsAndHashCode
class ExternalIdentifier {

    Sample sample
    String name
    String value
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, maxSize: 40, unique:['sample', 'value']
        value blank: false, maxSize: 40
    }

    String toString() {
        return "${name}: ${value}"
    }
}
