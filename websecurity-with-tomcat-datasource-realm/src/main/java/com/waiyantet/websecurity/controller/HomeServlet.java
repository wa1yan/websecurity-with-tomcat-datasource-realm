package com.waiyantet.websecurity.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.waiyantet.websecurity.model.PostDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/search-post",
		"/home"
})
public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/postdb")
	private DataSource dataSource;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("list", PostDao.getInstance(dataSource).search(req.getParameter("keyword")));
		
		getServletContext().getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}

}
