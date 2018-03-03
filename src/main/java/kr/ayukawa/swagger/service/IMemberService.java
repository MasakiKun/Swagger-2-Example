package kr.ayukawa.swagger.service;

import java.util.List;

import kr.ayukawa.swagger.model.Member;

public interface IMemberService {
	List<Member> getAllMembers();
	Member getMemberByMemberId(int memberId);
	boolean insertMember(Member member);
	boolean deleteMember(Member member);
	boolean deleteMemberById(int memberId);
	boolean updateMember(Member member);
}
