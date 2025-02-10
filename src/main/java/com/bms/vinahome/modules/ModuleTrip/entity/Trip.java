package com.bms.vinahome.modules.ModuleTrip.entity;

import com.bms.vinahome.modules.ModuleCompany.entity.Company;
import com.bms.vinahome.modules.ModuleRoute.entity.Route;
import com.bms.vinahome.modules.ModuleSeat.entity.SeatingChart;
import com.bms.vinahome.modules.ModuleVehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "trip")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    Company company;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    Route route;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "seat_chart_id", referencedColumnName = "id")
    SeatingChart seatChart;

    @Column(name = "driver_ids")
    String driverIds;

    @Column(name = "assistant_ids")
    String assistantIds;

    @Column(name = "time_departure")
    LocalTime timeDeparture;

    @Column(name = "date_departure")
    LocalDate dateDeparture;

    @Column(name = "note")
    String note;
}
