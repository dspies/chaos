package org.chaos.core

import grails.test.mixin.TestFor
import org.junit.Before

/**
 * @author dspies
 */
@TestFor(StudyCenter)
class StudyCenterTests {

    StudyCenter existing
    StudyCenter sc

    @Before
    void setup(){
        existing = new StudyCenter(name:'AZ');

        mockForConstraintsTests(StudyCenter, [existing])

        sc = new StudyCenter()
    }


    void testNullConstraints(){
        //test for null study center
        assertFalse sc.validate()
        assertEquals "nullable", sc.errors['name']
        assertEquals "nullable", sc.errors['description']
    }

    void testBlankConstraints(){
        //Test for blank study center
        sc.name = ''
        sc.description = ''

        assertFalse sc.validate()
        assertEquals "blank", sc.errors['name']
        assertEquals "blank", sc.errors['description']
    }

    void testMaxSizeConstraints(){
        //Test for +30char study center
        sc.name = "ReallyReallyReallyReallyReallyLong"
        sc.description = """Longer Than 255 characters. Cupcake ipsum dolor
				sit. Amet sweet roll wafer dessert I love I love wypas chocolate cake cupcake. Jelly
				beans I love liquorice tiramisu marshmallow liquorice cupcake marzipan. Jelly beans
				donut bear claw powder. Apple pie jelly beans wypas danish chocolate. Bear claw muffin
				cotton candy topping."""

        assertFalse sc.validate()
        assertEquals "maxSize", sc.errors['name']
        assertEquals "maxSize", sc.errors['description']
    }

    void testUniqueConstraints(){
        //Test for duplicate study center
        sc.name = 'AZ'

        assertFalse sc.validate()
        assertEquals "unique", sc.errors['name']
    }

    void testValidConstraints() {
		//Test for valid study center
		sc.name = 'MO'
        sc.description = 'Missouri'

		assertTrue sc.validate()
    }
	
	void testToString(){
		StudyCenter sc = new StudyCenter(name:'AZ')
		assertEquals "AZ", sc.toString()
	}
}