package org.chaos.lims

import grails.test.mixin.*
import org.chaos.core.EventType
import org.chaos.core.StudyArm
import org.chaos.core.StudyCenter
import org.chaos.core.StudyParticipant
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Sample)
class SampleTests {

    Sample sample
    StudyCenter studyCenter
    StudyParticipant studyParticipant
    StudyArm studyArm
    EventType eventType
    Material material
    TubeType tubeType

    @Before
    void setup(){

        studyCenter = new StudyCenter(name: 'study center', description: 'study center')
        studyParticipant = new StudyParticipant()
        studyArm = new StudyArm()
        eventType = new EventType()
        material = new Material()
        tubeType = new TubeType()

        Sample existingSample = new Sample(
                studyCenter: studyCenter,
                studyParticipant: studyParticipant,
                studyArm: studyArm,
                eventType: eventType,
                material: material,
                tubeType: tubeType,
                barcode: '123456',
                initialVolumeInMicroliters: 1000,
                volumeInMicroliters: 100,
                aliquot: 'a',
                labeled: true,
                usable: true,
                creationDate: new Date()
        )

        mockForConstraintsTests(Sample, [existingSample])

        sample = new Sample()
    }

    void populateSampleReferences(){
        sample.studyCenter = studyCenter
        sample.studyParticipant = studyParticipant
        sample.studyArm = studyArm
        sample.eventType = eventType
        sample.material = material
        sample.tubeType = tubeType
    }
    
    void testNullConstraints(){
        assertFalse sample.validate()
        assertEquals 11, sample.errors.errorCount
        assertEquals 'nullable', sample.errors['studyCenter']
        assertEquals 'nullable', sample.errors['studyParticipant']
        assertEquals 'nullable', sample.errors['studyArm']
        assertEquals 'nullable', sample.errors['eventType']
        assertEquals 'nullable', sample.errors['material']
        assertEquals 'nullable', sample.errors['tubeType']
        assertEquals 'nullable', sample.errors['barcode']
        assertEquals 'nullable', sample.errors['initialVolumeInMicroliters']
        assertEquals 'nullable', sample.errors['volumeInMicroliters']
        assertEquals 'nullable', sample.errors['aliquot']
        assertEquals 'nullable', sample.errors['creationDate']
    }

    void testBlankConstraints(){
        populateSampleReferences()
        sample.labeled = true
        sample.usable = true
        sample.creationDate = new Date()
        sample.initialVolumeInMicroliters = 100D
        sample.volumeInMicroliters = 100D

        sample.barcode = ''
        sample.aliquot = ''

        assertFalse sample.validate()
        assertEquals 2, sample.errors.errorCount
        assertEquals 'blank', sample.errors['barcode']
        assertEquals 'blank', sample.errors['aliquot']
    }

    void testMaxSizeConstraints(){
        populateSampleReferences()
        sample.labeled = true
        sample.usable = true

        sample.creationDate = new Date() + 1
        sample.barcode = '1234567890123456'
        sample.initialVolumeInMicroliters = 100000001D
        sample.volumeInMicroliters = 100000001D
        sample.aliquot = '123456789'

        assertFalse sample.validate()
        assertEquals 5, sample.errors.errorCount
        assertEquals 'max', sample.errors['creationDate']
        assertEquals 'maxSize', sample.errors['barcode']
        assertEquals 'max', sample.errors['initialVolumeInMicroliters']
        assertEquals 'max', sample.errors['volumeInMicroliters']
        assertEquals 'maxSize', sample.errors['aliquot']
    }

    void testMinSizeConstraints(){
        populateSampleReferences()
        sample.labeled = true
        sample.usable = true
        sample.creationDate = new Date()
        sample.barcode = '123457'
        sample.aliquot = 'a'

        sample.initialVolumeInMicroliters = 0.00009D
        sample.volumeInMicroliters = 0.00009D

        assertFalse sample.validate()
        assertEquals 2, sample.errors.errorCount
        assertEquals 'min', sample.errors['initialVolumeInMicroliters']
        assertEquals 'min', sample.errors['volumeInMicroliters']

    }

    void testUniqueConstraints(){
        populateSampleReferences()
        sample.labeled = true
        sample.usable = true
        sample.creationDate = new Date()
        sample.initialVolumeInMicroliters = 100D
        sample.volumeInMicroliters = 100D
        sample.aliquot = 'a'

        sample.barcode = '123456'

        assertFalse sample.validate()
        assertEquals 1, sample.errors.errorCount
        assertEquals 'unique', sample.errors['barcode']
    }

    void testScaleConstraint(){
        populateSampleReferences()
        sample.labeled = true
        sample.usable = true
        sample.creationDate = new Date()
        sample.aliquot = 'a'
        sample.barcode = '123454'

        sample.initialVolumeInMicroliters = 100.00009D
        sample.volumeInMicroliters = 100.00009D

        assertTrue sample.validate()

        assert 100.0001D == sample.initialVolumeInMicroliters
        assert 100.0001D == sample.volumeInMicroliters
    }

    void testToString(){
        sample.barcode = '123456'
        assertEquals '123456', sample.toString()
    }

}
