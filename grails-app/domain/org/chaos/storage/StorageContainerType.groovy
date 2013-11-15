package org.chaos.storage

import groovy.transform.EqualsAndHashCode
import org.chaos.lims.Material

/**
 * Used to maintain the common or default properties of {@link org.chaos.storage.StorageContainer}, such as dimensions,
 * generic container type, and temperature
 *
 * @author David Spies
 */
@EqualsAndHashCode
class StorageContainerType {

    String name
    String description
    Integer defaultRows
    Integer defaultColumns
    Integer defaultTemperatureInCelcius
    ContainerType containerType
    Date dateCreated
    Date lastUpdated

    static hasMany = [
            allowedChildStorageContainerTypes:StorageContainerType,
            allowedMaterials:Material
    ]

    static constraints = {
        name blank: false, maxSize: 40, unique: true
        description blank: false, maxSize: 255
        defaultRows min: 1
        defaultColumns min: 1

    }

    String toString(){
        return name
    }
}
