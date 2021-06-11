package kr.or.coupon.model.vo;

public class Coupon {
	private int couponNo;
	private String couponName;
	private String couponContent;
	private String memberId;
	private String startDate;
	private String endDate;
	private String status;
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coupon(int couponNo, String couponName, String couponContent, String memberId, String startDate,
			String endDate, String status) {
		super();
		this.couponNo = couponNo;
		this.couponName = couponName;
		this.couponContent = couponContent;
		this.memberId = memberId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponContent() {
		return couponContent;
	}
	public void setCouponContent(String couponContent) {
		this.couponContent = couponContent;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
