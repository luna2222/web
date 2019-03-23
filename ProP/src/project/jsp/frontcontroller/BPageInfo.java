package project.jsp.frontcontroller;

public class BPageInfo {

	int boardCategory;		//게시판의 구분
	String boardName = "";
	String boardTitle = "";
	String boardContent = "";
	int totalCount;		//총 게시물의 갯수
	int listCount;		//한 페이지당 보여줄 게시물의 갯수
	int totalPage;		//총 페이지
	int curPage;		//현재 페이지
	int pageCount;		//하단에 보여줄 페이지 리스트의 갯수
	int startPage;		//시작 페이지
	int endPage;		//끝 페이지
	
	public BPageInfo() {}


	public int getBoardCategory() {
		return boardCategory;
	}


	public void setBoardCategory(int boardCategory) {
		this.boardCategory = boardCategory;
	}

	
	
	public String getBoardName() {
		return boardName;
	}


	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	
}
