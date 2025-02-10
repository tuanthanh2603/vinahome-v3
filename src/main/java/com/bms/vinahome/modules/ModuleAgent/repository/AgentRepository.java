package com.bms.vinahome.modules.ModuleAgent.repository;

import com.bms.vinahome.modules.ModuleAgent.entity.Agent;
import com.bms.vinahome.modules.ModuleCompany.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findByCompanyId(Long companyId);

    boolean existsByCompanyAndName(Company company, String name);

    boolean existsByCompanyAndUsername(Company company, String username);
}
