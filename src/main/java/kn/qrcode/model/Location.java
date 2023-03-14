package kn.qrcode.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
@Entity
@Data
@SuperBuilder(toBuilder=true)
@NoArgsConstructor
@Table(name = "location")
public class Location extends AbstractActiveEntity implements Serializable {

    private final static long serialVersionUID = 3L;

    @Column(name = "name")
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;
}
