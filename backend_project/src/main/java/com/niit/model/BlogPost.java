package com.niit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "blogpost_table")
@JsonSerialize
public class BlogPost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5271164853558296208L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String blogTitle;

	@Lob
	private String blogContent;

	/*@Transient
	@ManyToOne
	private User postedBy;*/

	private String postedBy;

	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", blogTitle=" + blogTitle + ", blogContent=" + blogContent + ", postedBy="
				+ postedBy + ", postedOn=" + postedOn + ", likes=" + likes + ", approved=" + approved + "]";
	}

	@Temporal(TemporalType.DATE)
	private Date postedOn;

	private int likes;

	private boolean approved;

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	/*public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}*/

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}