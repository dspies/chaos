package org.chaos.lims

import groovy.transform.EqualsAndHashCode
import org.chaos.core.StudyArm
import org.chaos.core.StudyParticipant
import org.chaos.core.StudyCenter
import org.chaos.core.EventType

/**
 * Instances of materials donated by Study participants and collected or processed by Study personnel
 *
 * @author David Spies
 */
@EqualsAndHashCode
class Sample {

    StudyCenter studyCenter
    StudyParticipant studyParticipant
    StudyArm studyArm
    EventType eventType
    Material material
    TubeType tubeType
    String barcode
    Double initialVolumeInMicroliters
    Double volumeInMicroliters
    String aliquot
    boolean labeled
    boolean usable
    Date creationDate
    Date dateCreated
    Date lastUpdated

//Sample Collection Information
//        Collection_location (participant's home, study center, etc)

//Extra Sample Information
//        Collection Site on the body?
//        Double dilution - dilution with what?
//        Double concentration - Concentration of what

//Extra Sample Information (Lists)
//        tags: Tag
//        comments:SampleComment,
//        consents:Consent]

//Processing Information
//        Date processedDate - should be collected in ProcessingItem domain?
//        User creator?

    static mappedBy = [
            childRelationships: 'parentSample',
            parentRelationships: 'childSample'
    ]

    static hasMany = [
            externalIdentifiers:ExternalIdentifier,
            childRelationships: SampleRelationship,
            parentRelationships: SampleRelationship
    ]

    static constraints = {
        barcode blank: false, maxSize: 15, unique: true
        initialVolumeInMicroliters max: 100000000D, min: 0.0001D, scale: 4
        volumeInMicroliters max: 100000000D, min: 0.0001D, scale: 4
        aliquot blank: false, maxSize: 8
        creationDate validator: {
            if (it > new Date()){
                return ['max.exceeded']
            }
        }
    }

    String toString(){
        return barcode
    }
}