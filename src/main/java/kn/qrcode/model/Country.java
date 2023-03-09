package kn.qrcode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "country")
public class Country extends AbstractEntity implements Serializable {

    private final static long serialVersionUID = 6L;

    private String name;
}
