package mo.spring.restws.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mo.spring.restws.entities.UserEntity;

@Repository
									// extends CrudRepository<UserEntity, Long> bnsba repository 3adiya
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	
	@Query(value = "SELECTE * FROM users", nativeQuery = true)
	Page<UserEntity> findAllUser(Pageable pageableRequest);
	
	@Query(value = "SELECT * FROM users u WHERE u.firstname = ?1 OR u.lastname = ?1", nativeQuery = true)
	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, String search);
}
