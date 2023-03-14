package kn.qrcode.service;

import kn.qrcode.dto.EmployeeDto;
import kn.qrcode.dto.search.EmployeeSearchDto;
import kn.qrcode.exceptions.UserException;
import kn.qrcode.mapper.*;
import kn.qrcode.model.*;
import kn.qrcode.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;


    public EmployeeDto create(EmployeeDto employeeDto) {
        log.debug("Request to save Employee : {} {}", employeeDto.getFirstName(), employeeDto.getLastName());
        Employee employee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (employee != null) {
            log.info("Employee is already exist in DB");
        }
        employee = employeeRepository.save(employeeMapper.toEntity(employeeDto));
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto findById(Long id) {
        log.debug("Request to find Employee by id: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UserException("Employee #" + id + " not found"));
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        log.debug("Request to partial update Employee by: {}", employeeDto.getId());
        Employee employee = employeeRepository.findById(employeeDto.getId()).orElseThrow(() -> new UserException("Employee #" + employeeDto.getId() + " not found"));
        employeeMapper.update(employee, employeeDto);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public void delete(Long id) {
        log.debug("Request to set status active = false to Employee with Id: {}", id);
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDto> findAll() {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    public Page<EmployeeDto> findAll(boolean active, int page, int size) {
        log.debug("Request to get all Employees where active is : {}", active);
        PageRequest request = PageRequest.of(page, size);
        List<EmployeeDto> dtos = employeeMapper.toDtos(employeeRepository.findAll(active, request).getContent());
        return new PageImpl<>(dtos);
    }

    public Page<EmployeeDto> search(EmployeeSearchDto employeeSearchDto) {
        log.debug("Request to search Employee by: {}", employeeSearchDto);
        Page<Employee> employeePage = employeeRepository.findAll(employeeSearchDto.getSpecification(), employeeSearchDto.getPageable());
        List<EmployeeDto> employeeDtos = employeeMapper.toDtos(employeePage.getContent());
        return new PageImpl<>(employeeDtos, employeeSearchDto.getPageable(), employeePage.getTotalElements());
    }
}
