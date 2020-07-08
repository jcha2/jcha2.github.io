package lab4;

import java.io.*;
import java.util.*;;

public class InToPost {

	private static class Stack { // �迭�� stack ����
		ArrayList<String> key = new ArrayList<String>();
		int top; // �迭�� ������ �κ��� top���� �ۿ�

		void push(String newKey) { // �迭�� ���������� ����
			key.add(newKey);
			top = key.indexOf(newKey);
		}

		void pop() { // ������ ��� ������ ��� �� ����
			if (key.get(top).equals("(")) { // ���� ��ȣ�� ������� �ʰ� ����
				key.remove(top--);
			} else { // �������� ��� ��� �� ����
				System.out.print(key.get(top));
				key.remove(top--);
			}
		}
	}

	private static void Convert(String inExpr) {

		Stack st = new Stack();
		st.key.clear();
		String[] arr = inExpr.split("");

		for (int i = 0; i < arr.length; i++) {
			char arrChar = arr[i].charAt(0);

			if (arrChar > 47 && arrChar <= 57) { // ASCII ���, ������ ���
				System.out.print(arr[i]);
			} else { // ��ȣ�� ���
				if (st.key.isEmpty()) { // �� ������ ��� ������ push
					st.push(arr[i]);
				} else { // ������� ���� ����
					String topStr = st.key.get(st.top);
					char topChar = topStr.charAt(0);

					if (arrChar == 41) { // ���� ��ȣ ���� ���
						while (st.key.contains("(")) // ���� ��ȣ���� pop
							st.pop();
						continue;
					}
					if (arrChar == 40) { // ���� ��ȣ�� ��� ������ push
						st.push(arr[i]);
						continue;
					}
					if (Precedence(topChar) >= Precedence(arrChar)) { // ����ġ ����Ͽ� pop & push Ȥ�� push
						st.pop();
						st.push(arr[i]);
					} else
						st.push(arr[i]);

				}
			}
		}
		while (!st.key.isEmpty())
			st.pop();
	}

	private static int Precedence(char ch) {
		switch (ch) {
		case 37: // %, *, /
		case 42:
		case 47:
			return 2;
		case 43: // +, -
		case 45:
			return 1;
		default: // ��ȣ
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("expr_input.txt"));

		String inExpr = br.readLine().trim();
		inExpr = inExpr.substring(0, inExpr.length() - 1);
		System.out.println("original infix form : " + inExpr);

		System.out.print("converted postfix form : ");
		Convert(inExpr);

		br.close();
	}

}
