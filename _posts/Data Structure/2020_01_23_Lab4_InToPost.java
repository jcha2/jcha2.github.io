package lab4;

import java.io.*;
import java.util.*;;

public class InToPost {

	private static class Stack { // 배열로 stack 구현
		ArrayList<String> key = new ArrayList<String>();
		int top; // 배열의 마지막 부분이 top으로 작용

		void push(String newKey) { // 배열의 마지막으로 삽입
			key.add(newKey);
			top = key.indexOf(newKey);
		}

		void pop() { // 마지막 요소 꺼내고 출력 후 삭제
			if (key.get(top).equals("(")) { // 열린 괄호는 출력하지 않고 삭제
				key.remove(top--);
			} else { // 나머지의 경우 출력 후 삭제
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

			if (arrChar > 47 && arrChar <= 57) { // ASCII 사용, 숫자인 경우
				System.out.print(arr[i]);
			} else { // 기호인 경우
				if (st.key.isEmpty()) { // 빈 스택인 경우 무조건 push
					st.push(arr[i]);
				} else { // 비어있지 않은 스택
					String topStr = st.key.get(st.top);
					char topChar = topStr.charAt(0);

					if (arrChar == 41) { // 닫힌 괄호 만난 경우
						while (st.key.contains("(")) // 열린 괄호까지 pop
							st.pop();
						continue;
					}
					if (arrChar == 40) { // 열린 괄호의 경우 무조건 push
						st.push(arr[i]);
						continue;
					}
					if (Precedence(topChar) >= Precedence(arrChar)) { // 가중치 고려하여 pop & push 혹은 push
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
		default: // 괄호
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
