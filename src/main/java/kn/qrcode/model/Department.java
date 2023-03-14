package kn.qrcode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Table(name = "department")
public class Department extends AbstractActiveEntity implements Serializable {

    private static final long serialVersionUID = 5L;

    @Column(name = "name", length = 50, nullable = false)
    private String name;


}
