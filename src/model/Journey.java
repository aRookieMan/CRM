package model;

import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name="journey")
public class Journey {
	@Id @Column(name="journey_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer journey_id;
	public Integer getJourney_id() {
		return journey_id;
	}
	public void setJourney_id(Integer journey_id) {
		this.journey_id = journey_id;
	}
	private String journey_content;
	private Date journey_date;
	private String journey_remark;//完成，未完成，取消
	private String journey_adress;
	@ManyToOne(targetEntity=Staff.class)
	@JoinTable(name="client_journey",
		joinColumns=@JoinColumn(name="journey_id",referencedColumnName="journey_id",unique=true),
		inverseJoinColumns=@JoinColumn(name="staff_id",referencedColumnName="staff_id")
	)
	private Staff myStaff;
	
	
	public Staff getMyStaff() {
		return myStaff;
	}
	public void setMyStaff(Staff myStaff) {
		this.myStaff = myStaff;
	}
	public String getJourney_content() {
		return journey_content;
	}
	public void setJourney_content(String journey_content) {
		this.journey_content = journey_content;
	}
	public Date getJourney_date() {
		return journey_date;
	}
	public void setJourney_date(Date journey_date) {
		this.journey_date = journey_date;
	}
	public String getJourney_remark() {
		return journey_remark;
	}
	public void setJourney_remark(String journey_remark) {
		this.journey_remark = journey_remark;
	}
	public String getJourney_adress() {
		return journey_adress;
	}
	public void setJourney_adress(String journey_adress) {
		this.journey_adress = journey_adress;
	}
}
