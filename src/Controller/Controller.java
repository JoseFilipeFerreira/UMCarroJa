package Controller;

import Exceptions.InvalidUserException;
import Exceptions.UnknownCompareTypeException;
import Exceptions.WrongPasswordExecption;
import Model.*;
import Utils.Point;
import View.Menu;

import java.util.AbstractMap;
import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    private UMCarroJa model;
    private User user;
    private Menu menu;

    public Controller(UMCarroJa model) {
        this.menu = new Menu();
        this.model = model;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(this.menu.getRun()) {
            switch (menu.getMenu()) {
                case Login:
                    try {
                        AbstractMap.SimpleEntry<String, String> r = menu.newLogin();
                        user = model.logIn(Integer.parseInt(r.getKey()), r.getValue());
                        menu.selectOption((user instanceof Client)? 1 : 2);
                    }
                    catch (InvalidUserException | WrongPasswordExecption  e){ }
                    break;
                case Closest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), Car.CarType.Electric, "MaisPerto");
                        out.println(menu.createHeader());
                        out.println(rental);
                    }
                    catch (UnknownCompareTypeException e){}

                case Cheapest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), Car.CarType.Electric, "MaisBarato");
                        out.println(menu.createHeader());
                        out.println(rental);
                    }
                    catch (UnknownCompareTypeException e){}

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
            }

        }

    }
}
