package org.chaos.storage


import groovy.transform.EqualsAndHashCode

/**
 * An instance of storage container type (freezer, stack, box, etc) used to store other
 * storage containers, samples, or other lab materials.  These instances can override
 * defaults provided by the storage container type they represent.
 *
 * @author David Spies
 */
@EqualsAndHashCode
class StorageContainer {

    String name
    StorageContainerType storageContainerType
    StorageContainer parentStorageContainer
    Integer rows
    Integer columns
    Integer temperatureInCelcius
    Date dateCreated
    Date lastUpdated

    static hasMany = [childStorageContainers:StorageContainer,
                      storagePositions:StoragePosition]

    static constraints = {
        name blank: false, maxSize: 15, unique: ['parentStorageContainer']
        rows min: 1
        columns min: 1
    }

    String toString() {
        return name
    }
}
