package com.bms.vinahome.modules.ModuleTrip.repository;

import com.bms.vinahome.modules.ModuleTrip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("SELECT t FROM Trip t WHERE t.company.id = :companyId AND t.route.id = :routeId AND t.dateDeparture = :dateDeparture")
    List<Trip> findTripsByCompanyIdAndRouteIdAndDate(Long companyId, Long routeId, LocalDate dateDeparture);

    boolean existsByRouteIdAndDateDepartureAndTimeDeparture(Long routeId, LocalDate dateDeparture, LocalTime timeDeparture);

    Trip findByRouteIdAndDateDepartureAndTimeDeparture(Long routeId, LocalDate date, LocalTime time);
}
