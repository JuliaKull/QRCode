package kn.qrcode.dto;

import kn.qrcode.model.Department;
import kn.qrcode.model.JobTitle;
import kn.qrcode.model.Location;
import kn.qrcode.model.Organization;
import kn.qrcode.model.enums.GenderType;
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
    private GenderType genderType;
    private String startDate;
    private JobTitleDto jobTitle;
    private LocationDto location;
    private OrganizationDto organization;
    private DepartmentDto department;

}
