package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/board/boardOne")
public class BoardOneController extends HttpServlet {
	private BoardService boardService; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * VIEW 메뉴수정
		 * 로그인멤버와 글작성자와 동일할때만 수행가능하게끔
		 * 1) 글수정
		 * 2) 글삭제
		 */
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println("[boardOne] boardNo 파라미터값 : " + boardNo);
		
		Board paramBoard = new Board();
		paramBoard.setBoardNo(boardNo);
		
		this.boardService = new BoardService();
		Board boardOne = boardService.boardOne(paramBoard);
		System.out.println("boardOne.getboardTitle : "+boardOne.getBoardTitle());
		
		// view
		request.setAttribute("boardOne", boardOne);
		request.getRequestDispatcher("/WEB-INF/view/board/boardOne.jsp").forward(request, response);
	}

}
