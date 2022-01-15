package example.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.model.dto.ParentDto;
import example.model.dto.ParentsPageDto;
import example.service.JpaService;

@CrossOrigin
@RestController
public class JpaController {
	@Autowired
	JpaService service;

	@GetMapping("/")
	List<ParentDto> getParents(){
		return service.getParents();
	}
	@GetMapping("/find")
	List<ParentDto> getParentsByName(@RequestParam String searchString){
		return service.getParentsByName(searchString);
	}
	@GetMapping("/page")
	ParentsPageDto getParentsPage(@RequestParam int page, @RequestParam int quantity, HttpServletResponse response){
		return service.getParentsPage(page, quantity); 

	}
	@GetMapping("/find_page")
	ParentsPageDto getParentsByNamePage(@RequestParam int page, @RequestParam int quantity, @RequestParam String searchString){
		return service.getParentsByNamePage(page, quantity, searchString); 
		
	}
	@GetMapping("/{id}")
	ParentDto getParent(@PathVariable String id, HttpServletResponse response){
		ParentDto result = service.getParent( id );
		if (result!=null) {
			return result;
		}else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
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
