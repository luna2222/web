package com.study.jsp.dao;

import javax.sql.DataSource;

public class FileDao {
	DataSource dataSource;
	
	int listCount=10;
	int pageCount=10;
	
	private static FileDao instance =new FileDao();
	
	private FileDao() {
		try {
			Context content= new InitialContent();
			dataSource =(DataSource)context.lookup("java:comp/env/jdbc/Oracle11g")
		
		}
    }
}