
---
layout: post
post
title:  "Lab3 - LinkedListTest"

date:   2020-01-22 14:21:00

author: Jihun Cha
categories: Data Structure
---
###LinkedListTest

~~~package lab3;

import java.io.*;

class Node { // ����Ʈ�� ��� ��ü ����
	int studentId;
	String studentName;
	Node next;
}

class List { // ����Ʈ ����
	Node first;

	List() {
		first = null; // ��� ����. first
	}
}

public class LinkedListTest { // ���� & �޼���

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lab3_input.txt"));
		List list = new List();

		String line;

		while ((line = br.readLine()) != null) {
			String student[] = line.split(" ");
			Node newNode = new Node();
			if (student.length > 1) { // �л� �߰�, ������ ��� id�� �߰��� �޾ƿ;� ��
				newNode.studentId = Integer.parseInt(student[1]);
				if (student.length > 2) { // �߰��� ��쿡�� name���� �޾ƿ;� ��
					newNode.studentName = student[2] + " " + student[3];
				}
			}
			newNode.next = null;

			if (student[0].equals("i"))
				Insert(newNode, list);
			if (student[0].equals("d"))
				Delete(newNode, list);
			if (student[0].equals("f"))
				Find(newNode, list);
			if (student[0].equals("p"))
				Print(list);

		}

		br.close();

	}

	static void DeleteList(List list) { // ����Ʈ ����
		if (!IsEmpty(list)) {
			list.first = null;
		}
	}

	static boolean IsEmpty(List list) {
		if (list.first == null)
			return true;
		return false;
	}

	static boolean IsLast(Node node, List list) {
		if (node.next == null)
			return true;
		return false;
	}

	static void Delete(Node node, List list) { // ��� ����
		if (Find(node, list) > 0) {
			Node temp = list.first;

			while (temp.next != null) { // ����� ������ null�� �� �� �ߴܵ�
				Node left = temp;
				Node right = left.next;

				if (right.studentId == node.studentId) { // ���� ���� ��尡 ������ �����
					left.next = right.next; // �� ����� ���� ��带 �ٴ��� ���� �����ϰ�
					right.next = null; // ���� ���� null�� �����Ѵ� (����)
				}
				temp = temp.next;
			}
			System.out.println("Deletion Success : " + node.studentId);
			System.out.print("Current List> ");
			Print(list);

		} else // �ش� student ����, ���� �Ұ�
			System.out.println("Deletion Failed : element " + node.studentId + " is not in the list.");
	}

	static int Find(Node node, List list) {
		Node temp = list.first;

		while (temp != null) { // while�� �� ���鼭 id ������ 1 return
			if (temp.studentId == node.studentId) {
				return node.studentId;
			}
			temp = temp.next;
		}

		return 0;
	}

	static void Insert(Node newNode, List list) {

		if (Find(newNode, list) == 0) { // ����Ʈ�� �ش� �й� ���� ��쿡
			Node temp = list.first;

			if (list.first == null) { // ����ִ� ����Ʈ�� ��� ù ���� �߰�
				list.first = newNode;
				System.out.println("Insertion Success : " + newNode.studentId);
				System.out.print("Current List> ");
				Print(list);
			} else if (temp.studentId > newNode.studentId) { // ù ��庸�� ���� ���� ��쿡 ����Ʈ�� ù ���� �߰��ؾ� ��
				list.first = newNode;
				newNode.next = temp;
				System.out.println("Insertion Success : " + newNode.studentId);
				System.out.print("Current List> ");
				Print(list);
			} else { // ����Ʈ�� ù �κп� ���� �� �ƴ� ��쿡
				while (temp.next != null) { // �ڸ� ã�� ���
					if (temp.studentId < newNode.studentId && temp.next.studentId > newNode.studentId) {
						Node left = temp;
						Node right = temp.next;
						left.next = newNode;
						newNode.next = right;
// ���� ���left�� ������ ���right ���� ��Ƽ� left.next�� newNode�� ����Ű�� �ϰ�
// newNode.next�� right�� �����ϸ� insert �Ϸ�
						System.out.println("Insertion Success : " + newNode.studentId);
						System.out.print("Current List> ");
						Print(list);
						break;
					}
					temp = temp.next; // ����Ʈ�� �������� insert �ϴ� ���
					if (temp.next == null) {
						Node left = temp;
						left.next = newNode;
						newNode.next = null;
						System.out.println("Insertion Success : " + newNode.studentId);
						System.out.print("Current List> ");
						Print(list);
						break;
					}

				}
			}
		} else // �й� �̹� ����. insert �Ұ�
			System.out.println("Insertion Failed : element " + newNode.studentId + " is already in the list.");

	}

	static void Print(List list) {
		Node temp = list.first;
		while (temp != null) {
			System.out.print(temp.studentId + " " + temp.studentName + " ");
			temp = temp.next;
		}
		System.out.println();
	}

}~~~
