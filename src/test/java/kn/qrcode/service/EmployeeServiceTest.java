package kn.qrcode.service;

import kn.qrcode.dto.EmployeeDto;
import kn.qrcode.mapper.EmployeeMapper;
import kn.qrcode.mapper.EmployeeMapperImpl;
import kn.qrcode.mocks.dto.EmployeeDtoMock;
import kn.qrcode.model.Employee;
import kn.qrcode.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static kn.qrcode.mocks.model.EmployeeMock.shallowEmployeeMock;
import static kn.qrcode.mocks.model.EmployeeMock.shallowEmployeeMockList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    @Captor
    private ArgumentCaptor<Employee> employeeArgumentCaptor;

    @Spy
    private EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    @Test
    @DisplayName("Save Employee Success")
    void saveEmployeeSuccess() {


        EmployeeDto toSaveDto = EmployeeDtoMock.shallowEmployeeDto(null);

        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeDto savedDto = employeeService.create(toSaveDto);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee toSaveEntity = employeeArgumentCaptor.getValue();

        assertThat(savedDto.getId()).isEqualTo(toSaveDto.getId());
        verify(employeeMapper, times(1)).toDto(toSaveEntity);
        verify(employeeMapper, times(1)).toEntity(toSaveDto);
        verify(employeeRepository, times(1)).save(toSaveEntity);
        assertThat(toSaveDto).isEqualTo(savedDto);

    }

    @Test
    @DisplayName("update Employee success")
    void updateEmployee (){

        Long entityIdToUpdate = 1L;
        String updateLastName = "new LastName";
        Employee foundEntity = shallowEmployeeMock(entityIdToUpdate);
        EmployeeDto toUpdateDto =employeeMapper.toDto(foundEntity);
        toUpdateDto.setLastName(updateLastName);

        when(employeeRepository.findById(entityIdToUpdate)).thenReturn(Optional.of(foundEntity));

        EmployeeDto updatedDto =employeeService.update(toUpdateDto);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee toUpdateEntity = employeeArgumentCaptor.getValue();

        verify(employeeMapper, times(1)).update(toUpdateEntity,toUpdateDto);
        verify(employeeRepository, times(1)).save(foundEntity);
        verify(employeeMapper, times(2)).toDto(foundEntity);

        assertThat(toUpdateDto).isEqualTo(updatedDto);
    }

    @Test
    @DisplayName("soft delete Employee success")
    void deleteEmployeeSuccess (){

        Long entityIdToDelete = 1L;
        Employee foundEntity = shallowEmployeeMock(entityIdToDelete);
        EmployeeDto toDeleteDto =employeeMapper.toDto(foundEntity);
        when(employeeRepository.findById(entityIdToDelete)).thenReturn(Optional.of(foundEntity));
        employeeService.delete(entityIdToDelete);
        verify(employeeRepository,times(1)).deleteById(entityIdToDelete);

    }

    @Test
    @DisplayName("find Employee by id success")
    void findEmployeeById (){

        Long entityId = 1L;
        Employee foundEntity = shallowEmployeeMock(entityId);
        when(employeeRepository.findById(entityId)).thenReturn(Optional.of(foundEntity));
        employeeService.findById(entityId);
        verify(employeeRepository,times(1)).findById(entityId);

    }


    @Test
    @DisplayName("find all Employees active success")
    void findAllActiveEmployees (){

        boolean active = true;
        int page = 0;
        int size = 10;
        Page<Employee> employeesPage = new PageImpl<>(shallowEmployeeMockList(10));
        when(employeeRepository.findAll(active, PageRequest.of(page, size))).thenReturn(employeesPage);

        Page<EmployeeDto> resultPage = employeeService.findAll(active, page, size);

        verify(employeeRepository, times(1)).findAll(active, PageRequest.of(page, size));
        assertThat(resultPage.getSize()).isEqualTo(10);
        assertThat(resultPage.getTotalElements()).isEqualTo(10);
        assertThat(resultPage.getNumber()).isEqualTo(page);
        assertThat(resultPage.getSize()).isEqualTo(size);
    }
}

