package lab3;

import java.io.*;

class Node { // 리스트의 노드 객체 생성
	int studentId;
	String studentName;
	Node next;
}

class List { // 리스트 생성
	Node first;

	List() {
		first = null; // 헤더 역할. first
	}
}

public class LinkedListTest { // 메인 & 메서드

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lab3_input.txt"));
		List list = new List();

		String line;

		while ((line = br.readLine()) != null) {
			String student[] = line.split(" ");
			Node newNode = new Node();
			if (student.length > 1) { // 학생 추가, 삭제의 경우 id를 추가로 받아와야 함
				newNode.studentId = Integer.parseInt(student[1]);
				if (student.length > 2) { // 추가의 경우에는 name까지 받아와야 함
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

	static void DeleteList(List list) { // 리스트 삭제
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

	static void Delete(Node node, List list) { // 노드 삭제
		if (Find(node, list) > 0) {
			Node temp = list.first;

			while (temp.next != null) { // 노드의 다음이 null이 될 때 중단됨
				Node left = temp;
				Node right = left.next;

				if (right.studentId == node.studentId) { // 만약 다음 노드가 삭제할 노드라면
					left.next = right.next; // 현 노드의 다음 노드를 다다음 노드로 설정하고
					right.next = null; // 다음 노드는 null로 설정한다 (삭제)
				}
				temp = temp.next;
			}
			System.out.println("Deletion Success : " + node.studentId);
			System.out.print("Current List> ");
			Print(list);

		} else // 해당 student 없음, 삭제 불가
			System.out.println("Deletion Failed : element " + node.studentId + " is not in the list.");
	}

	static int Find(Node node, List list) {
		Node temp = list.first;

		while (temp != null) { // while로 쭉 돌면서 id 같으면 1 return
			if (temp.studentId == node.studentId) {
				return node.studentId;
			}
			temp = temp.next;
		}

		return 0;
	}

	static void Insert(Node newNode, List list) {

		if (Find(newNode, list) == 0) { // 리스트에 해당 학번 없을 경우에
			Node temp = list.first;

			if (list.first == null) { // 비어있는 리스트일 경우 첫 노드로 추가
				list.first = newNode;
				System.out.println("Insertion Success : " + newNode.studentId);
				System.out.print("Current List> ");
				Print(list);
			} else if (temp.studentId > newNode.studentId) { // 첫 노드보다 값이 작을 경우에 리스트의 첫 노드로 추가해야 함
				list.first = newNode;
				newNode.next = temp;
				System.out.println("Insertion Success : " + newNode.studentId);
				System.out.print("Current List> ");
				Print(list);
			} else { // 리스트의 첫 부분에 들어가는 거 아닐 경우에
				while (temp.next != null) { // 자리 찾은 경우
					if (temp.studentId < newNode.studentId && temp.next.studentId > newNode.studentId) {
						Node left = temp;
						Node right = temp.next;
						left.next = newNode;
						newNode.next = right;
// 왼쪽 노드left와 오른쪽 노드right 각각 잡아서 left.next가 newNode를 가리키게 하고
// newNode.next을 right로 설정하면 insert 완료
						System.out.println("Insertion Success : " + newNode.studentId);
						System.out.print("Current List> ");
						Print(list);
						break;
					}
					temp = temp.next; // 리스트의 마지막에 insert 하는 경우
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
		} else // 학번 이미 존재. insert 불가
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

}
