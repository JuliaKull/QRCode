package kn.qrcode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
@Entity
@Data
@SuperBuilder(toBuilder=true)
@NoArgsConstructor
@Table(name = "organization")
public class Organization extends AbstractActiveEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private String vatNumber;

    @Column(nullable = false)
    private String registrationCode;
}
