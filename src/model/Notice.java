package model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="notice")
public class Notice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer notice_id;
	public Integer getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(Integer notice_id) {
		this.notice_id = notice_id;
	}
	private String notice_detail;
	private Date notice_date;
	
	public String getNotice_detail() {
		return notice_detail;
	}
	public void setNotice_detail(String notice_detail) {
		this.notice_detail = notice_detail;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
}
