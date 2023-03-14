package kn.qrcode.mocks.model;



import kn.qrcode.model.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static kn.qrcode.mocks.model.DepartmentMock.shallowDepartmentDto;
import static kn.qrcode.mocks.model.JobTitleMock.shallowJobTitle;
import static kn.qrcode.mocks.model.LocationMock.shallowLocation;
import static kn.qrcode.mocks.model.OrganizationMock.shallowOrganization;
import static kn.qrcode.model.enums.GenderType.Male;

public class EmployeeMock {

        public static Employee shallowEmployeeMock(Long id) {
            return Employee.builder()
                    .id(id)
                    .firstName("John"+id)
                    .lastName("Doe"+id)
                    .email("john.doe@kehne+nagel.com")
                    .birthDate(LocalDate.now().minusYears(24))
                    .gender(Male)
                    .startDate(LocalDate.now().minusMonths(7))
                    .department(shallowDepartmentDto(1l))
                    .jobTitle(shallowJobTitle(1l))
                    .organization(shallowOrganization(1l))
                    .location(shallowLocation(1l))
                    .build();
        }

    public static Employee shallowEmployeeActiveMock(Long id) {
        return Employee.builder()
                .id(id)
                .firstName("John"+id)
                .active(true)
                .lastName("Doe"+id)
                .email("john.doe@kehne+nagel.com")
                .birthDate(LocalDate.now().minusYears(24))
                .gender(Male)
                .startDate(LocalDate.now().minusMonths(7))
                .department(shallowDepartmentDto(1l))
                .jobTitle(shallowJobTitle(1l))
                .organization(shallowOrganization(1l))
                .location(shallowLocation(1l))
                .build();
    }


    public static List<Employee> shallowEmployeeMockList(int howMany) {
           List<Employee> mockEmployees = new ArrayList<>();
           for (int i = 1; i <= howMany; i++) {
               mockEmployees.add(shallowEmployeeActiveMock((long) i));
           }
              return mockEmployees;
        }
}
