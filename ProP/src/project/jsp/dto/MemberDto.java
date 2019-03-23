package project.jsp.dto;

import java.sql.Timestamp;

public class MemberDto {

	private String mId;
	private String mPw;
	private String mName;
	private String mEmail;
	private Timestamp mDate;
	private String mAddress;
	
	public MemberDto() {}
	
	public MemberDto(String mId, String mPw, String mName, String mEmail, Timestamp mDate, String mAddress) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mDate = mDate;
		this.mAddress = mAddress;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public Timestamp getmDate() {
		return mDate;
	}

	public void setmDate(Timestamp mDate) {
		this.mDate = mDate;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

}