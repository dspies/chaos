package org.chaos.lims

import groovy.transform.EqualsAndHashCode

import org.chaos.core.ParticipantType
import org.chaos.core.EventType

/**
 * Type of Material to collect at an Event.
 *
 * @author David Spies
 */
@EqualsAndHashCode
class EventTypeMaterial {

    EventType eventType
    Material material
    ParticipantType participantType
    Integer aliquotCount
    Long defaultVolumeInMilliliters
    Date dateCreated
    Date lastUpdated

    static constraints = {
        material unique:['eventType', 'participantType']
        aliquotCount min:1
        defaultVolumeInMilliliters min: 0L
    }

    String toString(){
        "${aliquotCount} aliquots at ${defaultVolumeInMilliliters}ul"
    }
}
