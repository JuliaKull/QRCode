package kn.qrcode.mapper;

import kn.qrcode.dto.EmployeeDto;
import kn.qrcode.model.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static kn.qrcode.utils.Constants.DATE_FORMAT_DD_MM_YYYY;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper extends EntityMapper<Employee, EmployeeDto> {

    @Override
    @Mapping(target = "birthDate", dateFormat = DATE_FORMAT_DD_MM_YYYY)
    @Mapping(target = "startDate", dateFormat = DATE_FORMAT_DD_MM_YYYY)
    Employee toEntity(EmployeeDto dto);


    @Override
    @InheritInverseConfiguration(name = "toEntity")
    EmployeeDto toDto(Employee entity);
}
