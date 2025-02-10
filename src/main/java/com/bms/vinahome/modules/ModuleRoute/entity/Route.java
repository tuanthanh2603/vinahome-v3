package com.bms.vinahome.modules.ModuleRoute.entity;

import com.bms.vinahome.modules.ModuleCompany.entity.Company;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "route")
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;


    @Column(name = "route_name")
    private String routeName;

    @Column(name = "route_name_short")
    private String routeNameShort;

    @Column(name = "display_price")
    private Double displayPrice;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "note")
    private String note;

    @Column(name = "display_order")
    private Integer displayOrder;
}
