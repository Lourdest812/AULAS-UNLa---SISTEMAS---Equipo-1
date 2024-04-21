package com.example.classroomProject.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.classroomProject.controllers.ClassroomController;
import com.example.classroomProject.dtos.ClassroomDTO;
import com.example.classroomProject.mappers.ClassroomMapper;
import com.example.classroomProject.services.ClassroomService;
import com.example.classroomProject.services.impl.ClassroomServiceImpl;

import jakarta.validation.Valid;

@RestController(ClassroomControllerImpl.BEAN_NAME)
@RequestMapping(path = "classroom")
public class ClassroomControllerImpl implements ClassroomController {

	public static final String BEAN_NAME = "ClassroomControllerImpl";

	@Autowired
	private ClassroomMapper classroomMapper;

	@Autowired
	@Qualifier(ClassroomServiceImpl.BEAN_NAME)
	private ClassroomService classroomService;

	@Override
	@GetMapping(path = "/all")
	public ResponseEntity<List<ClassroomDTO>> getClassrooms() {
		List<ClassroomDTO> response = getClassroomMapper().classroomToDTOList(getClassroomService().findAll());
		return new ResponseEntity<List<ClassroomDTO>>(response, HttpStatus.OK);
	}

	@Override
	@GetMapping(path = "/{oid}")
	public ResponseEntity<ClassroomDTO> getClassroom(@PathVariable Long oid) {
		ClassroomDTO response = getClassroomMapper().classroomToDTO(getClassroomService().findByOid(oid).get());
		return new ResponseEntity<ClassroomDTO>(response, HttpStatus.OK);
	}

	@Override
	@PutMapping(path = "/{oid}")
	public ResponseEntity<Boolean> updateClassroom(@PathVariable Long oid, @RequestBody @Valid ClassroomDTO dto) {
		getClassroomService().updateClassroom(getClassroomMapper().classroomtoEntity(dto));
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}

	@Override
	@PostMapping
	public ResponseEntity<Boolean> createClassroom(@RequestBody @Valid ClassroomDTO dto) {
		getClassroomService().updateClassroom(getClassroomMapper().classroomtoEntity(dto));
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	@Override
	@PutMapping
	public ResponseEntity<Boolean> deleteClassrooms(@RequestBody List<Long> oids) {
		getClassroomService().deleteClassrooms(oids);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	public ClassroomMapper getClassroomMapper() {
		return classroomMapper;
	}

	public ClassroomService getClassroomService() {
		return classroomService;
	}

}