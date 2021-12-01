package example.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import example.model.entity.ParentEntity;

public interface IParentRepo extends JpaRepository<ParentEntity, String> {

}
