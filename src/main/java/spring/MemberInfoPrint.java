package spring;

public class MemberInfoPrint {
	
	//회원정보 조회해서 > MemberDao
	//출력 기능 구현   > MemberPrinter
	
	//의존객체필드 추가
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	//setter메소드 통해서 의존 객체 주입
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	
	
	//이메일과 일치하는 회원의 정보 출력 메서드
	public void printtMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			System.out.println("일치하는 회원정보 없음");
			return;
		}
		printer.print(member);
		
	}
}
