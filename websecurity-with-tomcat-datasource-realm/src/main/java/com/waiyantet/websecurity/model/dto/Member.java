package com.waiyantet.websecurity.model.dto;

public record Member(
		String login,
		String password,
		String name,
		Role role
		) {

	public enum Role{
		Admin, Member
		
	}
}
