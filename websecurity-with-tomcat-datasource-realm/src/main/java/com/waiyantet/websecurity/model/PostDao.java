package com.waiyantet.websecurity.model;

import java.util.List;

import javax.sql.DataSource;

import com.waiyantet.websecurity.model.dto.MemberVO;
import com.waiyantet.websecurity.model.dto.Post;

public interface PostDao {

	public static PostDao getInstance(DataSource dataSource) {
		return new PostDaoOImpl(dataSource);
	}

	public void deletePostById(int postId);

	public Post findById(int parseInt);

	public List<Post> search(String keyword);

	public int save(String postId, String title, String content, MemberVO loginUser);

}
