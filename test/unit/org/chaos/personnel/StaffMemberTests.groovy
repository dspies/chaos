package org.chaos.personnel

import grails.test.mixin.TestFor
import org.junit.Before

@TestFor(StaffMember)
class StaffMemberTests {

    StaffMember staff

    @Before
    void setup(){
        mockForConstraintsTests(StaffMember)

        //Test for null names and comments
        staff = new StaffMember()
    }

    void testNullConstraints(){
        assertFalse staff.validate()
        assertEquals "nullable", staff.errors['firstName']
        assertEquals "nullable", staff.errors['lastName']
        assertEquals "nullable", staff.errors['generalComments']
    }

    void testBlankConstraints(){
        //Test for blank first and last names
        staff.firstName = ''
        staff.lastName = ''
        staff.preferredName = ''
        staff.generalComments = ''
        assertFalse staff.validate()
        assertEquals "blank", staff.errors['firstName']
        assertEquals "blank", staff.errors['lastName']
    }

    void testMaxSizeConstraints(){
        //Test for maxSize on firstName, lastName, preferredName, and comments
        staff.firstName = 'Longer Than Forty Characters In Length So Fail'
        staff.lastName = 'Longer Than Forty Characters In Length So Fail'
        staff.preferredName = 'Longer Than Forty Characters In Length So Fail'
        staff.generalComments = '''Longer Than Two hundred and fifty five characters so fail
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel
				cursus est, id bibendum dui. Mauris varius nulla metus, in posuere
				orci ullamcorper in. Fusce sed justo elit. Quisque est ipsum, varius
				eu tincidunt nec, eleifend ac felis. Maecenas metus'''
        assertFalse staff.validate()
        assertEquals 'maxSize', staff.errors['firstName']
        assertEquals 'maxSize', staff.errors['lastName']
        assertEquals 'maxSize', staff.errors['preferredName']
        assertEquals 'maxSize', staff.errors['generalComments']
    }

    void testValidConstraints() {
        //Test valid staff Member
        staff.firstName = 'Test'
        staff.lastName = 'Person'
        staff.preferredName = ''
        staff.generalComments = ''
        assertTrue staff.errors.collect().toString(), staff.validate()
    }

    void testFullNameWithFirstName() {
        staff.firstName = 'John'
        staff.lastName = 'Doe'
        assertEquals 'John Doe', staff.fullName
    }

    void testFullNameWithPreferredName() {
        staff.firstName = 'John'
        staff.lastName = 'Doe'
        staff.preferredName = 'Joe'
        assertEquals 'Joe Doe', staff.fullName
    }

    void testToString(){
        staff.firstName = 'John'
        staff.lastName = 'Doe'
        assertEquals 'John Doe', staff.toString()
    }
}