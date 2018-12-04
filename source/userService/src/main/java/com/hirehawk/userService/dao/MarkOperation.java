package com.hirehawk.userService.dao;

public class MarkOperation {
	String userId;
	String operation;
	Integer prevMark;
	Integer newMark;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Integer getPrevMark() {
		return prevMark;
	}
	public void setPrevMark(Integer prevMark) {
		this.prevMark = prevMark;
	}
	public Integer getNewMark() {
		return newMark;
	}
	public void setNewMark(Integer newMark) {
		this.newMark = newMark;
	}
	public MarkOperation(String userId, String operation, Integer prevMark, Integer newMark) {
		super();
		this.userId = userId;
		this.operation = operation;
		this.prevMark = prevMark;
		this.newMark = newMark;
	}
	public MarkOperation() {
		super();
	}
	
}
