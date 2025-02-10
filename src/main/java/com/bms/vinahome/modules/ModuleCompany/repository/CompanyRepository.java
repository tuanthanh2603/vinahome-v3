package com.bms.vinahome.modules.ModuleCompany.repository;

import com.bms.vinahome.modules.ModuleCompany.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
