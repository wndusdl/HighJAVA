package ch10JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/*
	ȸ�� ������ �ϴ� ���α׷��� �ۼ��Ͻÿ�.(MYMEMBER���̺� �̿�)
	
	�Ʒ� �޴��� ����� ��� �����Ͻÿ�.(CRUD��� �����ϱ�) 
	�޴� ����)
		1. �ڷ� �߰�		==> INSERT(C)
		2. �ڷ� ����		==> DELETE(D)
		3. �ڷ� ����		==> UPDATE(U)
		4. ��ü �ڷ� ���	==> SELECT(R)
		0. �۾� ��.
	=====================
	
	����)
	1. �ڷ� �߰� ��ɿ��� 'ȸ��ID'�� �ߺ����� �ʴ´�.(�ߺ��Ǹ� �ٽ� �Է� �޴´�.)
	2. �ڷ� ������ 'ȸ��ID'�� �Է� �޾� ó���Ѵ�.
	3. �ڷ� �������� 'ȸ��ID'�� ������� �ʴ´�.
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
				System.out.println("����..."); 
				return;
			default:
				System.out.println("��ȣ�� �߸� �Է��߽��ϴ�. �ٽ� �Է��ϼ���.");
			}
		}
	}
	
	private int menu() {
		System.out.println("====================");
		System.out.println("1. �ڷ� �߰�");
		System.out.println("2. �ڷ� ����");
		System.out.println("3. �ڷ� ����");
		System.out.println("4. ��ü �ڷ� ���");
		System.out.println("0. �۾� ��");
		System.out.println("====================");
		System.out.print("��ȣ �Է� >> ");
		return sc.nextInt();
	}
	
	
	}
	
	
