package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.HistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryRecord, Long> {
    @Query(value = "SELECT clear_log()", nativeQuery = true)
    void clearLog();

    @Query(value = "SELECT SUM(kilometrage) FROM log WHERE car_id = ?1", nativeQuery = true)
    Double getKmForToday(Long carId);

    @Query(value = "SELECT coalesce(SUM(kilometrage),0) FROM history WHERE car_id = ?1 AND h_date BETWEEN ?2 AND ?3", nativeQuery = true)
    Double getKmByDateInterval(Long carId, Date firstDate, Date secondDate);

    @Query(value = "SELECT coalesce(SUM(kilometrage),0) FROM history WHERE car_id = ?1 AND h_date = ?2", nativeQuery = true)
    Double getKmByDate(Long carId, Date date);
}
