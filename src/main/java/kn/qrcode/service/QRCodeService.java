package kn.qrcode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import kn.qrcode.model.Employee;
import kn.qrcode.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


@Service
@Slf4j
@RequiredArgsConstructor
public class QRCodeService {

    private final EmployeeRepository repository;

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    public BufferedImage generateQRCodeImage(Long employeeId) throws WriterException, JsonProcessingException {
        log.debug("Request to generate QR Code for Employee with id: {}", employeeId);
        Employee employee = repository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String employeeJson = objectMapper.writeValueAsString(employee);
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(employeeJson, BarcodeFormat.QR_CODE, 400, 400);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public String readFromQRCodeImage(MultipartFile qrCodeImage){
        log.debug("Request to decode QR Code: {}", qrCodeImage.getOriginalFilename());
        try (InputStream inputStream = qrCodeImage.getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            String result = new MultiFormatReader().decode(bitmap).getText();
            System.out.println(result);
            return new ObjectMapper().writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }}
