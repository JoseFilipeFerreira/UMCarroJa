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
                case Closest_Car:
                    menu.back(); 
                    break;
                case Cheapest_Car:
                    menu.back();
                    break;
                case Cheapest_Near_Car:
                    menu.back();
                    break;
                case Specific_Car:
                    menu.back();
                    break;
                case Autonomy_Car:
                    menu.back();
                    break;
                case Free_Car:
                    menu.back();
                    break;
                case Fill_Car:
                    menu.back();
                    break;
                case Change_Price:
                    menu.back();
                    break;
                case Review_Rent:
                    menu.back();
                    break;
                case Register_Cost:
                    menu.back();
                    break;

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
            }

        }

    }
}
