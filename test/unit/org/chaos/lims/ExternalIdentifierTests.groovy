package org.chaos.lims

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ExternalIdentifier)
class ExternalIdentifierTests {

    ExternalIdentifier externalIdentifier
    Sample sample

    @Before
    void setup(){
        sample = new Sample(barcode: '111111')
        ExternalIdentifier existingExternalIdentifier = new ExternalIdentifier(name: 'outside', value: '123456', sample: sample)

        mockForConstraintsTests(ExternalIdentifier, [existingExternalIdentifier])

        externalIdentifier = new ExternalIdentifier()
    }

    void testNullConstraints(){
        assertFalse externalIdentifier.validate()
        assertEquals 3, externalIdentifier.errors.errorCount
        assertEquals 'nullable', externalIdentifier.errors['sample']
        assertEquals 'nullable', externalIdentifier.errors['name']
        assertEquals 'nullable', externalIdentifier.errors['value']
    }

    void testBlankConstraints(){
        externalIdentifier.sample = sample
        externalIdentifier.name = ''
        externalIdentifier.value = ''

        assertFalse externalIdentifier.validate()
        assertEquals 2, externalIdentifier.errors.errorCount
        assertEquals 'blank', externalIdentifier.errors['name']
        assertEquals 'blank', externalIdentifier.errors['value']
    }

    void testMaxSizeConstraints(){
        externalIdentifier.sample = sample
        externalIdentifier.name = 'Lorem ipsum dolor sit amet, consetetur sa'
        externalIdentifier.value = 'Lorem ipsum dolor sit amet, consetetur sa'

        assertFalse externalIdentifier.validate()
        assertEquals 2, externalIdentifier.errors.errorCount
        assertEquals 'maxSize', externalIdentifier.errors['name']
        assertEquals 'maxSize', externalIdentifier.errors['value']
    }

    void testUniqueConstraints(){
        externalIdentifier.sample = sample
        externalIdentifier.name = 'outside'
        externalIdentifier.value = '123456'

        assertFalse externalIdentifier.validate()
        assertEquals 1, externalIdentifier.errors.errorCount
        assertEquals 'unique', externalIdentifier.errors['name']
    }

    void testValidConstraints(){
        externalIdentifier.sample = sample
        externalIdentifier.name = 'somegroup'
        externalIdentifier.value = '123456'

        assertTrue externalIdentifier.validate()
    }

    void testToString(){
        externalIdentifier = new ExternalIdentifier(name: 'Outside', value: '999999')
        assertEquals 'Outside: 999999', externalIdentifier.toString()
    }

}