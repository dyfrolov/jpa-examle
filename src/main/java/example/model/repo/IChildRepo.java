package example.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import example.model.entity.ChildEntity;

public interface IChildRepo extends JpaRepository<ChildEntity, String> {

}
