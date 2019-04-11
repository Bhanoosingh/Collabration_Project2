package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;
@Repository//JobDaoImpl jobDaoImpl=new JobDaoImpl();
@Transactional
public class JobDaoImpl implements JobDao {
	@Autowired
	private SessionFactory sessionFactory;
	public void addJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);

	}

	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		//HQL - from Job
		Query query=session.createQuery("from Job");
		//SQL : select * from job_s180233 [all columns and all records]
		List<Job> jobs=query.list();
		return jobs;
	}

	public Job getJob(int id) {
		Session session=sessionFactory.getCurrentSession();
		//select * from job_s180233 where id=?
		Job job=(Job)session.get(Job.class, id);
		return job;
	}

}
