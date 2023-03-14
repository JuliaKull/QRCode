package kn.qrcode.mocks.dto;


import kn.qrcode.dto.LocationDto;

public class LocationDtoMock {

    public static LocationDto shallowLocationDto(Long id) {

        return LocationDto.builder()
                .id(id)
                .name("Tallinn")
                .country("Estonia")
                .city("Tallinn")
                .postalCode("45778")
                .street("Main street")
                .build();


    }

}
