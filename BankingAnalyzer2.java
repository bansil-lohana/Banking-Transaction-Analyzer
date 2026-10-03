package Dsaproject;

import java.util.Scanner;

public class BankingAnalyzer2 {

    // Linear search
    public static Transaction linearSearch(Transaction[] arr, int n, int id) {
        for (int i = 0; i < n; i++) {
            if (arr[i].id == id) return arr[i];
        }
        return null;
    }

    // Binary search (array must be sorted by ID)
    public static Transaction binarySearch(Transaction[] arr, int n, int id) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].id == id) return arr[mid];
            else if (arr[mid].id < id) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
    public static void viewAllTransactions(Transaction[] arr, int n) {
        System.out.println("\nAll Transactions:");
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }


    // Merge sort (sort by amount)
    public static void mergeSort(Transaction[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(Transaction[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Transaction[] L = new Transaction[n1];
        Transaction[] R = new Transaction[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].amount <= R[j].amount) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Print failed transactions
    public static void printFailedTransactions(Transaction[] arr, int n) {
        System.out.println("\nFailed Transactions:");
        for (int i = 0; i < n; i++) {
            if (arr[i].status.equalsIgnoreCase("Failed")) {
                System.out.println(arr[i]);
            }
        }
    }

 
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Transaction[] transactions = {
                new Transaction(101, 5000, "Deposit", "Success"),
                new Transaction(102, 3000, "Withdraw", "Failed"),
                new Transaction(103, 7000, "Deposit", "Success"),
                new Transaction(104, 2000, "Withdraw", "Failed"),
                new Transaction(105, 9000, "Deposit", "Success"),
                new Transaction(106, 1500, "Withdraw", "Success"),
                new Transaction(107, 8500, "Deposit", "Success")
        };
        int n = transactions.length;
        TransactionQueue q = new TransactionQueue();
        for (int i = 0; i < n; i++) {
            q.add(transactions[i]);
        }
        System.out.println("\n===== BANKING TRANSACTION ANALYZER =====");
        System.out.println("1) Search Transaction (Linear Search)");
        System.out.println("2) Search Transaction (Binary Search)");
        System.out.println("3) Sort Transactions by Amount");
        System.out.println("4) View Failed Transactions");
        System.out.println("5) View Last 5 Transactions");
        System.out.println("6) View All Transactions");
        System.out.println("⿧ Exit");
        int choice;
        do {
            System.out.print("Enter your choice(1-7): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Transaction ID to search: ");
                    int id1 = sc.nextInt();
                    Transaction result1 = linearSearch(transactions, n, id1);
                    System.out.println(result1 != null ? result1 : "Transaction not found.");
                    break;
                    case 2:
                    System.out.print("Enter Transaction ID to search: ");
                    int id2 = sc.nextInt();
                    Transaction result2 = binarySearch(transactions, n, id2);
                    System.out.println(result2 != null ? result2 : "Transaction not found.");
                    break;
                    case 3:
                    mergeSort(transactions, 0, n - 1);
                    System.out.println("\nTransactions Sorted by Amount:");
                    for (int i = 0; i < n; i++) {
                        System.out.println(transactions[i]);
                    }
                    break;
                    case 4:
                    printFailedTransactions(transactions, n);
                    break;
                    case 5:
                        q.printQueue();
                    break;
                    case 6:
                    viewAllTransactions(transactions, n);
                    break;
                    case 7:
                    System.out.println("Exiting program... Thank you!");
                    break;
                    default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 7);
    }
}
