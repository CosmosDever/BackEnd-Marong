package org.prod.marong.repository;

import org.prod.marong.model.entity.CasesEntity;
import org.prod.marong.model.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<CasesEntity, Long> {
    @Query("SELECT n FROM CasesEntity n")
    List<CasesEntity> findAllCase();

    @Query("SELECT n FROM CasesEntity n WHERE n.id = :id")
    CasesEntity findCaseById(String id);





}
