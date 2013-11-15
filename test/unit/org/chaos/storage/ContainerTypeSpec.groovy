package org.chaos.storage

import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ContainerType)
@Unroll
class ContainerTypeSpec extends Specification {

    void "Name constraint - #errorMessage"(){
        setup:
            ContainerType existingContainerType = new ContainerType(name:'box1', description: 'Some description')
            mockForConstraintsTests(ContainerType, [existingContainerType])

        when:
            def containerType = new ContainerType(name: name, description: "Simple Description")

        then:
            containerType.validate() == valid
            containerType.errors['name'] == errorMessage

        where:
            name                              | valid | errorMessage
            null                              | false | 'nullable'  //Cannot be nullable
            ""                                | false | 'blank'     //Cannot be blank
            "1234567890123456789012345678901" | false | 'maxSize'   //MaxSize 30
            "box1"                            | false | 'unique'    //cannot duplicate name
            "123456789012345678901234567890"  | true  | null        //valid
    }

    void "Description constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(ContainerType)

        when:
            def containerType = new ContainerType(name: 'test', description: description)

        then:
            containerType.validate() == valid
            containerType.errors['description'] == errorMessage

        where:
            description       | valid | errorMessage
            null              | false | 'nullable'  //Cannot be nullable
            ""                | false | 'blank'     //Cannot be blank
            "x".multiply(256) | false | 'maxSize'   //MaxSize 255
            "x".multiply(255) | true  | null        //valid
    }

    void "toString prints name"(){
        when:
            ContainerType containerType = new ContainerType(name: 'Example')

        then:
            containerType.toString() == "Example"
    }
}
