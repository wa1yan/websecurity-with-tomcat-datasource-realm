package com.waiyantet.websecurity.model.dto;

import com.waiyantet.websecurity.model.dto.Member.Role;

public record MemberVO(
		String login,
		String name,
		Role role
		) {

}
