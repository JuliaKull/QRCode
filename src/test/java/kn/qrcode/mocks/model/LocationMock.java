package kn.qrcode.mocks.model;


import kn.qrcode.dto.LocationDto;
import kn.qrcode.model.Location;

public class LocationMock {

    public static Location shallowLocation(Long id) {

        return Location.builder()
                .id(id)
                .name("Tallinn")
                .country("Estonia")
                .city("Tallinn")
                .postalCode("45778")
                .street("Main street")
                .build();


    }

}
