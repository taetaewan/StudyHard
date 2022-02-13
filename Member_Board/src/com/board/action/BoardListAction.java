package com.board.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.study.BoardDAO;
import com.board.study.BoardDTO;
import com.commons.action.Action;
import com.commons.action.ActionForward;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		
		BoardDAO dao=new BoardDAO();
		int listCount =dao.getListCount();
		System.out.println("listCount: " + listCount);
		
		int page=1;
		int limit=10;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		ArrayList<BoardDTO> list= new ArrayList<BoardDTO>();
		list=dao.getBoardList(page, limit);
		
		int maxPage=(int)((double)listCount/10 +0.95);
		int startPage=(((int)((double)page/10+0.9))-1)*10+1;
		
		int endPage=maxPage;
		if(endPage>startPage+10 -1);{
			endPage=startPage+10-1;
		}
		
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);
		request.setAttribute("list", list);
		
		ActionForward forward=new ActionForward();
		if(id==null) {
			forward.setPath("memberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		forward.setPath("board/board_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
