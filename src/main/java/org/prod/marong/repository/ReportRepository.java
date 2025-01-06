package org.prod.marong.repository;

import org.prod.marong.model.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    @Query("SELECT n FROM ReportEntity n WHERE n.id = :id")
    ReportEntity findReportById(@Param("id") String id);

    @Query("SELECT r.status FROM ReportEntity r WHERE r.caseId = :id")
    String findReportStatusById(@Param("id") String id);

    @Query("SELECT r.status FROM ReportEntity r")
    List<String> findReportStatus();

}
