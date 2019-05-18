package Controller;

import Exceptions.InvalidUserException;
import Exceptions.UnknownCompareTypeException;
import Exceptions.UserExistsException;
import Exceptions.WrongPasswordExecption;
import Model.*;
import Utils.Point;
import Utils.StringBetter;
import View.Menu;
import View.ViewModel.Register;

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
        String error = "";
        while(this.menu.getRun()) {
            switch (menu.getMenu()) {
                case Login:
                    try {
                        AbstractMap.SimpleEntry<String, String> r = menu.newLogin(error);
                        user = model.logIn(Integer.parseInt(r.getKey()), r.getValue());
                        menu.selectOption((user instanceof Client)? Menu.MenuInd.Cliente : Menu.MenuInd.Proprietario);
                        error = "";
                        AbstractMap.SimpleEntry<String, String> r = menu.newLogin();
                        user = model.logIn(r.getKey(), r.getValue());
                        menu.selectOption((user instanceof Client)? Menu.MenuInd.Cliente : Menu.MenuInd.Proprietário);
                    }
                    catch (InvalidUserException e){
                        error = new StringBetter("Invalid Username").under().grey().toString();
                    }
                    catch (WrongPasswordExecption e){
                        error = new StringBetter("Invalid Password").under().grey().toString();
                    }
                    break;
                case Registar_Cliente:
                    try {
                        Register registerCli = menu.newRegister();
                        Client client = new Client(
                                registerCli.getPos(),
                                registerCli.getEmail(),
                                registerCli.getPasswd(),
                                registerCli.getName(),
                                registerCli.getAddress(),
                                registerCli.getNif()
                        );
                        this.model.addUser(client);
                        menu.back();
                    }
                    catch (UserExistsException e){}
                    break;
                case Registar_Proprietario:
                    try {
                        Register registerProp = menu.newRegister();
                        Owner owner = new Owner(
                                registerProp.getEmail(),
                                registerProp.getName(),
                                registerProp.getAddress(),
                                registerProp.getNif(),
                                registerProp.getPasswd()
                        );
                        this.model.addUser(owner);
                        menu.back();
                    }
                    catch (UserExistsException e){}
                    break;
                case Closest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), "MaisPerto");
                        menu.showRental(rental);
                        menu.back();
                    }
                    catch (UnknownCompareTypeException e){}
                    break;

                case Cheapest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), "MaisBarato");
                        menu.showRental(rental);
                        menu.back();
                    }
                    catch (UnknownCompareTypeException e){}
                    break;
                case Review_Rent:
                    break;
                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
                        break;
            }

        }

    }
}
