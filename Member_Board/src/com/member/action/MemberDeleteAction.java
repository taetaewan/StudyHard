package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commons.action.Action;
import com.commons.action.ActionForward;
import com.member.study.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		
		ActionForward forward=new ActionForward();
		
		if(id==null) {
			forward.setPath("memberLogin.me");
			forward.setRedirect(true);
			return forward;
		} else if(!id.equals("admin")){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("<script>alert('관리자가 아닙니다!');");
			out.println("location.href='boardList.bo;</script>");
			return null;
		} else {
			request.setCharacterEncoding("utf-8");
			String member_id=request.getParameter("member_id");
			MemberDAO dao= new MemberDAO();
			dao.deleteMember(member_id);
			forward.setPath("memberListAction.me");
			forward.setRedirect(true);
			return forward;
		}
	}

}
