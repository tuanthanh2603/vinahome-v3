package com.bms.vinahome.modules.ModuleEmployee.controller;

import com.bms.vinahome.modules.ModuleEmployee.dto.DTO_RQ_CreateEmployee;
import com.bms.vinahome.modules.ModuleEmployee.dto.DTO_RQ_EditEmployee;
import com.bms.vinahome.modules.ModuleEmployee.dto.DTO_RP_Assistant;
import com.bms.vinahome.modules.ModuleEmployee.dto.DTO_RP_Driver;
import com.bms.vinahome.modules.ModuleEmployee.dto.DTO_RP_Employee;
import com.bms.vinahome.modules.ModuleEmployee.service.EmployeeService;
import com.bms.vinahome.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    // PB.01_US.01: Add New Employee
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<DTO_RP_Employee> addNewEmployee(@RequestBody DTO_RQ_CreateEmployee dto) {
        var result = employeeService.createEmployee(dto);
        return ApiResponse.<DTO_RP_Employee>builder()
               .code(1000)
               .message("Tạo nhân viên mới thành công")
               .result(result)
               .build();
    }

    // PB.01_US.02: Update Employee Information
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<DTO_RP_Employee> updateEmployee(@PathVariable Long id, @RequestBody DTO_RQ_EditEmployee dto) {
        var result = employeeService.updateEmployee(id, dto);
        return ApiResponse.<DTO_RP_Employee>builder()
                .code(1000)
                .message("Cập nhật thông tin nhân viên thành công")
                .result(result)
                .build();
    }

    // PB.01_US.03: Remove Employee
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<Void> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Xoá nhân viên thành công")
                .build();
    }

    // PB.01_US.04: Lock Account Employee
    @PostMapping("/lock/{id}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<Void> lockAccountEmployee(@PathVariable Long id) {
        employeeService.lockAccountEmployee(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Khoá tài khoản thành công")
                .build();
    }

    // PB.01_US.05: Change Password Account Employee
    @PostMapping("/change-password/{id}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<Void> changePassAccountEmployee(@PathVariable Long id) {
        employeeService.changePassAccountEmployee(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Mật khẩu mới của bạn là: 12345678")
                .build();
    }

    // PB.01_US.06: Filter/Get Employee List
    @GetMapping("/list-employee/{companyId}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN')")
    ApiResponse<List<DTO_RP_Employee>> getEmployeesByCompanyId_v2(@PathVariable("companyId") Long companyId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        var result = employeeService.getEmployeesByCompanyId(companyId);
        return ApiResponse.<List<DTO_RP_Employee>>builder()
                .code(1000)
                .message("Tải dữ liệu thành công")
                .result(result)
                .build();
    }

    // PB.01_US.07: Filter/Get List Employee Role Driver
    @GetMapping("/list-driver-name/{companyId}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN') or hasRole('EMPLOYEE')")
    ApiResponse<List<DTO_RP_Driver>> getDriverNameByCompanyId(@PathVariable("companyId") Long companyId) {
        var result = employeeService.getDriverNameByCompanyId(companyId);
        return ApiResponse.<List<DTO_RP_Driver>>builder()
                .code(1000)
                .message("Tải dữ liệu thành công")
                .result(result)
                .build();
    }


    // PB.01_US.08: Filter/Get List Employee Role Assistant
    @GetMapping("/list-assistant-name/{companyId}")
    @PreAuthorize("hasRole('ADMIN_APP') or hasRole('ADMIN') or hasRole('EMPLOYEE')")
    ApiResponse<List<DTO_RP_Assistant>> getAssistantNameByCompanyId(@PathVariable("companyId") Long companyId) {
        var result = employeeService.getAssistantNameByCompanyId(companyId);
        return ApiResponse.<List<DTO_RP_Assistant>>builder()
                .code(1000)
                .message("Tải dữ liệu thành công")
                .result(result)
                .build();
    }

//    public ResponseEntity<Void> changePassAccountEmployee (@PathVariable Long id) {
//        if (id == null || id <= 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400: ID không hợp lệ
//        }
//        try {
//            if (!employeeService.existsById(id)) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: ID không tồn tại
//            }
//            employeeService.changePassAccountEmployee(id);
//            return ResponseEntity.noContent().build(); // 204: Thay đổi mật khẩu thành công
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.notFound().build(); // 404: ID không tồn tại
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }

//    public ResponseEntity<Void> lockAccountEmployee (@PathVariable Long id) {
//        if (id == null || id <= 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400: ID không hợp lệ
//        }
//        try {
//            if (!employeeService.existsById(id)) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: ID không tồn tại
//            }
//            employeeService.lockAccountEmployee(id);
//            return ResponseEntity.noContent().build(); // 204: Khóa thành công
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.notFound().build(); // 404: ID không tồn tại
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }




