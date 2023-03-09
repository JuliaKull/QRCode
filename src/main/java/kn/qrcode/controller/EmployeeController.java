package kn.qrcode.controller;

import kn.qrcode.dto.EmployeeDto;
import kn.qrcode.dto.search.EmployeeSearchDto;
import kn.qrcode.service.EmployeeService;
import kn.qrcode.service.QRCodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    private final QRCodeService qrCodeService;

    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        log.debug("REST request to create Employee");
        return ResponseEntity.ok().body(employeeService.create(employeeDto));
    }

    @PostMapping(value = "/qrcode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> QRCodeGenerate(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.ok().body(qrCodeService.generateQRCodeImage(id));
    }

    @PostMapping(value = "/read_qrcode", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> readFromQRCode(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok().body(qrCodeService.readFromQRCodeImage(file));
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<EmployeeDto> getById(@PathVariable(value = "id") Long id) {
        log.debug("REST request to get Employee with id: {}", id);
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

    @PatchMapping(value = "/update/{id}",produces = {"application/json"}, consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<EmployeeDto> partialUpdate(@PathVariable(value = "id") Long id, @RequestBody EmployeeDto employeeDto) {
        log.debug("REST request to partialUpdate Employee");
        return ResponseEntity.ok().body(employeeService.partialUpdate(employeeDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        log.debug("REST request to delete Employee : {}", id);
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<List<EmployeeDto>> search(@RequestBody EmployeeSearchDto employeeSearchDto) {
        log.debug("REST request to search Employee by : {}", employeeSearchDto);
        return ResponseEntity.ok().body(employeeService.search(employeeSearchDto).toList());
    }

    @GetMapping(value = "/list", produces = {"application/json"})
    public ResponseEntity<List<EmployeeDto>> findAll(
            @RequestParam(value = "active", defaultValue = "true") boolean active,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.debug("REST request to get all Employees where active is : {}", active);
        return ResponseEntity.ok().body(employeeService.findAll(active, page, size).toList());
    }

    @GetMapping(value = "list_all", produces = {"application/json"})
    public ResponseEntity<List<EmployeeDto>> findAll() {
        log.debug("REST request to get all Employees");
        return ResponseEntity.ok().body(employeeService.findAll());
    }


}
