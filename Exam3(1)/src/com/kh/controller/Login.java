package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.DAO.MemberDAO;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		MemberDAO dao = new MemberDAO(); // 리턴타입이 boolean
		
		try {
			boolean result = dao.login(id, pwd);
			if(result) {
				pw.println("로그인 성공!");
			} else {
				pw.println("로그인 실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("로그인 에러!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