//    public ResponseEntity<Void> deleteEmployee (@PathVariable Long id) {
//        if (id == null || id <= 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400: ID không hợp lệ
//        }
//        try {
//            if (!employeeService.existsById(id)) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: ID không tồn tại
//            }
//            employeeService.deleteEmployeeById(id);
//            return ResponseEntity.noContent().build(); // 204: Xóa thành công
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }





    // VIN-11: Update Employee Information
//    @PutMapping("/update/{id}")
//    public ResponseEntity<DTO_RP_Employee> updateEmployee (@PathVariable Long id, @RequestBody DTO_RQ_EditEmployee dto) {
//        if (id == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400: Dữ liệu vào không hợp lệ
//        }
//        if (dto.getFullName() == null || dto.getFullName().isEmpty() ||
//                dto.getRoles() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400: Dữ liệu vào không hợp lệ
//        }
//        if (!employeeService.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: User không tồn tại
//        }
//        try {
//            DTO_RP_Employee updatedEmployee = employeeService.updateEmployee(id, dto);
//            return ResponseEntity.ok(updatedEmployee); // 200: Cap nhat thanh cong
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().build(); // 400: Du lieu vao khong hop le
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Loi he thong
//        }
//    }





    // UC_EM_06: Tìm kiếm nhân viên theo “tên”
//    @GetMapping("/filter-by-name")
//    public ResponseEntity<List<DTO_RP_Employee>> searchEmployeesByName(
//            @RequestParam("fullName") String fullName,
//            @RequestParam("companyId") Long companyId) {
//
//        try {
//            List<DTO_RP_Employee> employees = employeeService.searchEmployeesByName(fullName, companyId);
//            if (employees.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: Không tìm thấy nhân viên
//            }
//            return ResponseEntity.ok(employees); // 200: Trả về danh sách nhân viên
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }

    // UC_EM_07: Lọc nhân viên theo “vai trò”
//    @GetMapping("/filter-by-role")
//    public ResponseEntity<List<DTO_RP_Employee>> searchEmployeesByRole(
//            @RequestParam("role") Integer role,
//            @RequestParam("companyId") Long companyId) {
//
//        try {
//            List<DTO_RP_Employee> employees = employeeService.searchEmployeesByRole(role, companyId);
//            if (employees.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: Không tìm thấy nhân viên
//            }
//            return ResponseEntity.ok(employees); // 200: Trả về danh sách nhân viên
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }

//    @GetMapping("/list-employee/{companyId}")
//    public ResponseEntity<List<DTO_RP_Employee>> getEmployeesByCompanyId(@PathVariable Long companyId) {
//        if (companyId == null || companyId <= 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400: ID công ty không hợp lệ
//        }
//        try {
//            List<DTO_RP_Employee> employees = employeeService.getEmployeesByCompanyId(companyId);
//            if (employees.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404: Không có nhân viên nào thuộc công ty
//            }
//            return ResponseEntity.ok(employees); // 200: Trả về danh sách nhân viên
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Lỗi hệ thống
//        }
//    }




//    @GetMapping("/list-driver/{companyId}")
//    public ResponseEntity<List<DTO_RP_Driver>> getDriverByCompanyId(@PathVariable Long companyId) {
//        try {
//            List<DTO_RP_Driver> response = employeeService.getDriverByCompanyId(companyId);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Loi he thong
//        }
//    }
//
//    @GetMapping("/list-assistant/{companyId}")
//    public ResponseEntity<List<DTO_RP_Assistant>> getAssistantByCompanyId(@PathVariable Long companyId) {
//        try {
//            List<DTO_RP_Assistant> response = employeeService.getAssistantByCompanyId(companyId);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500: Loi he thong
//        }
//    }















}
