package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;



public class Userdao {

	public boolean check(Connection con,String name)throws Exception{
		boolean flag=false;
		String sql="select * from user where name=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			flag=true;
		}
		return flag;
	}
}
