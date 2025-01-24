package org.prod.marong.repository;

import org.prod.marong.model.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    @Query("SELECT n FROM NewsEntity n")
    List<NewsEntity> findAllNews();

    @Query("SELECT n FROM NewsEntity n WHERE n.id = :id")
    NewsEntity findNewsById(String id);



}
