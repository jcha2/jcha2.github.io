package lab5;

import java.io.*;

public class CircularQueue {

	private int[] key;
	// 원형 큐이므로 이후 연산에 front나 rear 값 설정 시 % cq.max_qsize 해줘야 함.
	private int front;
	private int rear;
	private int qsize;
	private int max_qsize;

	public CircularQueue() {
		front = 0;
		rear = 0;
		qsize = 0;
		max_qsize = 0;
	}

	public static CircularQueue CreateCircularQ(int max) { // 큐 생성
		CircularQueue cq = new CircularQueue();
		cq.key = new int[max];
		cq.max_qsize = max;
		System.out.println("Queue created, max size: " + cq.max_qsize);
		return cq;
	}

	public static boolean IsEmpty(CircularQueue cq) {
		if (cq.qsize == 0) {
			return true;
		}
		return false;
	}

	public static boolean IsFull(CircularQueue cq) {
		if (cq.qsize == cq.max_qsize) {
			return true;
		}
		return false;
	}

	public static void Dequeue(CircularQueue cq) { // front 에서 delete
		if (IsEmpty(cq)) {
			System.out.println("Not Deleted : queue is empty.");
		} else {
			System.out.println("Deleted : " + cq.key[cq.front]);
			cq.front = (cq.front++) % cq.max_qsize;
			cq.qsize--;
		}
	}

	public static void Enqueue(CircularQueue cq, int num) {
		if (IsFull(cq)) {
			System.out.println("Not Inserted : queue is full.");
		} else {
			cq.key[cq.qsize++] = num;
			cq.rear = (cq.qsize - 1) % cq.max_qsize;
			System.out.println("Inserted : " + cq.key[cq.rear]);
		}
	}

	public static void PrintFirst(CircularQueue cq) {
		System.out.println("front 위치 : " + cq.front % cq.max_qsize + ", 값 : " + cq.key[cq.front % cq.max_qsize]);
	}

	public static void PrintRear(CircularQueue cq) {
		System.out.println("rear 위치 : " + cq.rear % cq.max_qsize + ", 값 : " + cq.key[cq.rear % cq.max_qsize]);
	}

	void MakeEmpty(CircularQueue cq) {
		cq.front = 0;
		cq.rear = 0;
		cq.qsize = 0;
		cq.max_qsize = 0;

	}

	public static void main(String[] args) throws IOException {
		CircularQueue queue = new CircularQueue();
		BufferedReader br = new BufferedReader(new FileReader("qexpr_input.txt"));
		String str;

		while ((str = br.readLine()) != null) {
			String[] arr = str.split(" ");

			if (arr[0].equals("e")) {
				Enqueue(queue, Integer.parseInt(arr[1]));
			} else if (arr[0].equals("d")) {
				Dequeue(queue);
			} else if (arr[0].equals("f")) {
				PrintFirst(queue);
			} else if (arr[0].equals("r")) {
				PrintRear(queue);
			} else if (arr[0].equals("n")) {
				queue = CreateCircularQ(Integer.parseInt(arr[1]));
			} else
				System.out.println("Error");

		}

		br.close();

	}
}
