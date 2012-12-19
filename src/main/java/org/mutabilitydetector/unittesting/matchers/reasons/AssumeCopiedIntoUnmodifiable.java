package org.mutabilitydetector.unittesting.matchers.reasons;

import static org.mutabilitydetector.MutabilityReason.ABSTRACT_COLLECTION_TYPE_TO_FIELD;
import static org.mutabilitydetector.MutabilityReason.ABSTRACT_TYPE_TO_FIELD;
import static org.mutabilitydetector.MutabilityReason.COLLECTION_FIELD_WITH_MUTABLE_ELEMENT_TYPE;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.mutabilitydetector.MutableReasonDetail;
import org.mutabilitydetector.locations.FieldLocation;

public class AssumeCopiedIntoUnmodifiable extends BaseMatcher<MutableReasonDetail> {

    public static class Assuming {
        private final String fieldName;
        public Assuming(String fieldName) {
            this.fieldName = fieldName;
        }
        public Matcher<MutableReasonDetail> isSafelyCopiedUnmodifiableCollectionWithImmutableTypes() {
            return new AssumeCopiedIntoUnmodifiable(fieldName);
        }
        
    }

    public static AssumeCopiedIntoUnmodifiable.Assuming assuming(String fieldName) {
        return new Assuming(fieldName);
    }

    private final String fieldName;
    
    public AssumeCopiedIntoUnmodifiable(String fieldName) {
        this.fieldName = fieldName;
    }
    
    @Override
    public void describeTo(Description description) { }

    @Override
    public boolean matches(Object arg0) {
        MutableReasonDetail reasonDetail = (MutableReasonDetail) arg0;
        if (reasonDetail.reason().isOneOf(ABSTRACT_COLLECTION_TYPE_TO_FIELD, 
                                          ABSTRACT_TYPE_TO_FIELD, 
                                          COLLECTION_FIELD_WITH_MUTABLE_ELEMENT_TYPE)) {
            String potentiallyAbstractField = ((FieldLocation) reasonDetail.codeLocation()).fieldName();
            if (potentiallyAbstractField.equals(fieldName)) {
                return true;
            }
        }
        
        return false;
    }

}