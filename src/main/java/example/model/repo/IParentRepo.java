package example.model.repo;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import example.model.entity.ParentEntity;

public interface IParentRepo extends JpaRepository<ParentEntity, String> {

	Collection<ParentEntity> findAllByNameContains(String searchString);

	Page<ParentEntity> findByNameContains(String searchString,Pageable pageable);

	Page<ParentEntity> findByNameStartsWith(String string, Pageable pageable);

	Collection<ParentEntity> findByNameContains(String searchString);



}
