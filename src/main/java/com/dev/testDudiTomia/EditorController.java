package com.dev.testDudiTomia;


import org.springframework.web.bind.annotation.RestController;

import com.dev.testDudiTomia.logic.EditorLogic;

import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class EditorController {
	EditorLogic editorLogic = new EditorLogic();

	@PostMapping(path = "/")
	ResponseEntity<String> add(@RequestBody String newText){
		editorLogic.add(newText);
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
	}
	@PostMapping(path = "/{position}")
	ResponseEntity<String> add(@PathVariable("position") int position,@RequestBody String newText){
		try {
			editorLogic.add(newText,position);
			return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/{fromPosition}/{toPosition}")
	ResponseEntity<String> remove(@PathVariable("fromPosition") int fromPosition,@PathVariable("toPosition") int toPosition){
		try {
			editorLogic.delete(fromPosition, toPosition);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);	
	}
	@PutMapping("/italic/{fromPosition}/{toPosition}")
	ResponseEntity<String> italic(@PathVariable("fromPosition") int fromPosition,@PathVariable("toPosition") int toPosition){
		try {
			editorLogic.italic(fromPosition, toPosition);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
	}
	@PutMapping("/bold/{fromPosition}/{toPosition}")
	ResponseEntity<String> bold(@PathVariable("fromPosition") int fromPosition,@PathVariable("toPosition") int toPosition){
		try {
			editorLogic.bold(fromPosition, toPosition);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
	}
	@PutMapping("/underLine/{fromPosition}/{toPosition}")
	ResponseEntity<String> underLine(@PathVariable("fromPosition") int fromPosition,@PathVariable("toPosition") int toPosition){
		try {
			editorLogic.underLine(fromPosition, toPosition);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
	}
	@GetMapping(path = "/print")
	ResponseEntity<String> print(){
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
	}
	@GetMapping(path = "/redo")
	ResponseEntity<String> redo(){
		editorLogic.redo();
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
	}
	@GetMapping(path = "/undo")
	ResponseEntity<String> undo(){
		editorLogic.undo();
		return new ResponseEntity<>(editorLogic.print(), HttpStatus.OK);
	}
}
