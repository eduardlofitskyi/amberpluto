package com.lofitskyi.repository;

import com.lofitskyi.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long>{

    @Query("SELECT s FROM Station s WHERE s.city.id = :id")
    List<Station> getByCityId(@Param("id") Long id);
}
