package project.jsp.dto;

import java.sql.Timestamp;

public class FileDto {

	int fId;
	String fName;
	String fTitle;
	String fContent;
	String fileName;
	String orgfileName;
	Timestamp fDate;
	int fHit;
	
	public FileDto() {}

	public FileDto(int fId, String fName, String fTitle, String fContent, String fileName, String orgfileName,
			Timestamp fDate, int fHit) {
		super();
		this.fId = fId;
		this.fName = fName;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fileName = fileName;
		this.orgfileName = orgfileName;
		this.fDate = fDate;
		this.fHit = fHit;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfTitle() {
		return fTitle;
	}

	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrgfileName() {
		return orgfileName;
	}

	public void setOrgfileName(String orgfileName) {
		this.orgfileName = orgfileName;
	}

	public Timestamp getfDate() {
		return fDate;
	}

	public void setfDate(Timestamp fDate) {
		this.fDate = fDate;
	}

	public int getfHit() {
		return fHit;
	}

	public void setfHit(int fHit) {
		this.fHit = fHit;
	}
	
}
