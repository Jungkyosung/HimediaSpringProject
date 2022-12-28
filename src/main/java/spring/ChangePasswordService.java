package spring;

public class ChangePasswordService {
	//이메일 패스워드가 같다면 변경해준다.
	// 회원정보관리기능을 구현한 memberDao를 필드로 정의
	private MemberDao memberDao;
	//setter메서드를 통해서 의존 객체 주입
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	//pw변경 메서드를 정의
	public void ChangePassword(String email, String oldPassword, String newPassword) {
		//user 유무 확인(email검사)
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		//멤버의 패스워드를 변경
		member.changePassword(oldPassword, newPassword);
		
		//변경된 멤버정보를 Dao에 저장
		memberDao.update(member);
		
		
		
	}
}
