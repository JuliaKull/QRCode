package kn.qrcode.mocks.model;


import kn.qrcode.dto.OrganizationDto;
import kn.qrcode.model.Organization;

public class OrganizationMock {
    public static Organization shallowOrganization(Long id) {
        return Organization.builder()
                .id(id)
                .name("Mock Organization"+id)
                .vatNumber("444")
                .registrationCode("45556")
                .build();
    }
}
