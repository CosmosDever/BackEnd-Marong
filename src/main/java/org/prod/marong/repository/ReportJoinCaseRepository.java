package org.prod.marong.repository;

import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportJoinCaseRepository extends JpaRepository<ReportJoinCaseEntity, Long> {
    @Query("SELECT n FROM ReportJoinCaseEntity n")
    List<ReportJoinCaseEntity> findAllReportJoinCase();

    @Query("SELECT n FROM ReportJoinCaseEntity n WHERE n.userId = :id AND n.status = :status")
    List<ReportJoinCaseEntity> findReportJoinCaseById(String id,String status);

}
