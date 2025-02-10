package com.bms.vinahome.modules.ModuleOffice.controller;

import com.bms.vinahome.modules.ModuleOffice.dto.DTO_RP_OfficeName;
import com.bms.vinahome.modules.ModuleOffice.dto.DTO_RQ_Office;
import com.bms.vinahome.modules.ModuleOffice.dto.DTO_RP_Office;
import com.bms.vinahome.modules.ModuleOffice.service.OfficeService;
import com.bms.vinahome.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfficeController {
    @Autowired
    OfficeService officeService;

    // PB.02_US.01: Add new office
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<DTO_RP_Office> addNewOffice(@RequestBody DTO_RQ_Office dto) {
        var result = officeService.createOffice(dto);
        return ApiResponse.<DTO_RP_Office>builder()
                .code(1000)
                .message("Tạo văn phòng mới thành công")
                .result(result)
                .build();
    }

    // PB.02_US.02: Update office information
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<DTO_RP_Office> updateOffice(@PathVariable Long id, @RequestBody DTO_RQ_Office dto){
        var result = officeService.updateOffice(id, dto);
        return ApiResponse.<DTO_RP_Office>builder()
                .code(1000)
                .message("Cập nhật thông tin văn phòng thành công")
                .result(result)
                .build();
    }

    // PB.02_US.03: Remove office
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<Void> deleteOffice(@PathVariable Long id) {
        officeService.deleteOfficeById(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Xoá văn phòng thành công")
                .build();
    }

    // PB.02_US.04: Filter/Get list office
    @GetMapping("/list-office/{companyId}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<List<DTO_RP_Office>> getListOfficeByCompanyId(@PathVariable("companyId") Long companyId) {
        var result = officeService.getListOfficeByCompanyId(companyId);
        return ApiResponse.<List<DTO_RP_Office>>builder()
                .code(1000)
                .message("Tải dữ liệu thành công")
                .result(result)
                .build();
    }

    // PB.02_US.05: Filter/Get list office name
    @GetMapping("/list-office-name/{companyId}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN') or hasRole('EMPLOYEE')")
    ApiResponse<List<DTO_RP_OfficeName>> getListOfficeNameByCompanyId(@PathVariable Long companyId) {
        var result = officeService.getListOfficeNameByCompanyId(companyId);
        return ApiResponse.<List<DTO_RP_OfficeName>>builder()
                .code(1000)
                .message("Tải dữ liệu thành công")
                .result(result)
                .build();
    }






   /// @PostMapping("/create")
//    public ApiResponse<DTO_RP_Office> create(@RequestBody DTO_RQ_Office dto) {
//        ApiResponse<DTO_RP_Office> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(officeService.createOffice(dto));
//        return apiResponse;
//    }
//    @GetMapping("/list-office/{companyId}")
//    public ResponseEntity<List<DTO_RP_Office>> getListOfficeByCompanyId(@PathVariable Long companyId) {
//        try {
//            List<DTO_RP_Office> officeResponse = officeService.getListOfficeByCompanyId(companyId);
//            return ResponseEntity.ok(officeResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Loi he thong
//        }
//    }
//    @GetMapping("/list-office/{companyId}")
//    ApiResponse<List<DTO_RP_Office>> getListOfficeByCompanyId(@PathVariable Long companyId) {
//        List<DTO_RP_Office> offices = officeService.getListOfficeByCompanyId(companyId);
//        return ApiResponse.<List<DTO_RP_Office>>builder()
//                .code(1000)
//                .message("Lấy danh sách văn phòng thành công")
//                .result(offices)
//                .build();
//    }
//    @PutMapping("/update/{officeId}")
//    public ResponseEntity<DTO_RP_Office> updateOffice(
//            @PathVariable Long officeId,
//            @RequestBody DTO_RQ_Office updatedData) {
//        try {
//            DTO_RP_Office updatedOffice = officeService.updateOffice(officeId, updatedData);
//            return ResponseEntity.ok(updatedOffice); // 200: Thành công
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: Không tìm thấy văn phòng
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400: Yêu cầu không hợp lệ
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }
//    @DeleteMapping("/delete/{officeId}")
//    public ResponseEntity<Void> deleteOffice(@PathVariable Long officeId) {
//        try {
//            officeService.deleteOfficeById(officeId);
//            return ResponseEntity.noContent().build(); // 204: Xóa thành công
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: Không tìm thấy văn phòng
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }
//    @GetMapping("/list-office-name/{companyId}")
//    public ResponseEntity<List<DTO_RP_OfficeName>> getListOfficeNameByCompanyId(@PathVariable Long companyId) {
//        try {
//            List<DTO_RP_OfficeName> officeResponse = officeService.getListOfficeNameByCompanyId(companyId);
//            return ResponseEntity.ok(officeResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }

}
