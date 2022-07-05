package com.waiyantet.websecurity.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.waiyantet.websecurity.model.PostDao;
import com.waiyantet.websecurity.model.dto.MemberVO;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/show-detail",
		"/post/edit",
		"/post/delete"
})
public class PostServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/postdb")
	private DataSource dataSource;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var postId = req.getParameter("postId");
		var dao = PostDao.getInstance(dataSource);
		
		var page = switch (req.getServletPath()){
		case "/post/edit" -> "/views/edit-post.jsp";
		case "/show-detail" -> "/views/detail.jsp";
		case "/post/delete" -> {
			if(postId != null && !postId.isEmpty()) {
				dao.deletePostById(Integer.parseInt(req.getParameter("postId")));
			}
			yield null;
		}
		default ->null;
		};
		
		if(null == page) {
			resp.sendRedirect(getServletContext().getContextPath().concat("/home"));
		} else {
			if(null != postId && !postId.isEmpty() && !"0".equals(postId)) {
				var post = dao.findById(Integer.parseInt(req.getParameter("postId")));
				req.setAttribute("post", post);
			}
			getServletContext().getRequestDispatcher(page).forward(req, resp);
		}	
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVO loginUser = (MemberVO)req.getSession().getAttribute("loginUser");
		
		var postId = req.getParameter("postId");
		var title = req.getParameter("title");
		var content = req.getParameter("content");
		var dao = PostDao.getInstance(dataSource);
		
		//save to database
		var saveId = dao.save(postId, title, content, loginUser);

		var redirectUrl = getServletContext().getContextPath().concat("/show-detail?postId=" + saveId);
		
		resp.sendRedirect(redirectUrl);
		
		
	}
}
