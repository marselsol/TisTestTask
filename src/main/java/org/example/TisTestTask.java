package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

public class TisTestTask {
    public static void main(String[] args) {
        fillingList(100000);
    }

    public static List<Integer> fillingList(int requiredNumberOfPrimes) {
        if (requiredNumberOfPrimes < 1) {
            return new ArrayList<>();
        }
        int amountThreads = Runtime.getRuntime().availableProcessors();
//        int sizeStack = requiredNumberOfPrimes <= amountThreads ? amountThreads : requiredNumberOfPrimes / amountThreads;
        int sizeStack = 5000;
        AtomicInteger sumLoop = new AtomicInteger();
        Set<Integer> primeNum = new ConcurrentSkipListSet<>();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < amountThreads; i++) {

            Thread thread = new Thread(() -> {
                while (requiredNumberOfPrimes >= primeNum.size()) {
                    int localSumLoop = sumLoop.get();
                    sumLoop.set(sumLoop.get() + sizeStack);
                    int finishLocalSumLoop = localSumLoop + sizeStack;
                    List<Integer> primeNumList = new ArrayList<>();
                    for (int j = localSumLoop; j <= finishLocalSumLoop; j++) {
                        if (isPrimeNumber(j)) {
                            primeNumList.add(j);
                        }
                    }
                    primeNum.addAll(primeNumList);
                }
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
        List<Integer> primeNumList = new ArrayList<>(primeNum);
        Collections.sort(primeNumList);
        return primeNumList.subList(0, requiredNumberOfPrimes);
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