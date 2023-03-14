package kn.qrcode.mocks.model;


import kn.qrcode.dto.DepartmentDto;
import kn.qrcode.model.Department;

public class DepartmentMock {

        public static Department shallowDepartmentDto(Long id) {
            return Department.builder()
                    .id(id)
                    .name("Mock "+id)
                    .build();
        }
}
