package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commons.action.Action;
import com.commons.action.ActionForward;

@WebServlet("/MemberFrontController.me")
public class MemberFrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String uri=request.getRequestURI();
		String context=request.getContextPath();
		String command=uri.substring(context.length());
		
		Action action=null;
		ActionForward forward=null;
		
		if(command.equals("/memberLogin.me")) {
			forward=new ActionForward();
			forward.setPath("member/loginForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/memberLoginAction.me")) {
			
		} else if(command.equals("/memberJoin.me")) {
			forward= new ActionForward();
			forward.setPath("member/joinForm.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/memberJoinAction.me")) {
			
		}
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher rd=request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	}
}
