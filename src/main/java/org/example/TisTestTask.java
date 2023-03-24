package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TisTestTask {
    public static void main(String[] args) {
        multithreadedArrayFilling(111_111);
    }

    static public List<Integer> multithreadedArrayFilling(int lastNumber) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threadList = new ArrayList<>();
        int amountThreads = Runtime.getRuntime().availableProcessors();
        int sizeThread = (lastNumber / amountThreads) + 1;

        for (int i = 0; i < amountThreads; i++) {
            int k = i;
            Thread thread = new Thread(() -> {
                List<Integer> listInFori = new ArrayList<>();
                for (int j = k * sizeThread; j < k * sizeThread + sizeThread; j++) {
                    if (isPrimeNumber(j) && j <= lastNumber)
                        listInFori.add(j);
                }
                list.addAll(listInFori);
            });
            thread.start();
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(list);
        return list;
    }

    private static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        /*
        Чтобы проверить простое число или нет,
        достаточно проверить не делится ли это число на числа до квадратного корня из этого числа.
         */
        int sqrtNumber = (int) (Math.sqrt(number));
        for (int i = 2; i <= sqrtNumber; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}