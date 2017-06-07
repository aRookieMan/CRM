package model;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="buy")
public class Buy {
	@Id @Column(name="buy_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer buy_id;
	public Integer getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(Integer buy_id) {
		this.buy_id = buy_id;
	}
	private String goods_name;
	private String buy_amount;
	private String buy_detail;
	private String buy_falg;//未完成，取消，完成。
	private Date create_date;
	@ManyToOne(targetEntity=Client.class)
	@JoinTable(name="client_buy",
		joinColumns=@JoinColumn(name="buy_id",referencedColumnName="buy_id",unique=true),
		inverseJoinColumns=@JoinColumn(name="client_id",referencedColumnName="client_id")
	)
	private Client myClient;
	
	
	public Client getMyClient() {
		return myClient;
	}
	public void setMyClient(Client myClient) {
		this.myClient = myClient;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getbuy_amount() {
		return buy_amount;
	}
	public void setbuy_amount(String buy_amount) {
		this.buy_amount = buy_amount;
	}
	public String getbuy_detail() {
		return buy_detail;
	}
	public void setbuy_detail(String buy_detail) {
		this.buy_detail = buy_detail;
	}
	public String getbuy_falg() {
		return buy_falg;
	}
	public void setbuy_falg(String buy_falg) {
		this.buy_falg = buy_falg;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
