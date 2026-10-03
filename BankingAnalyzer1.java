package Dsaproject;

import java.util.Scanner;

class Transaction {
    int id;
    double amount;
    String type;
    String status;

    Transaction(int id, double amount, String type, String status) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.status = status;
    }

    public String toString() {
        return "Transaction ID: " + id + ", Amount: " + amount + ", Type: " + type + ", Status: " + status;
    }
}

class TransactionQueue {
    private Transaction[] queue = new Transaction[5];
    private int front = 0, rear = 0, size = 0;

    public void add(Transaction t) {
        if (size < queue.length) {
            queue[rear] = t;
            rear = (rear + 1) % queue.length;
            size++;
        } else {
            queue[rear] = t;
            rear = (rear + 1) % queue.length;
            front = (front + 1) % queue.length;
        }
    }

    void printQueue() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return;
        }
        int i = front;
        for (int count = 0; count < size; count++) {
            System.out.println(queue[i]);
            i = (i + 1) % queue.length;
        }
    }
}

