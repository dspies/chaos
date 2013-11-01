package org.chaos.lims

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(SampleRelationship)
class SampleRelationshipTests {

    SampleRelationship sampleRelationship
    Sample parent
    Sample child

    @Before
    void setup(){
        parent = new Sample(id: 1, barcode: '123456')
        child = new Sample(id: 2, barcode: '123457')

        SampleRelationship existingSampleRelationship = new SampleRelationship(
                parentSample: parent,
                childSample: child)

        mockForConstraintsTests(SampleRelationship, [existingSampleRelationship])

        sampleRelationship = new SampleRelationship()
    }

    void testNullConstraints(){
        assertFalse sampleRelationship.validate()
        assertEquals 2, sampleRelationship.errors.errorCount
        assertEquals 'nullable', sampleRelationship.errors['parentSample']
        assertEquals 'nullable', sampleRelationship.errors['childSample']
    }

    void testUniqueConstraints(){
        sampleRelationship.childSample = child
        sampleRelationship.parentSample = parent

        assertFalse sampleRelationship.validate()
        assertEquals 1, sampleRelationship.errors.errorCount
        assertEquals 'unique', sampleRelationship.errors['parentSample']
    }

    void testToString(){
        sampleRelationship = new SampleRelationship()
        assertEquals 'Sample Relationship', sampleRelationship.toString()
    }

}