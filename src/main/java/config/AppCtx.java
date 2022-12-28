package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrint;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;

//스프링 설정 클래스
@Configuration
public class AppCtx {
	
	//해당 메서드가 생성한 객체를 스프링 빈으로 설정
	//메서드 이름을 빈 객체의 이름으로 사용
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		//생성자 이용 객체 주입
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		//setter이용 객체주입
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;	
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrint infoPrinter() {
		MemberInfoPrint infoPrinter = new MemberInfoPrint();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setMemberPrinter(memberPrinter());
		return infoPrinter;
	}
}
