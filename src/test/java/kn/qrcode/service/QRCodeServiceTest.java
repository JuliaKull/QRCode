package kn.qrcode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.WriterException;
import kn.qrcode.model.Employee;
import kn.qrcode.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Optional;

import static kn.qrcode.mocks.model.EmployeeMock.shallowEmployeeMock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QRCodeServiceTest {

    @Mock
    private EmployeeRepository repository;
    @InjectMocks
    private QRCodeService qrCodeService;

    @Test
    public void testGenerateQRCodeImage() throws Exception {
        Long employeeId = 1L;
        Employee employee = shallowEmployeeMock(1l);
        when(repository.findById(employeeId)).thenReturn(Optional.of(employee));

        BufferedImage qrCodeImage = qrCodeService.generateQRCodeImage(employeeId);

        assertEquals(400, qrCodeImage.getWidth());
        assertEquals(400, qrCodeImage.getHeight());

    }

    @Test
    public void testReadFromQRCodeImage() throws Exception {
        String qrCodeText = "Test";
        String qrCodeTextJson = new ObjectMapper().writeValueAsString(qrCodeText);


        InputStream inputStream = getClass().getResourceAsStream("/test.png");
        MockMultipartFile qrCodeImage = new MockMultipartFile("qrCodeImage", "test.png", "image/png", inputStream);


        String result = qrCodeService.readFromQRCodeImage(qrCodeImage);


        assertEquals(qrCodeTextJson, result);

    }}