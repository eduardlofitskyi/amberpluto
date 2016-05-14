package com.lofitskyi.repository;

import com.lofitskyi.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long>{

    @Query("SELECT j FROM Journey j WHERE j.route.departureStation.id=:source and j.route.arrivalStation.id=:destination")
    List<Journey> getBySDD(@Param("source") long source,
                           @Param("destination") long destination);
}
