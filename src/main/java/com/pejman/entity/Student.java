package com.pejman.entity;

public class Student {
    private String sname;
	private String semail;
	private String spass;
	private String sdeg;
    //private int studentId;


    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSpass() {
        return spass;
    }

    public void setSpass(String spass) {
        this.spass = spass;
    }

    public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSdeg() {
		return sdeg;
	}

	public void setSdeg(String sdeg) {
		this.sdeg = sdeg;
	}
} 