package devoca.member.model.service;

import static devoca.common.JDBCTemplate.*;

import java.sql.Connection;

import devoca.member.model.dao.MemberDAO;
import devoca.member.model.vo.Member;



public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	/** 로그인 서비스
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 * */
	public Member login(Member member) throws Exception {
		
		// Connection 얻어오기
		Connection conn = getConnection();
		
		// DAO 수행
		Member loginMember = dao.login(conn,member);
		
		System.out.println("로그인 서비스 : " + loginMember);
		System.out.println("로그인 서비스 member : " + member);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return loginMember;
		
		
	}
	
	/** 회원가입 서비스
	 * @param member
	 * @return result
	 * @throws Exception
	 * */
	public int signUp(Member member) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, member);
		
		if(result > 0)	commit(conn);  
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	/** 아이디(이메일) 중복 검사 서비스
	 * @param uid
	 * @return result
	 * @throws Exception
	 * */
	public int idDupCheck(String uid) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(conn, uid);
		
		close(conn);
		
		System.out.println("서비스 memberId : " + uid);
		System.out.println("서비스 result : " + result);
		
		return result;
		
		
	}
	
	/** 닉네임 중복 검사 Service
	 * @param memberNick
	 * @return result
	 * @throws Exception
	 */
	
	public int nicknameDupCheck(String nn) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn, nn);
		
		close(conn);
		
		return result;
	}
	
	
	/** 회원탈퇴 Service
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int secession (int memberNo, String memberPw) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberNo, memberPw);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

}
