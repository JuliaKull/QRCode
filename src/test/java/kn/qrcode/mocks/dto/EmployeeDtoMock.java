package kn.qrcode.mocks.dto;


import kn.qrcode.dto.EmployeeDto;
import kn.qrcode.model.enums.GenderType;

import static kn.qrcode.mocks.dto.DepartmentDtoMock.shallowDepartmentDto;
import static kn.qrcode.mocks.dto.JobTitleDtoMock.shallowJobTitleDto;
import static kn.qrcode.mocks.dto.LocationDtoMock.shallowLocationDto;
import static kn.qrcode.mocks.dto.OrganizationDtoMock.shallowOrganizationDto;

public class EmployeeDtoMock {

    public static EmployeeDto shallowEmployeeDto(Long id) {
        return EmployeeDto.builder()
                .id(id)
                .firstName("John"+id)
                .lastName("Doe"+id)
                .email("employye@gmail.com")
                .birthDate("10-10-2000")
                .gender("Male")
                .startDate("10-11-2022")
                .department(shallowDepartmentDto(id))
                .jobTitle(shallowJobTitleDto(id))
                .location(shallowLocationDto(id))
                .organization(shallowOrganizationDto(id))
                .build();
    }
}
