 package com.hirehawk.userService.dao;

import java.io.Serializable;

import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User implements Serializable {

 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
    private String id;
   

	@Column(name = "photo")
    private String photo;

	@Column(name = "phone_number")
    private String phoneNumber;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "eval_average")
    private Float evalAvg;
	
	@Column(name = "eval_number")
	private Integer evalNumber;
	
	@Column(name = "eval_sum")
	private Integer evalSum;

    public User() {

    }

    public User(String id,String photo, String phoneNumber,String status, AverageMark averageMark) {
        this.id = id;

        this.photo = photo;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.setAverageMark(averageMark);
    }


    public User(FullUser u) {
        this.id = u.getId();
        this.photo = u.getPhoto();
        this.phoneNumber = u.getPhoneNumber();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
	


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setAverageMark(AverageMark mark) {
    	if(mark==null) return;
        this.evalNumber = mark.getEvalNumber();
        this.evalSum = mark.getEvalSum();
        this.evalAvg = mark.getEvalAvg();
    }
    public void updateAverageMark(AverageMark mark) {
    	if(mark==null)return;
        if(mark.getEvalNumber()!=null)this.evalNumber = mark.getEvalNumber();
        if(mark.getEvalSum()!=null)this.evalSum = mark.getEvalSum();
        if(mark.getEvalAvg()!=null)this.evalAvg = mark.getEvalAvg();
    }
    public AverageMark getAverageMark() {
        return new AverageMark(this.evalSum,this.evalNumber,this.evalAvg);
    }
    public User withMissingResolved(UserRepository repository) {
    	if(this.getId()==null)return null;
        User u = repository.findById(this.getId());  
        if(u==null)
        	return (User) this.clone();
        if(this.getPhoto()!=null)
        	u.setPhoto(this.getPhoto());
        if(this.getStatus()!=null)
        	u.setStatus(this.getStatus());
        if(this.getPhoneNumber()!=null)
        	u.setPhoneNumber(this.getPhoneNumber());
        if(this.getAverageMark()!=null)
        	u.updateAverageMark(this.getAverageMark());
        return u;
    }
    public User clone() {
    	return new User(this.id,this.getPhoto(),this.getPhoneNumber(),this.getStatus(),this.getAverageMark());
    }
}

