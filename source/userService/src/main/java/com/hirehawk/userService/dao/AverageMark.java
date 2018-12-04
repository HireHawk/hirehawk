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
	public AverageMark(Integer evalSum, Integer evalNumber) {
		this.evalSum = evalSum;
		this.evalNumber = evalNumber;
		this.updateEvalAvg();
	} 
	private void updateEvalAvg() {
		if(this.evalNumber == null)return;
		if(this.evalNumber == 0)return;
		if(this.evalSum ==null)this.evalSum=0;
		this.evalAvg= (float)this.evalSum/(float)(this.evalNumber);
	}
    public Integer getEvalNumber() {
        return this.evalNumber;
    }
    public void setEvalNumber(Integer evalNumber) {
        this.evalNumber = evalNumber;
        this.updateEvalAvg();
    }
    public Integer getEvalSum() {
        return this.evalSum;
    }
    public void setEvalSum(Integer evalSum) {
        this.evalSum = evalSum;
        this.updateEvalAvg();
    }
    public Float getEvalAvg() {
    	this.updateEvalAvg();
        return this.evalAvg;
    }
    public void setEvalAvg(Float evalAvg) {
        this.evalAvg = evalAvg;
    }
}
