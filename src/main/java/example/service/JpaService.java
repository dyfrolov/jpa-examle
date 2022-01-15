package example.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import example.model.dto.ChildDto;
import example.model.dto.ParentDto;
import example.model.dto.ParentsPageDto;
import example.model.entity.ChildEntity;
import example.model.entity.ParentChildEntity;
import example.model.entity.ParentEntity;
import example.model.repo.IChildRepo;
import example.model.repo.IParentRepo;

@Service
public class JpaService {
		@Autowired
		IParentRepo parentRepo;
		@Autowired
		IChildRepo childRepo;
		
		
		public List<ParentDto> getParents(){
			return parentRepo.findAll().stream().map(this::parentEntityToDto).collect(Collectors.toList());
		}
		public List<ParentDto> getParentsByName(String searchString) {
			return parentRepo.findByNameContains(searchString).stream().map(this::parentEntityToDto).collect(Collectors.toList());
		}

		public ParentsPageDto getParentsPage(int page, int quantity){
			Page<ParentEntity> pageParentEntity = parentRepo.findAll(PageRequest.of(page, quantity, Sort.by("name").ascending().and(Sort.by("id")))) ;
			ParentsPageDto result = pageParentEntityToParentsPageDto(pageParentEntity);
			return result;
		}
		private ParentsPageDto pageParentEntityToParentsPageDto(Page<ParentEntity> pageParentEntity) {
			ParentsPageDto result= new ParentsPageDto();
			if (pageParentEntity==null) return result;
			result.setParents(pageParentEntity.getContent().stream().map(this::parentEntityToDto).collect(Collectors.toList()));
			result.setTotalPages(pageParentEntity.getTotalPages());
			result.setTotalElements(pageParentEntity.getTotalElements());
			result.setFirst(pageParentEntity.isFirst());
			result.setHasNext(pageParentEntity.hasNext());
			result.setHasPrev(pageParentEntity.hasPrevious());
			result.setLast(pageParentEntity.isLast());
			result.setNumber(pageParentEntity.getNumber());
			result.setNumberOfElements(pageParentEntity.getNumberOfElements());
			result.setSize(pageParentEntity.getSize());
			return result;
		}
		public ParentsPageDto getParentsByNamePage(int page, int quantity, String searchString) {
			Page<ParentEntity> pageParentEntity = parentRepo.findByNameContains(searchString, PageRequest.of(page, quantity, Sort.by("name").ascending().and(Sort.by("id")) ) );
			ParentsPageDto result = pageParentEntityToParentsPageDto(pageParentEntity);
			return result;
		}
		
		public ParentDto getParent( String id ) {
			ParentEntity entity = null;
			if ( !parentRepo.existsById(id) ) {
				return null;
			}
			entity = parentRepo.getById(id);
			
			return parentEntityToDto( entity);
		}
		
		private ParentDto parentEntityToDto(ParentEntity entity) {
			ParentDto dto = new ParentDto();
			dto.setId( entity.getId());
			dto.setName( entity.getName());
			dto.setEnabled(entity.isEnabled());
			dto.setChilds( childsEntityListToDtoList(entity.getChilds()));
			return dto;
		}

		private List<ChildDto> childsEntityListToDtoList(List<ParentChildEntity> list) {
			return list.stream().map(e->childEntityToDto(e)).collect(Collectors.toList());
		}

		private ChildDto childEntityToDto(ParentChildEntity entity) {
			ChildDto dto = new ChildDto();
			dto.setId( entity.getChild().getId());
			dto.setName( entity.getChild().getName());
			dto.setInfo(entity.getInfo());
			return dto;
		}
		
		@Transactional
		public ParentDto createParent(ParentDto dto) {
			ParentDto result = null; 
			final ParentEntity parentEntity = new ParentEntity();
			parentEntity.setId( dto.getId() );
			parentEntity.setName( dto.getName() );
			parentEntity.setEnabled( dto.isEnabled() );
			parentEntity.setChilds(
					dto.getChilds().stream().map(d->parentChildDtoToEntity(d, parentEntity) ).collect(Collectors.toList())
					);
			ParentEntity entity = parentRepo.save( parentEntity);
			result = parentEntityToDto( entity );
			return result;
		}

		private ParentChildEntity parentChildDtoToEntity(ChildDto dto, ParentEntity parentEntity) {
			ParentChildEntity entity = new ParentChildEntity();
			entity.setParent(parentEntity);
			entity.setChild( ChildDtoToEntity(dto));
			entity.setInfo(dto.getInfo());
			entity.setId(String.valueOf(System.nanoTime()));
			return entity;
		}

		private ChildEntity ChildDtoToEntity(ChildDto dto) {
			ChildEntity entity = new ChildEntity();
			entity.setId( dto.getId());
			entity.setName( dto.getName());
			return entity;
		}
		@Transactional
		public ParentDto updateParent(ParentDto dto) {
			ParentDto result = null; 
			ParentEntity parentEntity = parentRepo.getById(dto.getId());
			parentEntity.setName( dto.getName() );
			List<ParentChildEntity> parentChildEntityList = parentEntity.getChilds();
			HashMap<String, ParentChildEntity> hashMap = new HashMap<>();
			for(ParentChildEntity parentChildEntity : parentChildEntityList) {
				hashMap.putIfAbsent(parentChildEntity.getChild().getId(), parentChildEntity);
			}
			parentChildEntityList.clear();

			for (ChildDto childDto: dto.getChilds()) {
				if (hashMap.get(childDto.getId())!=null) {
					hashMap.get(childDto.getId()).setInfo(childDto.getInfo());
					parentChildEntityList.add( hashMap.get(childDto.getId()) );
				}else {
					parentChildEntityList.add(parentChildDtoToEntity(childDto, parentEntity));
				}
			}
			ParentEntity entity = parentRepo.save( parentEntity);
			result = parentEntityToDto( entity );
			return result;
		}


		public ParentDto deleteParent(String id) {
			ParentDto result = null;
			ParentEntity parentEntity = parentRepo.getById(id);
			parentRepo.delete(parentEntity);
			result = parentEntityToDto( parentEntity );
			return result;
		}



}
