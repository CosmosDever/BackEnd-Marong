package org.prod.marong.repository;

import org.prod.marong.model.UserModel;
import org.prod.marong.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE CAST(u.id AS string) LIKE CONCAT(:userid, '%')")
    UserEntity findByUserId(String userid);

    @Query("SELECT u FROM UserEntity u WHERE u.gmail = :gmail")
    UserEntity findByGmail(String gmail);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.gmail = :gmail")
    boolean existsByGmail(String gmail);

    
}
