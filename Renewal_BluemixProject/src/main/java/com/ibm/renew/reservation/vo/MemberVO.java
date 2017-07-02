package com.ibm.renew.reservation.vo;

import java.util.Date;

public class MemberVO {
	private String mem_nm;
	private String mem_pn;
	private String mem_em;
	private String mem_site;
	private Date mem_reg_date;
	
	public String getMem_nm() {
		return mem_nm;
	}
	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}
	public String getMem_pn() {
		return mem_pn;
	}
	public void setMem_pn(String mem_pn) {
		this.mem_pn = mem_pn;
	}
	public String getMem_em() {
		return mem_em;
	}
	public void setMem_em(String mem_em) {
		this.mem_em = mem_em;
	}
	
	public String getMem_site() {
		return mem_site;
	}
	public void setMem_site(String mem_site) {
		this.mem_site = mem_site;
	}
	public Date getMem_reg_date() {
		return mem_reg_date;
	}
	public void setMem_reg_date(Date mem_reg_date) {
		this.mem_reg_date = mem_reg_date;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mem_nm=" + mem_nm + ", mem_pn=" + mem_pn + ", mem_em=" + mem_em + ", mem_site=" + mem_site
				+ ", mem_reg_date=" + mem_reg_date + "]";
	}
	
}
