package kn.qrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String gender;
    private String startDate;
    private JobTitleDto jobTitle;
    private LocationDto location;
    private OrganizationDto organization;
    private DepartmentDto department;

}
