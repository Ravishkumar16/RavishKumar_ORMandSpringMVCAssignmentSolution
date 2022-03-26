package com.greatLearning.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatLearning.model.Student;
import com.greatLearning.service.StudentService;

@Repository
public class StudentServiceImpl implements StudentService {

	
		@SuppressWarnings("unused")
		private SessionFactory sessionFactory;
		private Session session;

		@Autowired
		public StudentServiceImpl(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
			try {
				session = sessionFactory.getCurrentSession();
			} catch (HibernateException ex) {
				session = sessionFactory.openSession();
			}
		}

		@SuppressWarnings("unchecked")
		public List<Student> getAllStudents() {
			List<Student> students = session.createQuery("from Student").list();
			return students;
		}

		public Student getStudent(int studentId) {
			Student myStudent = session.get(Student.class, studentId);
			return myStudent;
		}

		@Transactional
		public void saveStudent(Student myStudent) {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(myStudent);
			transaction.commit();
		}

		@Transactional
		public void deleteStudent(int id) {
			Transaction transaction = session.beginTransaction();
			Student student = session.get(Student.class, id); // select * from student where id=1 -> Resultset -> Student
			session.delete(student);
			transaction.commit();
		}
	}


