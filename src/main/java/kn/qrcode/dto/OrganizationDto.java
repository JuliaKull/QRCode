package kn.qrcode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {

    private Long id;
    private String name;
    private String vatNumber;
    private String registrationCode;
}
