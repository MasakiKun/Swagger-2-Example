package kr.ayukawa.swagger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.ayukawa.swagger.model.Member;

@Service(value="memberService")
public class MemberService implements IMemberService {
	private List<Member> members = new ArrayList<>();
	{
		int id = 0;
		members.add(new Member(id++, "Jack", "male", 32, "Programmer"));
		members.add(new Member(id++, "Scarlett", "female", 28, "Movie Actor"));
		members.add(new Member(id++, "Hanson", "male", 22, "College Student"));
		members.add(new Member(id++, "Peter", "male", 28, "Programmer"));
		members.add(new Member(id++, "Jimmy", "male", 35, "Police"));
		members.add(new Member(id++, "Alice", "female", 30, "Doctor"));
	}

	@Override
	public List<Member> getAllMembers() {
		return members;
	}

	@Override
	public Member getMemberByMemberId(int memberId) {
		Member member = members.stream()
				.filter(m -> m.getMemberId() == memberId)
				.findFirst().orElse(null);
		
		return member;
	}
	
	@Override
	public boolean insertMember(Member member) {
		Member maxMember = members.stream()
				.max((m1, m2) -> Integer.compare(m1.getMemberId(), m2.getMemberId()))
				.orElse(null);
		
		int id = 0;
		if(maxMember != null) {
			id = maxMember.getMemberId() + 1;
		}
		
		member.setMemberId(id);
		members.add(member);
		
		return true;
	}
	
	@Override
	public boolean deleteMember(Member member) {
		Member obj = members.stream()
				.filter(m -> m.getMemberId() == member.getMemberId())
				.findFirst().orElse(null);

		if(obj != null)
			members.remove(obj);
		
		return true;
	}
	
	@Override
	public boolean deleteMemberById(int memberId) {
		Member obj = members.stream()
				.filter(m -> m.getMemberId() == memberId)
				.findFirst().orElse(null);
		
		if(obj != null) {
			members.remove(obj);
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public boolean updateMember(Member member) {
		Member obj = members.stream()
				.filter(m -> m.getMemberId() == member.getMemberId())
				.findFirst().get();
		
		if(member != null) {
			obj.setName(member.getName());
			obj.setGender(member.getGender());
			obj.setAge(member.getAge());
			obj.setJob(member.getJob());
		}
		
		return true;
			
	}
}
