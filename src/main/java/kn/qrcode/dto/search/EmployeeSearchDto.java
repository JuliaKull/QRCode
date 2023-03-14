package kn.qrcode.dto.search;

import jakarta.persistence.criteria.Predicate;
import kn.qrcode.model.Employee;
import kn.qrcode.model.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchDto extends GenericSearchDto<Employee> {

    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String gender;
    private String startDate;
    private Long jobTitleId;
    private Long locationId;
    private Long organizationId;
    private Long departmentId;

    @Override
    public Specification<Employee> getSpecification() {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Predicate noFiltersApplied = criteriaBuilder.conjunction();
            List<Predicate> filters = new ArrayList<>();
            filters.add(noFiltersApplied);

            if (Strings.isNotBlank(firstName)) {
                Predicate firstNameAsPredicate = criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
                filters.add(firstNameAsPredicate);
            }
            if (Strings.isNotBlank(lastName)) {
                Predicate lastNameAsPredicate = criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
                filters.add(lastNameAsPredicate);
            }
            if (Strings.isNotBlank(email)) {
                Predicate emailAsPredicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");
                filters.add(emailAsPredicate);
            }
            if (Strings.isNotBlank(birthDate)) {
                Predicate birthDateAsPredicate = criteriaBuilder.like(root.get("birthDate"), "%" + birthDate + "%");
                filters.add(birthDateAsPredicate);
            }
            if (Strings.isNotBlank(gender)) {
                Predicate genderAsPredicate = criteriaBuilder.equal(root.get("gender"), GenderType.valueOf(gender));
                filters.add(genderAsPredicate);
            }

            if (Strings.isNotBlank(startDate)) {
                Predicate startDateAsPredicate = criteriaBuilder.like(root.get("startDate"), "%" + startDate + "%");
                filters.add(startDateAsPredicate);
            }
            if (jobTitleId != null) {
                Predicate jobTitleIdAsPredicate = criteriaBuilder.equal(root.get("jobTitle").get("id"), jobTitleId);
                filters.add(jobTitleIdAsPredicate);
            }
            if (locationId != null) {
                Predicate locationIdAsPredicate = criteriaBuilder.equal(root.get("location").get("id"), locationId);
                filters.add(locationIdAsPredicate);
            }
            if (organizationId != null) {
                Predicate organizationIdAsPredicate = criteriaBuilder.equal(root.get("organization").get("id"), organizationId);
                filters.add(organizationIdAsPredicate);
            }
            if (departmentId != null) {
                Predicate departmentIdAsPredicate = criteriaBuilder.equal(root.get("department").get("id"), departmentId);
                filters.add(departmentIdAsPredicate);
            }
            return criteriaBuilder.and(filters.toArray(new Predicate[filters.size()]));
        };
    }
}

