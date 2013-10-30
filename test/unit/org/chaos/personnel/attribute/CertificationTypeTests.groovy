package org.chaos.personnel.attribute

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CertificationType)
class CertificationTypeTests {

    CertificationType certificationType

    @Before
    void setup(){
        CertificationType existingCertificationType = new CertificationType(name:'Sample Mgmt', description: 'Manage Samples')

        mockForConstraintsTests(CertificationType, [existingCertificationType])

        certificationType = new CertificationType()
    }

    void testNullConstraints(){
        assertFalse certificationType.validate()
        assertEquals 2, certificationType.errors.errorCount
        assertEquals 'nullable', certificationType.errors['name']
        assertEquals 'nullable', certificationType.errors['description']
    }

    void testBlankConstraints(){
        certificationType.name = ''
        certificationType.description = ''

        assertFalse certificationType.validate()
        assertEquals 2, certificationType.errors.errorCount
        assertEquals 'blank', certificationType.errors['name']
        assertEquals 'blank', certificationType.errors['description']
    }

    void testMaxConstraints(){
        certificationType.name = 'Lorem ipsum dolor sit amet, ex quodsi'
        certificationType.description = '''Lorem ipsum dolor sit amet, ex quodsi invenire comprehensam eos, noster
            delenit senserit no sed. Stet noster eos eu, qui soluta principes disputationi ne, sonet scripta tibique ex
            duo. Fuisset mentitum intellegebat in vim. Facete apeirian contentiones vel si'''

        assertFalse certificationType.validate()
        assertEquals 2, certificationType.errors.errorCount
        assertEquals 'maxSize', certificationType.errors['name']
        assertEquals 'maxSize', certificationType.errors['description']
    }

    void testUniqueConstraint(){
        certificationType.name = 'Sample Mgmt'
        certificationType.description = 'Some description'

        assertFalse certificationType.validate()
        assertEquals 1, certificationType.errors.errorCount
        assertEquals 'unique', certificationType.errors['name']
    }

    void testValidConstraints(){
        certificationType.name = 'Questionnaire Collection'
        certificationType.description = 'Collection of Questionnaire'

        assertTrue certificationType.validate()
    }

    void testToString(){
        certificationType.name = 'Questionnaire Collection'
        assertEquals 'Questionnaire Collection', certificationType.toString()
    }
}
