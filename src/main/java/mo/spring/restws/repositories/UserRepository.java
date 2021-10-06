package mo.spring.restws.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mo.spring.restws.entities.UserEntity;

@Repository
									// extends CrudRepository<UserEntity, Long> bnsba repository 3adiya
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
