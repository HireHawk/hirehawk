package com.hirehawk.userService.dao;

public class AverageMark {
	public Integer evalSum;
	public Integer evalNumber;
	public Float evalAvg;
	
	public AverageMark() {
		
	}
	public AverageMark(Integer evalSum, Integer evalNumber, Float evalAvg) {
		this.evalSum = evalSum;
		this.evalNumber = evalNumber;
		this.evalAvg = evalAvg;
	} 
    public Integer getEvalNumber() {
        return this.evalNumber;
    }
    public void setEvalNumber(Integer evalNumber) {
        this.evalNumber = evalNumber;
    }
    public Integer getEvalSum() {
        return this.evalSum;
    }
    public void setEvalSum(Integer evalSum) {
        this.evalSum = evalSum;
    }
    public Float getEvalAvg() {
        return this.evalAvg;
    }
    public void setEvalAvg(Float evalAvg) {
        this.evalAvg = evalAvg;
    }
}
