package com.niit.dao;

import java.util.List;

import com.niit.model.Message;

public interface ChatDao {
	public boolean saveMessage(Message msg);
	public List<Message> getMessage();

}
