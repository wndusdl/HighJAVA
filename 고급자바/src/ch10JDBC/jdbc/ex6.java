package ch10JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/*
	회원 관리를 하는 프로그램을 작성하시오.(MYMEMBER테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오.(CRUD기능 구현하기) 
	메뉴 예시)
		1. 자료 추가		==> INSERT(C)
		2. 자료 삭제		==> DELETE(D)
		3. 자료 수정		==> UPDATE(U)
		4. 전체 자료 출력	==> SELECT(R)
		0. 작업 끝.
	=====================
	
	조건)
	1. 자료 추가 기능에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
	2. 자료 삭제는 '회원ID'를 입력 받아 처리한다.
	3. 자료 수정에서 '회원ID'는 변경되지 않는다.
*/
public class ex6 {
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		new ex6().start();
	}
	
	public void start() {
		while (true) {
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				//insert(); break;
			case 2:
				//delete(); break;
			case 3:
				//update(); break;
			case 4:
				//update2(); break;
			case 5:
				//select(); break;
			case 0:
				System.out.println("종료..."); 
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}
	
	private int menu() {
		System.out.println("====================");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.println("====================");
		System.out.print("번호 입력 >> ");
		return sc.nextInt();
	}
	
	
	}
	
	
