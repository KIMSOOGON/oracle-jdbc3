package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Board;
import vo.Member;

@WebServlet("/board/removeBoard")
public class RemoveBoardController extends HttpServlet {
	private BoardService boardService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println("[RemoveBoardController 진입] boardNo 값 : "+boardNo);
		
		Board paramBoard = new Board();
		paramBoard.setBoardNo(boardNo);
		
		this.boardService = new BoardService();
		Board boardOne = boardService.boardOne(paramBoard);
		paramBoard.setMemberId(boardOne.getMemberId());
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		System.out.println("loginMember.getMemberId() : "+loginMember.getMemberId());
		System.out.println("boardOne.getMemberId() : "+boardOne.getMemberId());
		
		// 작성자 본인이 아닐경우 삭제폼 진입 불가	
		if((loginMember.getMemberId()).equals(boardOne.getMemberId())) {
			System.out.println("올바른접근 (로그인계정과 글쓴이가 동일)");
			int removeBoard = boardService.removeBoard(paramBoard);
			System.out.println("게시글이 삭제되었습니다");
			response.sendRedirect(request.getContextPath()+"/board/boardList");
			return;
		} else {
			System.out.println("잘못된접근 (로그인계정과 글쓴이가 다릅니다)");
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
	}

}
