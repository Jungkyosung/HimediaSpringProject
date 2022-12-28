package main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrint;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongPasswordException;

public class MainForAssembler {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("명령어를 입력하세요.");
			String command = scan.nextLine().trim();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			}
			else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			else if (command.startsWith("list")) {
				processListCommand();
				
				continue;
			}
			else if (command.startsWith("info")) {
				processInfoCommand(command.split(" "));
				
				continue;
			}
			
			printHelp();
		}
		scan.close();
	}

	

	private static void processInfoCommand(String[] args) {
		if(args.length !=2) {
			printHelp();
			return;
		}
		MemberInfoPrint InfoPrint = ctx.getBean("InfoPrint",MemberInfoPrint.class);
		InfoPrint.printMemberInfo(args[1]);
		
	}



	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}



	//객체를 생성하면 서비스에 필요한 모든 객체 생성되고 설정됨.
	//private static Assembler assembler = new Assembler();
	
	//입력받은 회원정보 등록
	private static void processNewCommand(String[] args) {
		//매개변수 개수 체크
		if(args.length != 5) {
			printHelp();
			return;
		}
		
		//getter메소드를 통해서 위의 assembler의 객체를 가져옴.
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		//패스워드와 패스워드 확인 정보가 일치하는지 확인
		if(!req.isPasswordEqualsToConfirmPassword()){
			System.out.println("패스워드 불일치");
			return;
		}
		
		try {
		regSvc.regist(req);
		System.out.println("정상 등록 완료");
	
		}catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.");
			return;
		}
	}
	//회원정보 비번 변경
	private static void processChangeCommand(String[] args) {
		//매개변수 체크
		if(args.length!=4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			changePwSvc.ChangePassword(args[1], args[2], args[3]);
			System.out.println("정상적으로 패스워드 변경완료");
		} catch(MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일 주소입니다.");
		} catch(WrongPasswordException e) {
			System.out.println("이메일, 비밀번호가 일치하지 않습니다.");
		}
		
	}
	
//	private static void processShowListCommand(String[] args) {
//		Assembler a = new Assembler();
//		MemberDao b = a.getMemberDao();
//		
//		
//		
//	}
	
	//계획되지 않은 명령어 입력시 사용법 안내
	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령어, 아래 명령어 사용법 확인!");
		System.out.println("[사용법]");
		System.out.println(">new 이메일 이름 암호 암호확인");
		System.out.println(">change 이메일 현재비번 변경비번");
		System.out.println(">list");
		System.out.println(">Info 이메일");
		System.out.println();
		
	}



}
