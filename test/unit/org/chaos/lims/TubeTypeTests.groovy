package org.chaos.lims

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TubeType)
class TubeTypeTests {

    TubeType tubeType

    @Before
    void setup(){
        TubeType existingTubeType = new TubeType(name: '2in Sterilized',
                description: '2in Sterilized tube with 2.25in lid',
                permitsLabel: true,
                maximumVolumeInMilliliters: 2500)

        mockForConstraintsTests(TubeType, [existingTubeType])

        tubeType = new TubeType()
    }

    void testNullConstraints(){
        assertFalse tubeType.validate()
        assertEquals 4, tubeType.errors.errorCount
        assertEquals 'nullable', tubeType.errors['name']
        assertEquals 'nullable', tubeType.errors['description']
        assertEquals 'nullable', tubeType.errors['permitsLabel']
        assertEquals 'nullable', tubeType.errors['maximumVolumeInMilliliters']
    }

    void testBlankConstraints(){
        tubeType.name = ''
        tubeType.description = ''
        tubeType.permitsLabel = true
        tubeType.maximumVolumeInMilliliters = 1D

        assertFalse tubeType.validate()
        assertEquals 2, tubeType.errors.errorCount
        assertEquals 'blank', tubeType.errors['name']
        assertEquals 'blank', tubeType.errors['description']
    }

    void testMinimumValueConstraints(){
        tubeType.name = '2in'
        tubeType.description = '2in tube'
        tubeType.permitsLabel = true
        tubeType.maximumVolumeInMilliliters = -1D

        assertFalse tubeType.validate()
        assertEquals 1, tubeType.errors.errorCount
        assertEquals 'min', tubeType.errors['maximumVolumeInMilliliters']
    }

    void testMinimumValueNotEqualConstraints(){
        tubeType.name = '2in'
        tubeType.description = '2in tube'
        tubeType.permitsLabel = true
        tubeType.maximumVolumeInMilliliters = 0D

        assertFalse tubeType.validate()
        assertEquals 1, tubeType.errors.errorCount
        assertEquals 'notEqual', tubeType.errors['maximumVolumeInMilliliters']
    }

    void testMaxSizeConstraints(){
        tubeType.name = 'Lorem ipsum dolor sit amet, con'
        tubeType.description = '''Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
            tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et
            justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata '''
        tubeType.permitsLabel = true
        tubeType.maximumVolumeInMilliliters = 1D

        assertFalse tubeType.validate()
        assertEquals 2, tubeType.errors.errorCount
        assertEquals 'maxSize', tubeType.errors['name']
        assertEquals 'maxSize', tubeType.errors['description']
    }

    void testUniqueConstraints(){
        tubeType.name = '2in Sterilized'
        tubeType.description = '2in Sterilized tube'
        tubeType.permitsLabel = true
        tubeType.maximumVolumeInMilliliters = 2500

        assertFalse tubeType.validate()
        assertEquals 1, tubeType.errors.errorCount
        assertEquals 'unique', tubeType.errors['name']
    }

    void testValidConstraints(){
        tubeType.name = '3in Sterilized'
        tubeType.description = '3in Sterilized tube'
        tubeType.permitsLabel = true
        tubeType.maximumVolumeInMilliliters = 2500

        assertTrue tubeType.validate()
    }

    void testToString(){
        tubeType.name = '2in Cryotube'
        assertEquals '2in Cryotube', tubeType.toString()
    }

}