package model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {
	@Id @Column(name="client_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer client_id;
	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	private String client_name;
	private String client_gender;
	private String client_phone;
	private String client_adress;
	private Date client_birthday;
	private String client_level;
	private String client_email;
	private String client_qq;
	@OneToMany(targetEntity=Buy.class)
	@JoinTable(name="client_buy",
			joinColumns=@JoinColumn(name="client_id",referencedColumnName="client_id"),
			inverseJoinColumns=@JoinColumn(name="buy_id",referencedColumnName="buy_id",unique=true)
	)
	private Set<Buy> myBuys;
	
	public Set<Buy> getMyBuys() {
		return myBuys;
	}

	public void setMyBuys(Set<Buy> myBuys) {
		this.myBuys = myBuys;
	}

	@ManyToOne(targetEntity=Staff.class)
	@JoinTable(name="staff_client",
		joinColumns=@JoinColumn(name="client_id",referencedColumnName="client_id",unique=true),
		inverseJoinColumns=@JoinColumn(name="staff_id",referencedColumnName="staff_id")
	)
	private Staff myStaff;
	

	public Staff getMyStaff() {
		return myStaff;
	}

	public void setMyStaff(Staff myStaff) {
		this.myStaff = myStaff;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_gender() {
		return client_gender;
	}

	public void setClient_gender(String client_gender) {
		this.client_gender = client_gender;
	}

	public String getClient_phone() {
		return client_phone;
	}

	public void setClient_phone(String client_phone) {
		this.client_phone = client_phone;
	}

	public String getClient_adress() {
		return client_adress;
	}

	public void setClient_adress(String client_adress) {
		this.client_adress = client_adress;
	}

	public String getClient_level() {
		return client_level;
	}

	public void setClient_level(String client_level) {
		this.client_level = client_level;
	}

	public Date getClient_birthday() {
		return client_birthday;
	}

	public void setClient_birthday(Date client_birthday) {
		this.client_birthday = client_birthday;
	}

	public String getClient_email() {
		return client_email;
	}

	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}

	public String getClient_qq() {
		return client_qq;
	}

	public void setClient_qq(String client_qq) {
		this.client_qq = client_qq;
	}
}
