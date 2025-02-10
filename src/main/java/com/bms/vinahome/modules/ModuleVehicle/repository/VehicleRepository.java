package com.bms.vinahome.modules.ModuleVehicle.repository;

import com.bms.vinahome.modules.ModuleCompany.entity.Company;
import com.bms.vinahome.modules.ModuleVehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByLicensePlateAndCompanyId(String licensePlate, Long companyId);

    boolean existsByCompanyAndLicensePlate(Company company, String licensePlate);

    List<Vehicle> findByCompanyId(Long companyId);
}
