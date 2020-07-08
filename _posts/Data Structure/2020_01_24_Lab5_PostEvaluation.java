
---
layout: post
post
title:  "Lab5 - Postorder Evaluation"

date:   2020-01-24 14:21:00

author: Jihun Cha
categories: Data Structure
---
### Postorder Evaluation
~~~package lab5;

import java.io.*;
import java.util.*;

public class PostEvaluation {

	private static class Stack {
		ArrayList<String> key = new ArrayList<String>();
		int top;

		void push(String newKey) { // �迭�� ���������� ����
			key.add(newKey);
			top = key.indexOf(newKey);
		}

		String pop() { // ������ ��� ������ ����, ����
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

			if (arrChar > 47 && arrChar <= 57) { // ���� ������ push
				st.push(arr[i]);
			} else { // �������� ��� ���� �� �� ������ ���� ����
				int op1 = Integer.parseInt(st.pop());
				int op2 = Integer.parseInt(st.pop());

				switch (arr[i]) { // op2 �� op1�� �ڸ��� �ٲ��� �ϹǷ� ����
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
		expr = expr.substring(0, expr.length() - 1); // # ����
		System.out.println("converted postfix form : " + expr);

		System.out.print("evaluation result : " + Evaluate(expr));

		br.close();
	}
}
