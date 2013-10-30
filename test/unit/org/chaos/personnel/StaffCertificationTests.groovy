package org.chaos.personnel

import grails.test.mixin.*
import org.chaos.personnel.attribute.CertificationType
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(StaffCertification)
class StaffCertificationTests {

    StaffCertification staffCertification
    StaffMember staffMember
    CertificationType certificationType

    @Before
    void setup(){
        staffMember = new StaffMember(firstName: 'Tom')
        certificationType = new CertificationType(name:'Test', description:'test')

        mockForConstraintsTests(StaffCertification, [])

        staffCertification = new StaffCertification()
    }

    void testNullConstraints(){
        assertFalse staffCertification.validate()
        assertEquals 4, staffCertification.errors.errorCount
        assertEquals 'nullable', staffCertification.errors['staffMember']
        assertEquals 'nullable', staffCertification.errors['certificationType']
        assertEquals 'nullable', staffCertification.errors['description']
        assertEquals 'nullable', staffCertification.errors['certificationDate']
    }

    void loadDefaultInstances(){
        staffCertification.staffMember = staffMember
        staffCertification.certificationType = certificationType
        staffCertification.certificationDate = new Date()
    }

    void testMaxConstraints(){
        loadDefaultInstances()

        staffCertification.description = '''Lorem ipsum dolor sit amet, ex quodsi invenire comprehensam eos, noster
            delenit senserit no sed. Stet noster eos eu, qui soluta principes disputationi ne, sonet scripta tibique ex
            duo. Fuisset mentitum intellegebat in vim. Facete apeirian contentiones vel si'''
        assertFalse staffCertification.validate()
        assertEquals 1, staffCertification.errors.errorCount
        assertEquals 'maxSize', staffCertification.errors['description']
    }

    void testValidConstraints(){
        loadDefaultInstances()

        staffCertification.description = ''
        assertTrue staffCertification.validate()
    }

}
