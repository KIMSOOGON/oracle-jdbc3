package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;


@WebServlet("/board/addBoard")
public class AddBoardController extends HttpServlet {

	// 게시글 입력 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/addBoard.jsp").forward(request, response);
	}
	
	// 게시글 입력 액션
	private BoardService boardService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글인코딩
		request.setCharacterEncoding("utf-8");
		
		// 파라미터값 받아오기
		String boardTitle = request.getParameter("boardTitle");
		String memberId = request.getParameter("memberId");
		String boardContent = request.getParameter("boardContent");
		System.out.println("[게시글등록 액션]");
		System.out.println("입력된 게시글 타이틀 : "+boardTitle);
		
		Board paramBoard = new Board();
		paramBoard.setBoardTitle(boardTitle);
		paramBoard.setMemberId(memberId);
		paramBoard.setBoardContent(boardContent);
		
		this.boardService = new BoardService();
		int insertBoard = boardService.insertBoard(paramBoard);
		
		if(insertBoard == 1) {
			System.out.println("등록성공");
			response.sendRedirect(request.getContextPath()+"/board/boardList");
			return;
		} else {
			System.out.println("등록실패");
		}
	}

}
