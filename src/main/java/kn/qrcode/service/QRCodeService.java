package kn.qrcode.service;

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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class QRCodeService {

    private final EmployeeRepository repository;

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    public BufferedImage generateQRCodeImage(Long employeeId) throws WriterException {
        log.debug("Request to genetare QR Code for Employee with Id : {}",employeeId);
        Employee employee = repository.findById(employeeId).get();
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(employee.toString(), BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public String readFromQRCodeImage(MultipartFile qrCodeImage) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(qrCodeImage.toString()));
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
        Result result = new MultiFormatReader().decode(bitmap);
        System.out.println(result.getText());
        return result.getText();
    }



}
