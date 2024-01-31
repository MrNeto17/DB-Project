package com.gameon.presentation;

import com.gameon.businessLogic.BLService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BLService srv = new BLService();
        ITest[] tests = new ITest[]{
                () -> {
                    try {
                        srv.teste0();
                    } catch (Exception ignored) {
                    }
                },
                () -> {
                    try {
                        srv.teste1();
                    } catch (Exception ignored) {
                    }
                },
                () -> {
                    try {
                        srv.teste2();
                    } catch (Exception ignored) {
                    }
                },
                () -> {
                    try {
                        srv.teste3();
                    } catch (Exception ignored) {
                    }
                },
                () -> {
                    try {
                        srv.teste4();
                    } catch (Exception ignored) {
                    }
                },
                () -> {
                    try {
                        srv.teste5();
                    } catch (Exception ignored) {
                    }
                },
        };

        Scanner imp = new Scanner(System.in);
        System.out.printf("Escolha um teste (0-%d)? ", tests.length - 1);
        int option = imp.nextInt();
        if (option >= 0 && option <= tests.length)
            tests[option].test();


    }


    protected interface ITest {
        void test();
    }
}
