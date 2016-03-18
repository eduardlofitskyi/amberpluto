package com.lofitskyi.repository;

import com.lofitskyi.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>{

    @Query("SELECT r FROM Route r WHERE r.departureStation.id = :id")
    List<Route> getByCityId(@Param("id") Long id);
}
