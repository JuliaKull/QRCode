package kn.qrcode.mocks.dto;


import kn.qrcode.dto.OrganizationDto;

public class OrganizationDtoMock {
    public static OrganizationDto shallowOrganizationDto(Long id) {
        return OrganizationDto.builder()
                .id(id)
                .name("Mock Organization"+id)
                .vatNumber("444")
                .registrationCode("45556")
                .build();
    }
}
