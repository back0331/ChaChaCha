package payment_DTO;
import java.sql.*;

public class BoardDataBean {
	private String pg;
	private String pay_method;
	private String merchant_uid;
	private String name;
	private String amount;
	private String buyer_email;
	private String buyer_name;
	private int buyer_tel;
	private String buyer_addr;
	private int buyer_postcode;
	
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getMerchant_uid() {
		return merchant_uid;
	}
	public void setMerchant_uid(String merchant_uid) {
		this.merchant_uid = merchant_uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public int getBuyer_tel() {
		return buyer_tel;
	}
	public void setBuyer_tel(int buyer_tel) {
		this.buyer_tel = buyer_tel;
	}
	public String getBuyer_addr() {
		return buyer_addr;
	}
	public void setBuyer_addr(String buyer_addr) {
		this.buyer_addr = buyer_addr;
	}
	public int getBuyer_postcode() {
		return buyer_postcode;
	}
	public void setBuyer_postcode(int buyer_postcode) {
		this.buyer_postcode = buyer_postcode;
	}
	
	
}
