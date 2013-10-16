package org.chaos.lims

import org.chaos.core.*
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(EventTypeMaterial)
class EventTypeMaterialTests {

    EventType eventType
    EventType existingEventType
    Material material
    ParticipantType participantType
    EventTypeMaterial existingEventTypeMaterial
    EventTypeMaterial eventTypeMaterial

    @Before
    void setup(){
        eventType = new EventType(name:'event type 2')
        existingEventType = new EventType(name: 'event type 1')
        material = new Material(name:'serum')
        participantType = new ParticipantType(name:'Index')

        existingEventTypeMaterial = new EventTypeMaterial(
            eventType: existingEventType,
            material: material,
            participantType: participantType
        )

        mockForConstraintsTests(EventTypeMaterial, [existingEventTypeMaterial])

        eventTypeMaterial = new EventTypeMaterial()
    }

    void testNullConstraints(){
        assertFalse eventTypeMaterial.validate()

        assertEquals 5, eventTypeMaterial.errors.errorCount
        assertEquals 'nullable', eventTypeMaterial.errors['eventType']
        assertEquals 'nullable', eventTypeMaterial.errors['material']
        assertEquals 'nullable', eventTypeMaterial.errors['participantType']
        assertEquals 'nullable', eventTypeMaterial.errors['aliquotCount']
        assertEquals 'nullable', eventTypeMaterial.errors['defaultVolumeInMilliliters']
    }

    void testMinimumConstraints(){
        eventTypeMaterial.eventType = eventType
        eventTypeMaterial.material = material
        eventTypeMaterial.participantType = participantType
        eventTypeMaterial.aliquotCount = 0
        eventTypeMaterial.defaultVolumeInMilliliters = -1L

        assertFalse eventTypeMaterial.validate()
        assertEquals 2, eventTypeMaterial.errors.errorCount
        assertEquals 'min', eventTypeMaterial.errors['aliquotCount']
        assertEquals 'min', eventTypeMaterial.errors['defaultVolumeInMilliliters']
    }

    void testUniqueConstraints(){
        eventTypeMaterial.eventType = existingEventType
        eventTypeMaterial.material = material
        eventTypeMaterial.participantType = participantType
        eventTypeMaterial.aliquotCount = 1
        eventTypeMaterial.defaultVolumeInMilliliters = 1L

        assertFalse eventTypeMaterial.validate()
        assertEquals 1, eventTypeMaterial.errors.errorCount
        assertEquals 'unique', eventTypeMaterial.errors['material']
    }

    void testValidConstraints(){

        eventTypeMaterial.eventType = eventType
        eventTypeMaterial.material = material
        eventTypeMaterial.participantType = participantType
        eventTypeMaterial.aliquotCount = 1
        eventTypeMaterial.defaultVolumeInMilliliters = 1L

        assertTrue eventTypeMaterial.validate()
    }

    void testToString(){
        eventTypeMaterial = new EventTypeMaterial(aliquotCount:2, defaultVolumeInMilliliters: 1000)
        assertEquals "2 aliquots at 1000ul", eventTypeMaterial.toString()
    }
}
