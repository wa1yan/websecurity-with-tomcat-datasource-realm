package com.waiyantet.websecurity.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.waiyantet.websecurity.model.dto.MemberVO;
import com.waiyantet.websecurity.model.dto.Post;
import com.waiyantet.websecurity.model.dto.Member.Role;

public class PostDaoOImpl implements PostDao {

	private static final String SEARCH_SQL = """
											select p.id, p.title, p.content, p.creation,
											m.login, m.name, m.role
											from post p 
											inner join member m 
											on m.login = p.owner
											""";
	private static final String DELETE_SQL = "delete from post where id = ?";
	private static final String FIND_SQL = """
											select p.id, p.title, p.content, p.creation,
											m.name, m.login, m.role
											from post p
											inner join member m
											on p.owner = m.login
											where p.id = ?
											""";
	private static final String SAVE_SQL = "insert into post(title, content, owner) values (?,?,?)";
	private static final String UPDATE_SQL = "update post set title = ?, content = ? where id = ?";
	
	DataSource dataSource;

	public PostDaoOImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Post> search(String keyword) {
		var post = new ArrayList<Post>();
		var sb = new StringBuffer(SEARCH_SQL);
		
		if(null != keyword && !keyword.isEmpty()) {
			sb.append("where lower(p.title) like ? or lower(p.content) like ?");
		}
		
		try(var con = dataSource.getConnection();
				var stmt = con.prepareStatement(sb.toString())){
			
			if(null != keyword && !keyword.isEmpty()) {
				stmt.setString(1, "%".concat(keyword).toLowerCase().concat("%"));
				stmt.setString(2, "%".concat(keyword).toLowerCase().concat("%"));
			}
			
			var result = stmt.executeQuery();
			while(result.next()) {
				post.add(getPost(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return post;
	}
	
	@Override
	public void deletePostById(int postId) {
		try(var con = dataSource.getConnection();
				var stmt = con.prepareStatement(DELETE_SQL)){
			
			stmt.setInt(1, postId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Post findById(int postId) {
		
		try(var con = dataSource.getConnection();
				var stmt = con.prepareStatement(FIND_SQL)){
			
			stmt.setInt(1, postId);
			
			var result = stmt.executeQuery();
			while(result.next()) {
				return getPost(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int save(String postId, String title, String content, MemberVO loginUser) {
		
		if(postId == null || postId.isEmpty() || "0".equals(postId)) {
			return createPost(title,content,loginUser);
		}
		
		var updateId = Integer.parseInt(postId);
		
		try(var con = dataSource.getConnection();
				var stmt = con.prepareStatement(UPDATE_SQL)){
			
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setInt(3, updateId);
			
			var result =  stmt.executeUpdate();
			if(result == 1 ) {
				return updateId;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	private int createPost(String title, String content, MemberVO loginUser) {
		try(var con = dataSource.getConnection();
				var stmt = con.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
	
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, loginUser.login());
			
			stmt.executeUpdate();
			var key = stmt.getGeneratedKeys();
			while(key.next()) {
				return key.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private Post getPost(ResultSet result) {
		
		try {
			return new Post(
					result.getInt("id"),
					result.getString("title"),
					result.getString("content"),
					result.getTimestamp("creation").toLocalDateTime(),
					new MemberVO(
							result.getString("login"),
							result.getString("name"),
							Role.valueOf(result.getString("role"))
							));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
