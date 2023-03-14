package kn.qrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String postalCode;
}
