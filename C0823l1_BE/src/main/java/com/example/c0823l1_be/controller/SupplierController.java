package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.SupplierDto;
import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService iSupplierService;

    /**
     * Lấy danh sách tất cả các nhà cung cấp với phân trang và sắp xếp.
     * @param pageable Thông tin phân trang và sắp xếp.
     * @return Danh sách nhà cung cấp dưới dạng DTO và phân trang.
     */
    @GetMapping("")
    public ResponseEntity<Page<SupplierDto>> showSupplier(
            @PageableDefault(sort = "phone", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Supplier> suppliers = iSupplierService.findAll(pageable);
        Page<SupplierDto> supplierDTOs = suppliers.map(supplier -> new SupplierDto(
                supplier.getId(),
                supplier.getUid(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail()
        ));
        return ResponseEntity.ok(supplierDTOs);
    }

    /**
     * Xóa nhiều nhà cung cấp dựa trên danh sách UID.
     * @param uids Danh sách UID của các nhà cung cấp cần xóa.
     * @return Trạng thái của thao tác xóa.
     */
    @PostMapping("/deleteByUid")
    public ResponseEntity<?> deleteSuppliersByUid(@RequestBody List<String> uids) {
        if (uids == null || uids.isEmpty()) {
            return ResponseEntity.badRequest().body("Không có UID nhà cung cấp nào được cung cấp để xóa.");
        }
        try {
            iSupplierService.deleteByUids(uids);
            return ResponseEntity.ok().body("Nhà cung cấp đã xóa thành công");
        } catch (Exception e) {
            // Log lỗi để phân tích sau
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không xóa được nhà cung cấp    ");
        }
    }

    /**
     * Tìm kiếm nhà cung cấp theo địa chỉ và từ khóa, với phân trang và sắp xếp.
     * @param address Địa chỉ để tìm kiếm (có thể là null).
     * @param search Từ khóa tìm kiếm theo tên hoặc số điện thoại (có thể là null).
     * @param page Số trang hiện tại.
     * @param size Số lượng nhà cung cấp mỗi trang.
     * @param sort Tham số sắp xếp (ví dụ: "phone,desc").
     * @return Danh sách nhà cung cấp theo điều kiện tìm kiếm và phân trang.
     */
    @GetMapping("/search")
    public ResponseEntity<Page<SupplierDto>> searchSuppliers(
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", defaultValue = "phone,desc") String sort) {

        // Tách các tham số sắp xếp từ chuỗi sort
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sortSpecification = Sort.by(direction, sortParams[0]);

        // Tạo Pageable với các tham số sắp xếp và phân trang
        Pageable pageable = PageRequest.of(page, size, sortSpecification);

        // Tìm kiếm và chuyển đổi sang DTO
        Page<Supplier> suppliers = iSupplierService.searchByAddressAndName(address, search, pageable);
        Page<SupplierDto> supplierDTOs = suppliers.map(supplier -> new SupplierDto(
                supplier.getId(),
                supplier.getUid(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail()
        ));
        return ResponseEntity.ok(supplierDTOs);
    }

}

