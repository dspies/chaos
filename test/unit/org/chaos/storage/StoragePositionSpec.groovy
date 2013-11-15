package org.chaos.storage

import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(StoragePosition)
@Unroll
class StoragePositionSpec extends Specification {

    void "StorageContainer constraint - #errorMessage"(){
        setup:
            mockForConstraintsTests(StoragePosition)

        when:
            def storagePosition = new StoragePosition(storageContainer: storageContainer, position: '1')

        then:
            storagePosition.validate() == valid
            storagePosition.errors['storageContainer'] == errorMessage

        where:
            storageContainer       | valid | errorMessage
            null                   | false | 'nullable'
            new StorageContainer() | true  | null
    }

    void "Position constraint - #errorMessage"(){
        setup:
            StoragePosition existingStoragePosition = new StoragePosition(
                    storageContainer:storageContainer,
                    position: '1')
        mockForConstraintsTests(StoragePosition, [existingStoragePosition])

        when:
            def storagePosition = new StoragePosition(
                    storageContainer: storageContainer,
                    position: position)

        then:
            storagePosition.validate() == valid
            storagePosition.errors['position'] == errorMessage

        where:
            position         | valid | errorMessage
            null             | false | 'nullable'   //Cannot be null
            ''               | false | 'blank'      //Cannot be blank
            '1'              | false | 'unique'     //Cannot have the same position as another StoragePosition in the same StorageContainer
            'x'.multiply(11) | false | 'maxSize' //Cannot be longer than 10 characters
            '2'              | true  | null         //Valid

            storageContainer = new StorageContainer(name: 'Box1')
    }

    void "toString returns position"(){
        when:
            StoragePosition storagePosition = new StoragePosition(position: 'A01')

        then:
            storagePosition.toString() == 'A01'
    }
}
