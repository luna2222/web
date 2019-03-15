package com.study.jsp.command;

public class BPageInfo {
 int totalCount; //총개시물의 갯수
 int listCount; // 한페이지당 보여줄 게시물릐 갯수
 int totalPage;// 총페이지
 int curPage; //현재페이지
 int pageCount;//하단의 보여줄페이지 리스트 갯수
 int startPage; //시작 페이지
 int endPage;//끝 페이지
 
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
