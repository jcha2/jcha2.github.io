package lab5;

import java.io.*;
import java.util.*;

public class PostEvaluation {

	private static class Stack {
		ArrayList<String> key = new ArrayList<String>();
		int top;

		void push(String newKey) { // 배열의 마지막으로 삽입
			key.add(newKey);
			top = key.indexOf(newKey);
		}

		String pop() { // 마지막 요소 꺼내고 리턴, 삭제
			String returnStr = key.get(top);
			key.remove(top--);
			return returnStr;
		}
	}

	static int Evaluate(String expr) {
		int result = 0;
		Stack st = new Stack();
		String arr[] = expr.split("");

		for (int i = 0; i < arr.length; i++) {
			char arrChar = arr[i].charAt(0);

			if (arrChar > 47 && arrChar <= 57) { // 숫자 만나면 push
				st.push(arr[i]);
			} else { // 연산자의 경우 숫자 두 개 꺼내여 연산 진행
				int op1 = Integer.parseInt(st.pop());
				int op2 = Integer.parseInt(st.pop());

				switch (arr[i]) { // op2 와 op1의 자리가 바뀌어야 하므로 주의
				case "*":
					result = op2 * op1;
					st.push(Integer.toString(result));
					break;
				case "/":
					result = op2 / op1;
					st.push(Integer.toString(result));
					break;
				case "%":
					result = op2 % op1;
					st.push(Integer.toString(result));
					break;
				case "+":
					result = op2 + op1;
					st.push(Integer.toString(result));
					break;
				case "-":
					result = op2 - op1;
					st.push(Integer.toString(result));
					break;
				default:
					break;
				}
			}
		}

		result = Integer.parseInt(st.pop());

		return result;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("expr_input.txt"));

		String expr = br.readLine().trim();
		expr = expr.substring(0, expr.length() - 1); // # 제거
		System.out.println("converted postfix form : " + expr);

		System.out.print("evaluation result : " + Evaluate(expr));

		br.close();
	}
}
