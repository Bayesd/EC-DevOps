package com.company;
import java.util.Scanner;

public class TheGame {
    static boolean isRunning = true;

    public static void main(String[] args) {
    int i = 0;
        while (isRunning) {
            Scanner sc = new Scanner(System.in);

            try {
                System.out.println("The Game is afoot.");
                boolean in = sc.nextBoolean();
                if (in) {
                    isRunning = false;
                    System.out.println("You lost The Game.");
                }  else if (i >= 1){
                    isRunning = false;
                    System.out.println("You lost The Game.");
                } else {
                    System.err.println("You are losing The Game.");
                    i++;
                }
            } catch (Exception e) {
                System.err.println("You really lost The Game.");
            }

        }
    }
}
//          https://sv.wikipedia.org/wiki/The_Game_(tankespel)