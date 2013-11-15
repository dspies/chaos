@artifact.package@

import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(@artifact.testclass@)
class @artifact.name@ extends Specification {

    void "(fieldName) constraint - #errorMessage"() {
        setup:
            mockForConstraintsTests(@artifact.testclass@)

        when:
            //def @artifact.testclass@ = new @artifact.testclass@()

        then:
            fail "Implement me"
            //@artifact.testclass@.validate() == valid
            //@artifact.testclass@.errors['(fieldName)'] == errorMessage

        where:
            //(fieldName)  | valid | errorMessage
            //(fieldValue) | false | 'error message, such as "nullable"'  //Cannot be ...
            //(fieldValue) | true  | null     //Valid
    }
}
