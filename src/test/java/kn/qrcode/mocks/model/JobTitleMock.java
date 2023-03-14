package kn.qrcode.mocks.model;
import kn.qrcode.dto.JobTitleDto;
import kn.qrcode.model.JobTitle;

public class JobTitleMock {

    public static JobTitle shallowJobTitle(Long id) {

        return JobTitle.builder()
                .id(id)
                .name("Mock")
                .build();
    }
}
