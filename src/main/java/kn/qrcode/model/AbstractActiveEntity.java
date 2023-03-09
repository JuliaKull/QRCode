package kn.qrcode.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class AbstractActiveEntity extends AbstractEntity{

    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean active;
}
