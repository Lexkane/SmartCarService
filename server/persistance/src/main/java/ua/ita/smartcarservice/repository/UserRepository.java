package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select distinct u from UserEntity as u left join WorkersSkill as w " +
            "on u.id = w.workerId left join UserTechnicalService as ut " +
            "on u.id = (select ut.userId from UserTechnicalService as ut " +
            "left join Car as c on ut.userId = c.user " +
            "where c.id = :id) where w.skill.name = :name")
    List<UserEntity> findByCarIdAndWorkersSkill(@Param("name") String name, @Param("id") Long id);

    List<UserEntity> getByUserTechnicalService(UserTechnicalService userTechnicalService);

    UserEntity getUserById(Long id);

    List<UserEntity> findAll();

    UserEntity getByUsername(String username);

    @Query(value = "select * from user as u " +
            "left join user_roles as ur on u.id = ur.user_id " +
            "left join role as r on ur.role_id = r.id " +
            "where r.name = :name", nativeQuery = true)
    List<UserEntity> getUserEntitiesByRoleName(@Param("name") String name);

    @Query(value = "select * from user as u " +
            "left join user_roles as ur on u.id = ur.user_id " +
            "left join role as r on ur.role_id = r.id " +
            "left join users_service as us on u.id = us.user_id " +
            "where r.name = :name " +
            "and us.technical_service_id = :technical_service_id", nativeQuery = true)
    List<UserEntity> getUserEntitiesByRoleNameAndTechnicalService(@Param("name") String name,
                                                                  @Param("technical_service_id") Long technical_service_id);

    @Query("SELECT c.user FROM Car c WHERE c.id = :carId")
    UserEntity findUserEntityByCarId(@Param("carId") long carId);

}
