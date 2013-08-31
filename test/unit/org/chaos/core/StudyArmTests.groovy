package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(StudyArm)
class StudyArmTests {

    Study existingStudy
    StudyArm existingStudyArm
    StudyArm studyArm

    @Before
    void setup(){
        existingStudy = new Study(name:'duplicate');
        existingStudyArm = new StudyArm(name:'arm1', description:'first arm', study:existingStudy);

        mockForConstraintsTests(StudyArm, [existingStudyArm])

        //Test null values
        studyArm = new StudyArm();
    }

    void testNullConstraints(){
        assertFalse studyArm.validate()
        assertEquals "nullable", studyArm.errors['name']
        assertEquals "nullable", studyArm.errors['description']
        assertEquals "nullable", studyArm.errors['study']
    }

    void testBlankConstraints(){
        //Test for blank name and description
        studyArm.name = ''
        studyArm.description = ''
        studyArm.study = existingStudy

        assertFalse studyArm.validate()
        assertEquals "blank", studyArm.errors['name']
        assertEquals "blank", studyArm.errors['description']
    }

    void testMaxSizeConstraints(){
        //Test for maxSize name(25) and description(255)
        studyArm.name = 'Lorem ipsum dolor sit amet.'
        studyArm.description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eget sapien magna.
                Curabitur cursus lorem eget malesuada ornare. Duis sit amet felis eu sem consequat aliquet. Mauris
                suscipit euismod pretium. Curabitur pharetra eros id turpis semper metus."""
        studyArm.study = existingStudy

        assertFalse studyArm.validate()
        assertEquals "maxSize", studyArm.errors['name']
        assertEquals "maxSize", studyArm.errors['description']
    }

    void testUniqueConstraints(){
        //Test for duplicate values
        studyArm.name = 'arm1'
        studyArm.description = "Some Description"
        studyArm.study = existingStudy

        assertFalse studyArm.validate()
        assertEquals "unique", studyArm.errors['name']
    }

    void testValidConstraints() {
        //Test for good values
        studyArm.name = 'First Arm'
        studyArm.description = 'Some Description'
        studyArm.study = existingStudy

        assertTrue studyArm.validate()
    }
	
	void testToString() {
        StudyArm studyArm = new StudyArm(name:'Arm 1')
        assertEquals "Arm 1", studyArm.toString()
	}

}
