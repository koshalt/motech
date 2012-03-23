package org.motechproject.scheduletracking.api.domain.filtering;

import ch.lambdaj.Lambda;
import org.motechproject.scheduletracking.api.domain.Enrollment;
import org.motechproject.scheduletracking.api.domain.Metadata;
import org.motechproject.scheduletracking.api.repository.AllEnrollments;
import org.motechproject.scheduletracking.api.service.impl.EnrollmentService;

import java.util.List;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.Matchers.hasItem;

public class MetadataPropertyCriterion implements Criterion {

    private String key;
    private String value;

    public MetadataPropertyCriterion(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public List<Enrollment> fetch(AllEnrollments allEnrollments) {
        return allEnrollments.findByMetadataProperty(key, value);
    }

    @Override
    public List<Enrollment> filter(List<Enrollment> enrollments, EnrollmentService enrollmentService) {
        return Lambda.filter(having(on(Enrollment.class).getMetadata(), hasItem(new Metadata(key, value))), enrollments);
    }
}
