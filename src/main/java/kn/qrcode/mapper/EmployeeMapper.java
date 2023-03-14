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
    @Mapping(source = "birthDate",target = "birthDate", dateFormat = DATE_FORMAT_DD_MM_YYYY)
    @Mapping(source = "startDate",target = "startDate", dateFormat = DATE_FORMAT_DD_MM_YYYY)
    @Mapping(source = "gender",target = "gender")
    Employee toEntity(EmployeeDto dto);

    @Override
    @Mapping(source = "birthDate",target = "birthDate", dateFormat = DATE_FORMAT_DD_MM_YYYY)
    @Mapping(source = "startDate",target = "startDate", dateFormat = DATE_FORMAT_DD_MM_YYYY)
    @Mapping(source = "gender",target = "gender")
    void update(@MappingTarget Employee entity, EmployeeDto dto);

    @Override
    @InheritInverseConfiguration(name = "toEntity")
    EmployeeDto toDto(Employee entity);
}
