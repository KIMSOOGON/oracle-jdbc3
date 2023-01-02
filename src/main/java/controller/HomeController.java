package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;


@WebServlet("/home")
public class HomeController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		 * 메뉴구성(VIEW -> /WEB-INF/view/home.jsp) 
		 * 로그인 전 or 후 따라 분기
		 * 
		 * -로그인 전-
		 * 1) 회원가입
		 * 2) 로그인
		 * 
		 * -로그인 후-
		 * 1) 로그아웃
		 * 2) 회원정보
		 * 3) 게시판list
		*/
		
		// 로그인 되어있으면 로그인폼 진입 불가능
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 홈 View 보이기
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

}
