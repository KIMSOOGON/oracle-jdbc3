package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Board;
import vo.Member;

@WebServlet("/board/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	private BoardService boardService;
	
	// 글 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글번호 파라미터값 받아오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println("[ModifyBoard컨트롤러] 수정할 글번호 : "+boardNo);
		Board paramBoard = new Board();
		paramBoard.setBoardNo(boardNo);

		this.boardService = new BoardService();
		Board boardOne = boardService.boardOne(paramBoard);
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
				
		System.out.println("loginMember.getMemberId() : "+loginMember.getMemberId());
		System.out.println("boardOne.getMemberId() : "+boardOne.getMemberId());
		
		// 작성자 본인이 아닐경우 수정폼 진입 불가	
		if((loginMember.getMemberId()).equals(boardOne.getMemberId())) {
			System.out.println("올바른접근 (로그인계정과 글쓴이가 동일)");
			// view와 공유할 모델데이터 설정
			request.setAttribute("boardOne", boardOne);
			// view 연결
			request.getRequestDispatcher("/WEB-INF/view/board/modifyBoard.jsp").forward(request, response);
		} else {
			System.out.println("잘못된접근 (로그인계정과 글쓴이가 다릅니다)");
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
	}

	// 글 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 Controller
		request.setCharacterEncoding("utf-8");
		
		// 글번호 파라미터값 받아오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String memberId = request.getParameter("memberId");
		
		System.out.println("ModifyBoardAction컨트롤러 no값 = "+boardNo);
		System.out.println("ModifyBoardAction컨트롤러 title값 = "+boardTitle);
		
		Board paramBoard = new Board();
		paramBoard.setBoardNo(boardNo);
		paramBoard.setBoardTitle(boardTitle);
		paramBoard.setBoardContent(boardContent);
		paramBoard.setMemberId(memberId);
		
		// 2 Model
		this.boardService = new BoardService();
		int modifyBoard = boardService.modifyBoard(paramBoard);
		
		response.sendRedirect(request.getContextPath()+"/board/boardOne?boardNo="+boardNo);
		return;
	}
}
