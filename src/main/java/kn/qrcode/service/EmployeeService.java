package kn.qrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import kn.qrcode.dto.EmployeeDto;
import kn.qrcode.dto.search.EmployeeSearchDto;
import kn.qrcode.exceptions.UserException;
import kn.qrcode.mapper.EmployeeMapper;
import kn.qrcode.model.Employee;
import kn.qrcode.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    private final EmployeeMapper mapper;

    public EmployeeDto create(EmployeeDto employeeDto) {
        log.debug("Request to save Employee : {} {}", employeeDto.getFirstName(), employeeDto.getLastName());
        Employee employee = repository.findByEmail(employeeDto.getEmail());
        if (employee != null) {
            log.info("Employee is already exist in DB");
        }
        employee = repository.save(mapper.toEntity(employeeDto));
        return mapper.toDto(employee);
    }

    public EmployeeDto findById(Long id) {
        log.debug("Request to find Employee by id: {}", id);
        Employee employee = repository.findById(id).orElseThrow(() -> new UserException("Employee #" + id + " not found"));
        return mapper.toDto(employee);
    }

    public EmployeeDto partialUpdate(EmployeeDto employeeDto) {
        log.debug("Request to partial update Employee by: {}", employeeDto.getId());
        Employee employee = repository.findById(employeeDto.getId()).orElseThrow(() -> new UserException("Employee #" + employeeDto.getId() + " not found"));
        mapper.partialUpdate(employee, employeeDto);
        repository.save(employee);
        return mapper.toDto(employee);
    }


    public void delete(Long id) {
        log.debug("Request to set status active = false to Employee with Id: {}", id);
        repository.deleteById(id);
    }


    public List<EmployeeDto> findAll() {
        log.debug("Request to get all Employees");
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Page<EmployeeDto> findAll(boolean active, int page, int size) {
        log.debug("Request to get all Employees where active is : {}", active);
        PageRequest request = PageRequest.of(page, size);
        List<EmployeeDto> dtos = mapper.toDtos(repository.findAll(active, request).getContent());
        return new PageImpl<>(dtos);
    }

    public Page<EmployeeDto> search(EmployeeSearchDto employeeSearchDto) {
        log.debug("Request to search Employee by: {}", employeeSearchDto);
        Page<Employee> employeePage = repository.findAll(employeeSearchDto.getSpecification(), employeeSearchDto.getPageable());
        List<EmployeeDto> employeeDtos = mapper.toDtos(employeePage.getContent());
        return new PageImpl<>(employeeDtos, employeeSearchDto.getPageable(), employeePage.getTotalElements());
    }


}
