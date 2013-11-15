package org.chaos.storage

import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(StorageContainerType)
@Unroll
class StorageContainerTypeSpec extends Specification {

    static def validContainerType = new ContainerType(name: 'default')

    void "Name constraint - #errorMessage"() {
        setup:
            StorageContainerType existingStorageContainerType = new StorageContainerType(
                    name: '12in Rack'
            )
            mockForConstraintsTests(StorageContainerType, [existingStorageContainerType])

        when:
            def storageContainerType = new StorageContainerType(
                    name: name,
                    description: 'Description',
                    defaultRows: 1,
                    defaultColumns: 1,
                    defaultTemperatureInCelcius: -20,
                    containerType: validContainerType
            )

        then:
            storageContainerType.validate() == valid
            storageContainerType.errors['name'] == errorMessage

        where:
            name             | valid | errorMessage
            null             | false | 'nullable'   //Cannot be null
            ''               | false | 'blank'      //Cannot be blank
            '12in Rack'      | false | 'unique'     //Must be unique
            'x'.multiply(41) | false | 'maxSize'    //Cannot be longer than 40 characters
            'new'            | true  | null         //Valid
    }

    void "Description constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainerType)

        when:
            def storageContainerType = new StorageContainerType(
                name: 'name',
                description: description,
                defaultRows: 1,
                defaultColumns: 1,
                defaultTemperatureInCelcius: -20,
                containerType: validContainerType
            )

        then:
            storageContainerType.validate() == valid
            storageContainerType.errors['description'] == errorMessage

        where:
            description       | valid | errorMessage
            null              | false | 'nullable'  //Cannot be null
            ''                | false | 'blank'     //Cannot be blank
            'x'.multiply(256) | false | 'maxSize'   //Cannot exceed 255 characters
            'x'.multiply(255) | true  | null        //Valid
    }

    void "DefaultRows constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainerType)

        when:
            def storageContainerType = new StorageContainerType(
                    name: 'name',
                    description: 'description',
                    defaultRows: defaultRows,
                    defaultColumns: 1,
                    defaultTemperatureInCelcius: -20,
                    containerType: validContainerType
            )

        then:
            storageContainerType.validate() == valid
            storageContainerType.errors['defaultRows'] == errorMessage

        where:
            defaultRows | valid | errorMessage
            null        | false | 'nullable'    //Cannot be null
            0           | false | 'min'         //Cannot be less than 1
            1           | true  | null          //Valid
    }

    void "DefaultColumns constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainerType)

        when:
            def storageContainerType = new StorageContainerType(
                name: 'name',
                description: 'description',
                defaultRows: 1,
                defaultColumns: defaultColumns,
                defaultTemperatureInCelcius: -20,
                containerType: validContainerType
            )

        then:
            storageContainerType.validate() == valid
            storageContainerType.errors['defaultColumns'] == errorMessage

        where:
            defaultColumns | valid | errorMessage
            null           | false | 'nullable'    //Cannot be null
            0              | false | 'min'         //Cannot be less than 1
            1              | true  | null          //Valid
    }

    void "DefaultTemperatureInCelcius constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainerType)

        when:
            def storageContainerType = new StorageContainerType(
                name: 'name',
                description: 'description',
                defaultRows: 1,
                defaultColumns: 1,
                defaultTemperatureInCelcius: defaultTemperature,
                containerType: validContainerType
            )

        then:
            storageContainerType.validate() == valid
            storageContainerType.errors['defaultTemperatureInCelcius'] == errorMessage

        where:
            defaultTemperature | valid | errorMessage
            null               | false | 'nullable'    //Cannot be null
            -20                | true  | null          //Valid
    }

    void "ContainerType constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainerType)

        when:
            def storageContainerType = new StorageContainerType(
                name: 'name',
                description: 'description',
                defaultRows: 1,
                defaultColumns: 1,
                defaultTemperatureInCelcius: -20,
                containerType: containerType
            )

        then:
            storageContainerType.validate() == valid
            storageContainerType.errors['containerType'] == errorMessage

        where:
            containerType      | valid | errorMessage
            null               | false | 'nullable'    //Cannot be null
            validContainerType | true  | null          //Valid
    }

    void "toString returns name"(){
        when:
            StorageContainerType storageContainerType = new StorageContainerType(
                    name: '12in Rack'
            )

        then:
            storageContainerType.toString() == '12in Rack'
    }
}
