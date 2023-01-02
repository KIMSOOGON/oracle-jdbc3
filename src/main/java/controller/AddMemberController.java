package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/member/addMember")
public class AddMemberController extends HttpServlet {
	private MemberService memberService;
	
	// 회원가입 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember != null) { // login 상태
			response.sendRedirect(request.getContextPath()+"/home");
		}
		
		if(request.getParameter("msg") != null) {
			request.setAttribute("msg", request.getParameter("msg"));
		}
		
		request.getRequestDispatcher("/WEB-INF/view/member/addMember.jsp").forward(request, response);
	}

	// 회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * redirect -> get방식 /member/login <- 컨트롤러 요청
		 * 
		 */
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		System.out.println("[회원가입 액션 컨트롤러]");
		System.out.println("입력값(아이디/패스워드/이름)==>"+memberId+"/"+memberPw+"/"+memberName);
		
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		paramMember.setMemberName(memberName);
		
		this.memberService = new MemberService();
		
		// 아이디 중복체크
		boolean checkId = memberService.insertMemberCheckId(paramMember);
		System.out.println("아이디 중복체크(true면 중복) : "+checkId);
		
		if(!checkId) {
			// 중복아닌경우 회원가입 이어서 진행
			int insertMember = memberService.insertMember(paramMember);
			if(insertMember == 1) {
				System.out.println("회원가입 성공");
				response.sendRedirect(request.getContextPath()+"/home");
				return;
			} else {
				System.out.println("회원가입 실패");
				response.sendRedirect(request.getContextPath()+"/member/addMember");
				return;
			}
		} else {
			// 중복일 경우
			System.out.println("중복된 아이디가 존재합니다");
			String msg = "overlap";
			response.sendRedirect(request.getContextPath()+"/member/addMember?msg="+msg);
			return;
		}
	}

}
