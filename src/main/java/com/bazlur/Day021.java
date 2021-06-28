package com.bazlur;

public class Day021 {
	static boolean running = true;

	public static void main(String[] args) {
		var t1 = new Thread(() -> {
			while (running) {
			}
			System.out.println("from the other side");
		});

		var t2 = new Thread(() -> {
			running = false;
			System.out.println("Hello");
		});
		t1.start();
		t2.start();
	}
}
