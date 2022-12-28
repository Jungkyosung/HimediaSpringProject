package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {
	//id관리 필드
	//멤버 생성 시 하나씩 증가, 중복 방지
	private static long nextId = 0;
	
	//멤버 정보(객체)를 저장하는 맵 객체 정의
	//멤버 정보의 이메일을 키로 사용(중복방지)
	private Map<String, Member> map = new HashMap<>();
	
	// 이메일을 이용해서 멤버 조회,반환
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	//insert
	public void insert(Member member) {
		this.nextId++;
		member.setId(this.nextId);
		map.put(member.getEmail(), member);
	}
	
	//update
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	//모든 멤버의 정보 반환
	public Collection<Member> selectAll() {
		return map.values();
	}
	
}
