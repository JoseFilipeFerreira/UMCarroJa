package Controller;

import View.Menu;

import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    Menu menu;

    public Controller() {
        this.menu = new Menu();
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(this.menu.getRun()) {
            switch (menu.getMenu()) {
                case Q1:
                    menu.back(); 
                    break;
                case Q2:
                    menu.getInputMes();
                    menu.back();
                    break;
                case Q3:
                    menu.getInputClient();
                    menu.back();
                    break;
                case Q4:
                    menu.getInputProduto();
                    menu.back();
                    break;
                case Q5:
                    menu.getInputClient();
                    menu.back();
                    break;
                case Q6:
                    menu.getInputInteiro();
                    menu.back();
                    break;
                case Q7:
                    menu.back();
                    break;
                case Q8:
                    menu.getInputInteiro();
                    menu.back();
                    break;
                case Q9:
                    menu.getInputProduto();
                    menu.back();
                    break;
                case Q10:
                    menu.back();
                    break;
                case Q1_1:
                    menu.back();
                    break;
                case Q1_2:
                    menu.back();
                    break;

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
            }

        }

    }
}
