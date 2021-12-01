package example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.model.dto.ChildDto;
import example.model.dto.ParentDto;
import example.model.entity.ChildEntity;
import example.model.entity.ParentChildEntity;
import example.model.entity.ParentEntity;
import example.model.repo.IChildRepo;
import example.model.repo.IParentRepo;

@Service
public class JpaService {
		@Autowired
		IParentRepo parentRepo;
		IChildRepo childRepo;
		
		public List<ParentDto> getParents(){
			return parentRepo.findAll().stream().map(this::parentEntityToDto).collect(Collectors.toList());
		}
		
		private ParentDto parentEntityToDto(ParentEntity entity) {
			ParentDto dto = new ParentDto();
			dto.setId( entity.getId());
			dto.setName( entity.getName());
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
