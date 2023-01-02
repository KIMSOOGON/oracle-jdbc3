package controller;

import java.io.IOException;

import java.util.ArrayList;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Board;
import vo.Member;


@WebServlet("/board/boardList")
public class BoardListController extends HttpServlet {

	private BoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 로그인 안되어있을 경우 홈 컨트롤러로 이동
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
						
		// 현재페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 페이지 당 목록 수
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		
		// service 불러와서 list에 담아주기
		this.boardService = new BoardService();
		ArrayList<Board> list = null;		// list
		String word = null;					// 검색값
		String search = null;				// 검색카테고리(제목,내용,작성자)
		int ttlCntBoard = 0;				// 게시글 총개수 
		
		if(request.getParameter("word") == null) {
			// 검색값 없는 경우
			list = boardService.getBoardListByPage(currentPage, rowPerPage);
			ttlCntBoard = boardService.countBoard();
		} else {
			// 검색값이 있을 경우
			System.out.println("확인:"+request.getParameter("word"));
			System.out.println("확인:"+request.getParameter("search"));
			word = request.getParameter("word");
			search = request.getParameter("search");
			list = boardService.getBoardListByPage(currentPage, rowPerPage, search, word);
			ttlCntBoard = boardService.countBoard(search, word);
			request.setAttribute("search", search);
			request.setAttribute("word", word);
		}
		System.out.println("검색값 : "+word);
		System.out.println("검색카테고리 : "+search);
		System.out.println("검색결과 총 갯수 : "+ttlCntBoard);
		
		// 페이징을 위한 마지막페이지 값 구하기
		int lastPage = ttlCntBoard / rowPerPage;
		if(ttlCntBoard % rowPerPage != 0) {
			lastPage = lastPage +1;
		}
		System.out.println("마지막페이지 : "+lastPage);
		
		// view로 list와 함께 보내주기
		request.setAttribute("boardList", list);
		request.setAttribute("currentPage", currentPage); // view에서 필요
		request.setAttribute("rowPerPage", rowPerPage); // view에서 필요
		request.setAttribute("ttlCntBoard", ttlCntBoard);
		request.setAttribute("lastPage", lastPage);
		
		/*
		 * View메뉴구성
		 * 1) 글 입력
		 * 2) 글 상세보기
		 */
		request.getRequestDispatcher("/WEB-INF/view/board/boardList.jsp").forward(request, response);
	}
}