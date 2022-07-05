package com.waiyantet.websecurity.model;

import javax.sql.DataSource;

import com.waiyantet.websecurity.model.dto.MemberVO;

public interface MemberDao {

	public static MemberDao getInstance(DataSource dataSource) {
		return new MemberDaoImpl(dataSource);
	}

	public void signUp(String login, String password, String name);

	public MemberVO find(String login);
}
