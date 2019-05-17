package View;
import Model.Rental;
import Utils.StringBetter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.out;

public class Menu implements IMenu{
    private MenuInd menu;
    private Stack<MenuInd> prev;
    private ArrayList<MenuInd> options;
    private boolean run;

    public <T> void menuNavigator(Navigator<T> nav){
        Scanner scanner = new Scanner(System.in);
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
            out.println(nav);
            switch (scanner.next().trim().charAt(0)){
                case 'n':
                    nav.next();
                    break;
                case 'p':
                    nav.previous();
                    break;
                case 'b':
                    this.back();
                    return;
            }
        }
    }

    public enum MenuInd {
        Inicial,
        Login,
        Register,
        Cliente,
        Proprietário,
        Closest_Car,
        Cheapest_Car,
        Cheapest_Near_Car,
        Specific_Car,
        Autonomy_Car,
        Free_Car,
        Fill_Car,
        Change_Price,
        Review_Rent,
        Register_Cost
    }

    public Menu() {
        this.menu = MenuInd.Inicial;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }

    public Menu(MenuInd menuInd) {
        this.menu = menuInd;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }

    public MenuInd getMenu() {
        return this.menu;
    }

    public void showRental(Rental rental){
        Scanner scanner = new Scanner(System.in);
        out.print("\033\143");
        out.println(this.createHeader());
        out.println();
        out.println(rental);
        scanner.nextLine();
    }

    public AbstractMap.SimpleEntry<String, String> newLogin(){
        Scanner scanner = new Scanner(System.in);
        out.print("\033\143");
        out.println((this.createHeader()));
        out.println("User:");
        String user = scanner.nextLine();
        out.println("Password:");
        String password = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(user, password);
    }

    public int getInputInteiro(){
        Scanner scanner = new Scanner(System.in);
        String str;
        boolean error = false;
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
            if (error)
                out.println(new StringBetter("Inteiro Inválido").under().toString());
            else
                out.println();
            out.println("Inserir Inteiro: ");
            str = scanner.nextLine();
            if (str.matches("^[+-]?\\d{1,8}$"))
                return Integer.parseInt(str);
            else
                error = true;
        }
    }

    public Menu parser(String str){
        if (str.matches("^[+-]?\\d{1,8}$")) {
            this.selectOption(Integer.parseInt(str));
        }
        switch (str){
            case "b":
            case "..":
                this.back();
                break;
            case "e":
                this.run = false;
                break;
        }

        return this;
    }

    public boolean getRun() {
        return this.run;
    }

    public Menu selectOption(int i){
        if (this.options.size() > i - 1) {
            this.prev.push(this.menu);
            this.menu = this.options.get(i - 1);
            this.correctMenu();
        }
        return this;
    }

    public Menu selectOption(MenuInd i){
        this.prev.push(this.menu);
        this.menu = i;
        this.correctMenu();
        return this;
    }

    public Menu back(){
        if (this.prev.size() > 0) {
            this.menu = this.prev.pop();
            this.correctMenu();
        }
        else {
            this.run = false;
        }
        if (this.menu.equals(MenuInd.Login))
            this.back();
        return this;
    }

    private String createHeader(){
        StringBetter strHeader = new StringBetter("\t--");
        for (MenuInd val : this.prev)
            strHeader.append(val.name()).append("/");

        return strHeader.append(this.menu.name()).append("--\n").red().toString().replace('_', ' ');
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\033\143");
        s.append(this.createHeader()).append("\n\n");

        for(int i = 0; i < this.options.size(); i++)
            s.append(i + 1).append("- ").append(this.menuOptionText(i)).append("\n");
        s.append("\n");
        return s.toString();
    }

    private String menuOptionText(int i) {
        String r = "";
        switch (this.options.get(i)){
            case Inicial:
                r += "Menu Inicial";
            case Register:
                r += "Registar novo utilizador";
                break;
            case Login:
                r += "Login";
                break;
            case Closest_Car:
                r += "Carro mais próximo das suas coordenadas";
                break;
            case Cheapest_Car:
                r += "Carro mais barato";
                break;
            case Cheapest_Near_Car:
                r += "Carro mais barato dentro de uma distância que estão dispostos a andar a pé";
                break;
            case Specific_Car:
                r += "Carro específico";
                break;
            case Autonomy_Car:
                r += "Carro com uma autonomia desejada.";
                break;
            case Free_Car:
                r += "Sinalizar que um dos seus carros está disponível para aluguer";
                break;
            case Fill_Car:
                r += "Abastecer o veiculo";
                break;
            case Change_Price:
                r += "Alterar o preço por km";
                break;
            case Review_Rent:
                r += "Aceitar/rejeitar o aluguer de um determinado cliente;";
                break;
            case Register_Cost:
                r += "Registar quanto custou a viagem.";
                break;
        }
        return r;
    }

    private void correctMenu() {
        switch (this.menu) {
            case Inicial:
                this.options.clear();
                this.options.add(MenuInd.Login);
                this.options.add(MenuInd.Register);
                break;
            case Login:
                this.options.clear();
                break;
            case Cliente:
                this.options.clear();
                this.options.add(MenuInd.Closest_Car);
                this.options.add(MenuInd.Cheapest_Car);
                this.options.add(MenuInd.Cheapest_Near_Car);
                this.options.add(MenuInd.Specific_Car);
                this.options.add(MenuInd.Autonomy_Car);
                break;
            case Proprietário:
                this.options.clear();
                this.options.add(MenuInd.Free_Car);
                this.options.add(MenuInd.Fill_Car);
                this.options.add(MenuInd.Change_Price);
                this.options.add(MenuInd.Review_Rent);
                this.options.add(MenuInd.Register_Cost);
                break;
            case Closest_Car:
                this.options.clear();
                break;
            case Cheapest_Car:
                this.options.clear();
                break;
            case Cheapest_Near_Car:
                this.options.clear();
                break;
            case Specific_Car:
                this.options.clear();
                break;
            case Autonomy_Car:
                this.options.clear();
                break;
            case Free_Car:
                this.options.clear();
                break;
            case Fill_Car:
                this.options.clear();
                break;
            case Change_Price:
                this.options.clear();
                break;
            case Review_Rent:
                this.options.clear();
                break;
            case Register_Cost:
                this.options.clear();
                break;
        }
    }
}
