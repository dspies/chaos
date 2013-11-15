package org.chaos.storage

import groovy.transform.EqualsAndHashCode

/**
 * Used to represent the location of a {@link org.chaos.lims.Sample} or other lab material in a {@link org.chaos.storage.StorageContainer}
 *
 * @author David Spies
 */
@EqualsAndHashCode
class StoragePosition {

    StorageContainer storageContainer
    String position
    Date dateCreated
    Date lastUpdated

    static constraints = {
        position blank: false, maxSize: 10, unique: ['storageContainer']
    }

    String toString() {
        return position
    }
}
