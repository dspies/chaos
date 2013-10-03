package org.chaos.lims

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Material)
class MaterialTests {

    Material material

    @Before
    void setup(){
        Material existingMaterial = new Material(name: 'Heparinized Whole Blood', description: 'Whole Blood with Heparin')

        mockForConstraintsTests(Material, [existingMaterial])

        material = new Material()
    }

    void testNullConstraints(){
        assertFalse material.validate()
        assertEquals 2, material.errors.errorCount
        assertEquals "nullable", material.errors['name']
        assertEquals "nullable", material.errors['description']
    }

    void testBlankConstraints(){
        material.name = ''
        material.description = ''

        assertFalse material.validate()
        assertEquals 2, material.errors.errorCount
        assertEquals 'blank', material.errors['name']
        assertEquals 'blank', material.errors['description']
    }

    void testMaxSizeConstraints(){
        material.name = 'Lorem ipsum dolor sit amet, consetet'
        material.description = 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata '

        assertFalse material.validate()
        assertEquals 2, material.errors.errorCount
        assertEquals 'maxSize', material.errors['name']
        assertEquals 'maxSize', material.errors['description']
    }

    void testUniqueConstraints(){
        material.name = 'Heparinized Whole Blood'
        material.description = 'Whole Blood'

        assertFalse material.validate()
        assertEquals 1, material.errors.errorCount
        assertEquals 'unique', material.errors['name']
    }

    void testValidConstraints(){
        material.name = 'EBC'
        material.description = 'Exhaled Breath Condensate'

        assertTrue material.validate()
    }

    void testToString(){
        material.name = 'Sputum'

        assertEquals 'Sputum', material.toString()
    }
}