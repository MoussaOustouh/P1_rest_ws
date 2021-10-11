package mo.spring.restws.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mo.spring.restws.entities.UserEntity;

@Repository
									// extends CrudRepository<UserEntity, Long> bnsba repository 3adiya
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);

//	@Query(value = "SELECT * FROM users", nativeQuery = true)
//	Page<UserEntity> findAllUser(Pageable pageableRequest);
	
	@Query("SELECT user FROM UserEntity user")
	Page<UserEntity> findAllUser(Pageable pageableRequest);
	
//	@Query(value = "SELECT * FROM users u WHERE (u.firstname = ?1 OR u.lastname = ?1) AND u.email_verification_status = ?2", nativeQuery = true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, String search, int status);

//	@Query(value = "SELECT * FROM users u WHERE (u.firstname = :search OR u.lastname = :search) AND u.email_verification_status = :status", nativeQuery = true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);
	
	@Query(value = "SELECT * FROM users u WHERE (u.firstname LIKE %:search% OR u.lastname LIKE %:search%) AND u.email_verification_status = :status", nativeQuery = true)
	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);
}
