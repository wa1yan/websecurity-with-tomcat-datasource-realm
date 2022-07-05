package com.waiyantet.websecurity.controller;

import java.io.IOException;
import javax.sql.DataSource;

import com.waiyantet.websecurity.model.MemberDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/sign-in", "/sign-up", "/sign-out" })
public class SecurityServelt extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/postdb")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var page = switch (req.getServletPath()) {
		case "/sign-in" -> "/views/sign-in.jsp";
		case "/sign-up" -> "/views/sign-up.jsp";
		case "/sign-out" -> {
			req.getSession().invalidate();
			yield null;
			}
		default -> null;
		};

		if (null == page) {
			resp.sendRedirect(getServletContext().getContextPath().concat("/home"));
		} else {
			getServletContext().getRequestDispatcher(page).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var login = req.getParameter("loginId");
		var password = req.getParameter("password");
		var name = req.getParameter("name");

		var dao = MemberDao.getInstance(dataSource);

		switch (req.getServletPath()) {
		case "/sign-in" -> {
			req.login(login, password);
			}
		case "/sign-up" -> {
			dao.signUp(login, password, name);
			req.login(login, password);
			}
		};
		req.getSession(true).setAttribute("loginUser", dao.find(login));
		resp.sendRedirect(getServletContext().getContextPath().concat("/home"));

	}
}