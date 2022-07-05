package com.waiyantet.websecurity.model.dto;

import java.time.LocalDateTime;

public record Post(
		int id,
		String title,
		String content,
		LocalDateTime creation,
		MemberVO owner
	) {

}
