package com.niit.controllers;

/*import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.StudentDao;
import com.niit.model.Student;

*//*@RestController  //@Controller + @ResponseBody
public class StudentController {
	@Autowired
	private StudentDao studentDao;
		@RequestMapping(value="/getallstudents",method=RequestMethod.GET)
		public  List<Student> getAllStudents(){
			List<Student> students=studentDao.getAllStudents();
			return students;
		}
		//Input - student object in JSON format 
		//JSON to Java object @RequestBody  [Http Request Body contains data in JSON fmt]
		//Output - student object in JSON format
		@RequestMapping(value="/addstudent",method=RequestMethod.POST)
		public  ResponseEntity<?> addStudent(@RequestBody Student student){
			student=studentDao.addStudent(student);
			return new ResponseEntity<Student>(student,HttpStatus.CREATED);
		}
		@RequestMapping(value="/getstudent/{id}",method=RequestMethod.GET)
		//http://localhost:8080/...../getstudent/1
		// T is Type of data
		//? - Any type of data
		public  ResponseEntity< ?> getStudentById(@PathVariable int id){
			Student student=studentDao.getStudentById(id);
			if(student!=null)
			return new ResponseEntity<Student>(student, HttpStatus.OK);
			else
				return new ResponseEntity<String>("Student Details for Id " + id + " Not Found",HttpStatus.NOT_FOUND);
		}
		@RequestMapping(value="/deletestudent/{id}",method=RequestMethod.DELETE)
		public List<Student> deleteStudent(@PathVariable int id){
			studentDao.deleteStudent(id);
			List<Student> students=studentDao.getAllStudents();
			return students;
		}
		@RequestMapping(value="/updatestudent",method=RequestMethod.PUT)
		public Student updateStudent(@RequestBody Student student){
			studentDao.updateStudent(student);
			return student;
		}
	}

*/