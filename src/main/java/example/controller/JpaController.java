package example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.model.dto.ParentDto;
import example.service.JpaService;

@RestController
public class JpaController {
	@Autowired
	JpaService service;
	@GetMapping("/")
	List<ParentDto> getParents(){
		return service.getParents();
	}
	
	@PostMapping("/")
	ParentDto createParent(@RequestBody ParentDto dto) {
		return service.createParent(dto);
	}
	@PutMapping("/")
	ParentDto updateParent(@RequestBody ParentDto dto) {
		return service.updateParent(dto);
	}
	@DeleteMapping("/{id}")
	ParentDto deleteParent(@PathVariable String id){
		return service.deleteParent(id);
	}
}
