package kn.qrcode.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static kn.qrcode.utils.Constants.DEFAULT_JPA_PAGE_SIZE;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class GenericSearchDto<T> {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer limit = DEFAULT_JPA_PAGE_SIZE;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer page = 0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sort = "id";

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sort.Direction dir = Sort.DEFAULT_DIRECTION;

    @JsonIgnore
    public Specification<T> getSpecification() {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Predicate noFiltersApplied = criteriaBuilder.conjunction();
            List<Predicate> filters = new ArrayList<>();
            filters.add(noFiltersApplied);
            addFilters(root, query, criteriaBuilder, filters);
            return criteriaBuilder.and(filters.toArray(new Predicate[filters.size()]));
        };
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(
                (page != null) ? page : 0,
                (limit != null && limit >= 0) ? limit : DEFAULT_JPA_PAGE_SIZE,
                getSortSpec());
    }

    @JsonIgnore
    public Sort getSortSpec() {
        if (sort == null) return Sort.unsorted();
        return (dir != null && dir == Sort.Direction.DESC) ?
                Sort.by(sort).descending() : Sort.by(sort).ascending();
    }

    protected void addFilters(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Predicate> filters) {
    }
}
