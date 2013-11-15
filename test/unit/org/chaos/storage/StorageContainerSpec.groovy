package org.chaos.storage

import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(StorageContainer)
@Unroll
class StorageContainerSpec extends Specification {

    static def validExistingParentStorageContainer = new StorageContainer(name: 'Freezer 1')
    static def validParentStorageContainer = new StorageContainer(name: 'Freezer 2')
    static def validStorageContainerType = new StorageContainerType(name: '12x1 stack')

    void "Name constraint - #errorMessage"() {
        setup:

            StorageContainer existingStorageContainer = new StorageContainer (
                    name: 'Stack 1',
                    parentStorageContainer: validExistingParentStorageContainer
            )
            mockForConstraintsTests(StorageContainer, [existingStorageContainer])

        when:
            def storageContainer = new StorageContainer (
                name: name,
                storageContainerType: validStorageContainerType,
                parentStorageContainer: parentStorageContainer,
                rows: 1,
                columns: 1,
                temperatureInCelcius: -20
            )

        then:
            storageContainer.validate() == valid
            storageContainer.errors['name'] == errorMessage

        where:
            name             | parentStorageContainer              | valid | errorMessage
            null             | null                                | false | 'nullable' //Cannot be null
            ''               | null                                | false | 'blank'    //Cannot be blank
            'x'.multiply(16) | null                                | false | 'maxSize'  //Cannot be longer than 15 characters
            'Stack 1'        | validExistingParentStorageContainer | false | 'unique'   //Must be unique
            'Stack 1'        | validParentStorageContainer         | true  | null   //Valid because it resides under a different parent
            'Stack 1'        | null                                | true  | null   //Valid, no parent
            'x'.multiply(15) | null                                | true  | null   //Valid
    }

    void "StorageContainerType constraint - #errorMessage"() {
        setup:
            mockForConstraintsTests(StorageContainer, [])

        when:
            def storageContainer = new StorageContainer (
                name: 'New',
                storageContainerType: storageContainerType,
                parentStorageContainer: null,
                rows: 1,
                columns: 1,
                temperatureInCelcius: -20
        )

        then:
            storageContainer.validate() == valid
            storageContainer.errors['storageContainerType'] == errorMessage

        where:
            storageContainerType      | valid | errorMessage
            null                      | false | 'nullable' //Cannot be null
            validStorageContainerType | true  | null   //Valid
    }

    void "ParentStorageContainer constraint - #errorMessage"() {
        setup:
            mockForConstraintsTests(StorageContainer, [])

        when:
            def storageContainer = new StorageContainer (
                name: 'New',
                storageContainerType: validStorageContainerType,
                parentStorageContainer: parentStorageContainer,
                rows: 1,
                columns: 1,
                temperatureInCelcius: -20
            )

        then:
            storageContainer.validate() == valid
            storageContainer.errors['parentStorageContainer'] == errorMessage

        where:
            parentStorageContainer      | valid | errorMessage
            null                        | true  | null  //Valid without parent
            validParentStorageContainer | true  | null  //Valid with parent
    }

    void "Rows constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainer)

        when:
            def storageContainer = new StorageContainer (
                name: 'New',
                storageContainerType: validStorageContainerType,
                parentStorageContainer: null,
                rows: rows,
                columns: 1,
                temperatureInCelcius: -20
            )

        then:
            storageContainer.validate() == valid
            storageContainer.errors['rows'] == errorMessage

        where:
            rows | valid | errorMessage
            null | false | 'nullable'    //Cannot be null
            0    | false | 'min'         //Cannot be less than 1
            1    | true  | null          //Valid
    }

    void "Columns constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainer)

        when:
            def storageContainer = new StorageContainer (
                    name: 'New',
                    storageContainerType: validStorageContainerType,
                    parentStorageContainer: null,
                    rows: 1,
                    columns: columns,
                    temperatureInCelcius: -20
            )

        then:
            storageContainer.validate() == valid
            storageContainer.errors['columns'] == errorMessage

        where:
            columns | valid | errorMessage
            null    | false | 'nullable'    //Cannot be null
            0       | false | 'min'         //Cannot be less than 1
            1       | true  | null          //Valid
    }

    void "TemperatureInCelcius constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StorageContainer)

        when:
            def storageContainer = new StorageContainer (
                name: 'New',
                storageContainerType: validStorageContainerType,
                parentStorageContainer: null,
                rows: 1,
                columns: 1,
                temperatureInCelcius: temperature
            )

        then:
            storageContainer.validate() == valid
            storageContainer.errors['temperatureInCelcius'] == errorMessage

        where:
            temperature | valid | errorMessage
            null        | false | 'nullable'    //Cannot be null
            1           | true  | null          //Valid
    }

    void "toString returns name"(){
        when:
            StorageContainer storageContainer = new StorageContainer(
                    name: '12in Rack'
            )

        then:
            storageContainer.toString() == '12in Rack'
    }

}
