package kn.qrcode.repository;


import kn.qrcode.dto.EmployeeDto;
import kn.qrcode.dto.search.EmployeeSearchDto;
import kn.qrcode.model.Employee;
import kn.qrcode.model.Location;
import kn.qrcode.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import static kn.qrcode.mocks.model.DepartmentMock.shallowDepartmentDto;
import static kn.qrcode.mocks.model.JobTitleMock.shallowJobTitle;
import static kn.qrcode.mocks.model.LocationMock.shallowLocation;
import static kn.qrcode.mocks.model.OrganizationMock.shallowOrganization;
import static kn.qrcode.model.enums.GenderType.Male;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestSpecificationQueries {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Test
    //@Rollback(value = false)
    public void testWithInput(){
        Location location = locationRepository.save(createNewLocation(1l));
        Employee employee = employeeRepository.save(createNewEmployee(1l));

        EmployeeSearchDto searchByLastName = EmployeeSearchDto.builder()
                .lastName("Markov")
                .build();
        testTemplate(1,searchByLastName);

        Long locationId =shallowLocation(1l).getId();
        EmployeeSearchDto searchByLocationId = EmployeeSearchDto.builder()
                .locationId(locationId)
                .build();
        testTemplate(1,searchByLocationId);


    }

    private void testTemplate (int expectedResultsetSize, EmployeeSearchDto employeeSearch) {
        Page<EmployeeDto> pageOfEmployees = employeeService.search(employeeSearch);
        assertThat(pageOfEmployees.getContent().size()).isEqualTo(expectedResultsetSize);
    }



    private Location createNewLocation(Long id) {

        return Location.builder()
                .id(id)
                .name("Tallinn")
                .country("Estonia")
                .city("Tallinn")
                .postalCode("45778")
                .street("Main street")
                .build();
    }

    private Employee createNewEmployee(Long id) {

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

}
