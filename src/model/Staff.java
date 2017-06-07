package model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="staff")
public class Staff {
	@Id @Column(name="staff_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer staff_id;
	public Integer getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	private String staff_name;
	private String staff_gender;
	private String staff_phone;
	private String staff_password;
	private String staff_email;
	private String staff_post;//Ö°Îñ
	private String staff_department;
	@OneToMany(targetEntity=Client.class)
	@JoinTable(name="staff_client",
			joinColumns=@JoinColumn(name="staff_id",referencedColumnName="staff_id"),
			inverseJoinColumns=@JoinColumn(name="client_id",referencedColumnName="client_id",unique=true)
	)
	private Set<Client> myClients;
	
	public Set<Client> getMyClients() {
		return myClients;
	}

	public void setMyClients(Set<Client> myClients) {
		this.myClients = myClients;
	}
	@OneToMany(targetEntity=Journey.class)
	@JoinTable(name="staff_journey",
			joinColumns=@JoinColumn(name="staff_id",referencedColumnName="staff_id"),
			inverseJoinColumns=@JoinColumn(name="journey_id",referencedColumnName="journey_id",unique=true)
	)
	private Set<Journey> myJourneys;
	
	
	public Set<Journey> getMyJourneys() {
		return myJourneys;
	}

	public void setMyJourneys(Set<Journey> myJourneys) {
		this.myJourneys = myJourneys;
	}

	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_gender() {
		return staff_gender;
	}
	public void setStaff_gender(String staff_gender) {
		this.staff_gender = staff_gender;
	}
	public String getStaff_phone() {
		return staff_phone;
	}
	public void setStaff_phone(String staff_phone) {
		this.staff_phone = staff_phone;
	}
	public String getStaff_password() {
		return staff_password;
	}
	public void setStaff_password(String staff_password) {
		this.staff_password = staff_password;
	}
	public String getStaff_email() {
		return staff_email;
	}
	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}
	public String getStaff_post() {
		return staff_post;
	}
	public void setStaff_post(String staff_post) {
		this.staff_post = staff_post;
	}
	public String getStaff_department() {
		return staff_department;
	}
	public void setStaff_department(String staff_department) {
		this.staff_department = staff_department;
	}

}
