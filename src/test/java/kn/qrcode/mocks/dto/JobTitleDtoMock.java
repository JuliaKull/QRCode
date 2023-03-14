package kn.qrcode.mocks.dto;
import kn.qrcode.dto.JobTitleDto;

import java.time.LocalDateTime;

public class JobTitleDtoMock {

    public static JobTitleDto shallowJobTitleDto(Long id) {

        return JobTitleDto.builder()
                .id(id)
                .name("Mock")
                .build();
    }
}
