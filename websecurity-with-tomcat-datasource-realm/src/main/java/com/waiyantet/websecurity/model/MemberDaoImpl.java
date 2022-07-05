package com.waiyantet.websecurity.model;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.waiyantet.websecurity.model.dto.MemberVO;
import com.waiyantet.websecurity.model.dto.Member.Role;

public class MemberDaoImpl implements MemberDao{

	private static final String FIND_SQL = "select * from member where login = ?";
	private static final String SIGN_UP_SQL = "insert into member(name, login, password) values (?,?,?)";
	
	DataSource dataSource;

	public MemberDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void signUp(String login, String password, String name) {
		try(var con = dataSource.getConnection();
				var stmt = con.prepareStatement(SIGN_UP_SQL)){
			
			stmt.setString(1, name);
			stmt.setString(2, login);
			stmt.setString(3, password);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public MemberVO find(String login) {
		
		try(var con = dataSource.getConnection();
				var stmt = con.prepareStatement(FIND_SQL)){
			stmt.setString(1, login);
			var result = stmt.executeQuery();
			
			while(result.next()) {
				return new MemberVO(
						result.getString("login"),
						result.getString("name"),
						Role.valueOf(result.getString("role"))
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
