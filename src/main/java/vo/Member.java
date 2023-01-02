package vo;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String updatedate;
	private String createdate;
	
	// 디폴트 생성자
	public Member() {}

	// 매개변수 가진 생성자
	public Member(String memberId, String memberPw, String memberName, String updatedate, String createdate) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.updatedate = updatedate;
		this.createdate = createdate;
	}
	
	// Override
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", updatedate=" + updatedate + ", createdate=" + createdate + "]";
	}
	
	// getter / setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	
}
