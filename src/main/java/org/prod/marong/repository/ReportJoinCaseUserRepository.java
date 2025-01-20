package org.prod.marong.repository;

import org.prod.marong.model.entity.ReportJoinCaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportJoinCaseUserRepository extends JpaRepository<ReportJoinCaseUserEntity, Long> {

    @Query("SELECT n FROM ReportJoinCaseUserEntity n WHERE n.userId = :id")
    ReportJoinCaseUserEntity findReportJoinCaseUserById(String id);




}
