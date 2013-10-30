package org.chaos.personnel

import groovy.transform.EqualsAndHashCode
import org.chaos.personnel.attribute.CertificationType

/**
 * Certifications held by individuals involved in the studies
 * @author David Spies
 */
@EqualsAndHashCode
class StaffCertification {

    StaffMember staffMember
    CertificationType certificationType
    String description
    Date certificationDate
    Date expireDate
    Date dateCreated
    Date lastUpdated

    static constraints = {
        description nullable: false, blank: true, maxSize: 255
        expireDate nullable: true
    }

    String toString() {
        super.toString();
    }
}
