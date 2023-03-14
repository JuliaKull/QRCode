package kn.qrcode.mocks.dto;


import kn.qrcode.dto.DepartmentDto;

public class DepartmentDtoMock {

        public static DepartmentDto shallowDepartmentDto(Long id) {
            return DepartmentDto.builder()
                    .id(id)
                    .name("Mock "+id)
                    .build();
        }
}
