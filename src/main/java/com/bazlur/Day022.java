package com.bazlur;

public class Day022 {
	public static void main(String[] args) throws InterruptedException {
		var account = new BankAccount(0);

		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				account.deposit(100);
				account.withdraw(100);
			}
		};

		var t1 = new Thread(runnable);
		var t2 = new Thread(runnable);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Available balance: " + account.getBalance());
	}
}

final class BankAccount {
	private long initialAmount;

	BankAccount(long initialAmount) {
		this.initialAmount = initialAmount;
	}

	public void withdraw(long amount) {
		deposit(-amount);
	}

	public void deposit(long amount) {
		initialAmount = initialAmount + amount;
	}

	public long getBalance() {
		return initialAmount;
	}
}