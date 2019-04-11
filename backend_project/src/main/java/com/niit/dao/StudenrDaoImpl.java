package com.niit.dao;



import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Student;
@Repository
@Transactional
public class StudenrDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	public List<Student> getAllStudents() {

		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Student"); //select * from student
		return query.list();
	}

	public Student addStudent(Student student) {
		Session  session=sessionFactory.getCurrentSession();
		session.save(student);
		return student;
	}

	public Student getStudentById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Student student=(Student)session.get(Student.class, id);
		return student;
		}

	public void deleteStudent(int id) {
		Session session=sessionFactory.getCurrentSession();
		Student student=(Student)session.get(Student.class, id);
		session.delete(student);

	}

	public void updateStudent(Student student) {
		Session session=sessionFactory.getCurrentSession();
		session.update(student);//update student set ... where id=1

	}

}
