package View;

import Exceptions.InvalidNewRegisterException;
import Exceptions.InvalidNewRentalException;
import Exceptions.InvalidRatingException;
import Exceptions.InvalidTimeIntervalException;
import Model.Rental;
import Utils.Point;
import Utils.StringBetter;
import View.ViewModel.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.lang.System.out;

public class Menu{
    private MenuInd menu;
    private final Stack<MenuInd> prev;
    private final ArrayList<MenuInd> options;
    private boolean run;

    public <T> void menuNavigator(Navigator<T> nav) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            out.print("\033\143");
            out.println(this.createHeader());
            out.println(nav);
            switch (scanner.next().trim().charAt(0)) {
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
        Registar_Cliente,
        Registar_Proprietario,
        Cliente,
        Proprietario,
        Alugueres_Cliente,
        Closest_Car,
        Cheapest_Car,
        Cheapest_Near_Car,
        Specific_Car,
        Autonomy_Car,
        Alugueres_Owner,
        Review_Rent,
        Car_Overview,
        Register_Cost,
        Add_Car,
        Top_10_Clients,
        Alugueres,
        Pending_Ratings_Cli
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

    public void showRental(Rental rental) {
        Scanner scanner = new Scanner(System.in);
        out.print("\033\143");
        out.println(this.createHeader());
        out.println();
        out.println(rental);
        scanner.nextLine();
    }

    public String carOverviewShow (String error, List<List<String>> valTab){
        createMenuHeader(error);
        ArrayList<String> colLabl = new ArrayList<>();
        colLabl.add("Matricula");
        colLabl.add("Autonomia");
        colLabl.add("Preço/km");
        colLabl.add("Disponibilidade");
        colLabl.add("Ratings");
        ArrayList<String> linLabl = new ArrayList<>();
        for(int i = 0; i < valTab.size(); i++ )
            linLabl.add(String.format("%dº", i + 1));


        Table<String> tab = new Table<>(valTab,linLabl,colLabl);
        out.println(tab);
        out.println("\tR[pos] -> Refill car\n\tC[pos] [price] -> Change Price\n\tD[pos] -> Toggle Availability");

        return new Scanner(System.in).nextLine().toLowerCase();
    }

    public void rentalHistoryShow (TimeInterval ti, List<List<String>> valTab){
        this.createMenuHeader("");
        ArrayList<String> colLabl = new ArrayList<>();
        colLabl.add("Data");
        colLabl.add("Carro");
        colLabl.add("Owner");
        colLabl.add("Inicio da Viagem");
        colLabl.add("Fim da Viagem");
        colLabl.add("Preço");
        ArrayList<String> linLabl = new ArrayList<>();
        for(int i = 0; i < valTab.size(); i++ )
            linLabl.add(String.format("%d", i + 1));

        Table<String> tab = new Table<>(valTab,linLabl,colLabl);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        out.println(ti.getInicio().format(formatter) + " -> " + ti.getFim().format(formatter));
        out.println(tab);

        new Scanner(System.in).nextLine();
    }

    public AutonomyCar autonomyCarShow(String error) throws InvalidNewRentalException {
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader(error);
        out.println("Tipo do Carro: [electric, gas, hybrid or any]");
        String carType = scanner.nextLine();
        try {
            out.println("Alcance:");
            int range = scanner.nextInt();
            return new AutonomyCar(this.getDest(), range, carType);
        } catch (InputMismatchException e) {
            throw new InvalidNewRentalException();
        }
    }

    public CheapestNearCar walkingDistanceShow(String error) throws InvalidNewRentalException {
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader(error);
        out.println("Tipo do Carro: [electric, gas, hybrid or any]");
        String carType = scanner.nextLine();
        try {
            out.println("Distância a andar a pé:");
            int walk = scanner.nextInt();
            return new CheapestNearCar(this.getDest(), walk, carType);
        } catch (InputMismatchException e) {
            throw new InvalidNewRentalException();
        }
    }

    public String reviewRentShow(String error, List<String> lR){
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader(error);
        for(int i = 0; i < lR.size() && i < 4; i++) {
            out.println(i + 1 + ".");
            out.println(lR.get(i));
        }
        return scanner.nextLine().toLowerCase();
    }

    public void top10ClientsShow (List<List<String>> valTab){
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader("");
        ArrayList<String> colLabl = new ArrayList<>();
        colLabl.add("User");
        colLabl.add("Distance");
        ArrayList<String> linLabl = new ArrayList<>();
        for(int i = 1; i < 11; i++)
            linLabl.add(String.format("%dº", i));
        Table<String> tab = new Table<>(valTab,linLabl,colLabl);
        out.println(tab);

        scanner.nextLine();
    }

    public SpecificCar specificRentCarShow(String error) throws InvalidNewRentalException {
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader(error);
        out.println("Matricula:");
        String carType = scanner.nextLine();
        try {
            return new SpecificCar(this.getDest(), carType);
        } catch (InputMismatchException e) {
            throw new InvalidNewRentalException();
        }
    }

    public RentCarSimple simpleRentCarShow(String error) throws InvalidNewRentalException {
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader(error);
        out.println("Tipo do Carro: [electric, gas, hybrid or any]");
        String carType = scanner.nextLine();
        try {
            return new RentCarSimple(this.getDest(), carType);
        } catch (InputMismatchException e) {
            throw new InvalidNewRentalException();
        }
    }

    public AbstractMap.SimpleEntry<String, String> newLogin(String error) {
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader(error);
        out.println("User:");
        String user = scanner.nextLine();
        out.println("Password:");
        String password = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(user, password);
    }

    public RegisterCar newRegisterCar(String error) throws InvalidNewRegisterException {
        this.createMenuHeader(error);
        Scanner scanner = new Scanner(System.in);
        out.println("Matricula:");
        String matricula = scanner.nextLine();
        out.println("Marca:");
        String marca = scanner.nextLine();
        out.println("Tipo do Carro: [electric, gas, hybrid or any]");
        String carType = scanner.nextLine();
        try {
            out.println("Velocidade Média:");
            double avgSpeed = scanner.nextDouble();
            out.println("Preço base:");
            double basePrice = scanner.nextDouble();
            out.println("Consumo médio:");
            double gasMileage = scanner.nextDouble();
            out.println("Alcance:");
            int range = scanner.nextInt();

            return new RegisterCar(
                    matricula,
                    carType,
                    avgSpeed,
                    basePrice,
                    gasMileage,
                    range,
                    this.getDest(),
                    marca);
        } catch (InputMismatchException e) {
            throw new InvalidNewRegisterException();
        }
    }

    public RegisterUser newRegisterUser(String error) throws InvalidNewRegisterException {
        createMenuHeader(error);
        Scanner scanner = new Scanner(System.in);
        out.println("Nome de Utilizador:");
        String user = scanner.nextLine();
        out.println("Email:");
        String email = scanner.nextLine();
        out.println("Password:");
        String pass = scanner.nextLine();
        out.println("Morada:");
        String adress = scanner.nextLine();
        int nif;
        try {
            out.println("Nif:");
            nif = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            throw new InvalidNewRegisterException();
        }
        if (this.menu.equals(MenuInd.Registar_Cliente)) {
            try {
                return new RegisterUser(user, email, pass, adress, nif, this.getDest());
            }
            catch (InputMismatchException e) {
                throw new InvalidNewRegisterException();
            }
        }
        else {
            return new RegisterUser(user, email, pass, adress, nif);
        }


    }

    public Menu parser(String str) {
        if (str.matches("^[+-]?\\d{1,8}$")) {
            int i = Integer.parseInt(str);
            if (this.options.size() > i - 1 && i > 0) {
                this.prev.push(this.menu);
                this.menu = this.options.get(i - 1);
                this.correctMenu();
            }
        }
        switch (str) {
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

    public Menu selectOption(MenuInd i) {
        this.prev.push(this.menu);
        this.menu = i;
        this.correctMenu();
        return this;
    }

    public TimeInterval getTimeInterval(String error) throws InvalidTimeIntervalException{
        Scanner scanner = new Scanner(System.in);
        this.createMenuHeader(error);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            out.println("Inicio de Intervalo (yyyy-MM-dd HH:mm):");
            LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine(), formatter);

            out.println("Fim de Intervalo (yyyy-MM-dd HH:mm):");
            LocalDateTime fim = LocalDateTime.parse(scanner.nextLine(), formatter);

            return new TimeInterval(inicio, fim);
        }
        catch (DateTimeParseException e){
            throw new InvalidTimeIntervalException();
        }
    }

    public AbstractMap.SimpleEntry<Integer, Integer> pendingRateShow(String error, String pending, int total)
            throws InvalidRatingException {
        Scanner scanner = new Scanner(System.in);
        createMenuHeader(error);
        out.println(total + ".");
        out.println(pending);
        out.println();
        try {
            out.println("Rating de Owner");
            int owner = scanner.nextInt();
            if (owner < 0 || owner > 100)
                throw new InvalidRatingException();
            out.println("Rating de Carro");
            int carro = scanner.nextInt();
            if (carro < 0 || carro > 100)
                throw new InvalidRatingException();
            return new AbstractMap.SimpleEntry<>(owner, carro);
        }
        catch (InputMismatchException e){
            throw new InvalidRatingException();
        }

    }

    public boolean getRun() { return this.run; }

    public Menu back() {
        if (this.prev.size() > 0) {
            this.menu = this.prev.pop();
            this.correctMenu();
        } else {
            this.run = false;
        }
        if (this.menu.equals(MenuInd.Login) || this.menu.equals(MenuInd.Register))
            this.back();
        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\033\143");
        s.append(this.createHeader()).append("\n\n");

        for (int i = 0; i < this.options.size(); i++)
            s.append(i + 1).append("- ").append(this.menuOptionText(i)).append("\n");
        return s.toString();
    }

    private void createMenuHeader(String error) {
        out.print("\033\143");
        out.println(this.createHeader());
        out.println(new StringBetter(error).under().toString());
    }

    private Point getDest(){
        Scanner scanner = new Scanner(System.in);
        out.println("UMCarroJa wants to know your destination!");
        out.println("x:");
        double x = scanner.nextDouble();
        out.println("y:");
        double y = scanner.nextDouble();

        return new Point(x, y);
    }

    private String createHeader() {
        StringBetter strHeader = new StringBetter("\t--");
        for (MenuInd val : this.prev)
            strHeader.append(val.name()).append("/");

        return strHeader.append(this.menu.name()).append("--\n").red().toString();
    }

    private String menuOptionText(int i) {
        String r = "";
        switch (this.options.get(i)) {
            case Inicial:
                r += "Menu Inicial";
            case Register:
                r += "Registar novo utilizador";
                break;
            case Registar_Cliente:
                r += "Registar novo Cliente";
                break;
            case Registar_Proprietario:
                r += "Registar novo Proprietário";
                break;
            case Login:
                r += "Login";
                break;
            case Alugueres_Cliente:
            case Alugueres_Owner:
                r += "Histórico de alugueres";
                break;
            case Closest_Car:
                r += "Carro mais próximo das suas coordenadas";
                break;
            case Cheapest_Car:
                r += "Carro mais barato";
                break;
            case Cheapest_Near_Car:
                r += "Carro mais barato dentro de uma distância";
                break;
            case Specific_Car:
                r += "Carro específico";
                break;
            case Autonomy_Car:
                r += "Carro com uma autonomia desejada.";
                break;
            case Add_Car:
                r += "Adicionar novo carro";
                break;
            case Car_Overview:
                r += "Várias operações sobre carros";
                break;
            case Review_Rent:
                r += "Aceitar/rejeitar o aluguer de um determinado cliente;";
                break;
            case Register_Cost:
                r += "Registar quanto custou a viagem.";
                break;
            case Top_10_Clients:
                r += "UMCarroJá Challenge";
                break;
            case Alugueres:
                r += "Alugar um carro";
                break;
            case Pending_Ratings_Cli:
                r += "Avaliações pendentes";
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
            case Register:
                this.options.clear();
                this.options.add(MenuInd.Registar_Cliente);
                this.options.add(MenuInd.Registar_Proprietario);
                break;
            case Cliente:
                this.options.clear();
                this.options.add(MenuInd.Alugueres_Cliente);
                this.options.add(MenuInd.Pending_Ratings_Cli);
                this.options.add(MenuInd.Alugueres);
                this.options.add(MenuInd.Top_10_Clients);
                break;
            case Alugueres:
                this.options.clear();
                this.options.add(MenuInd.Closest_Car);
                this.options.add(MenuInd.Cheapest_Car);
                this.options.add(MenuInd.Cheapest_Near_Car);
                this.options.add(MenuInd.Specific_Car);
                this.options.add(MenuInd.Autonomy_Car);
                break;
            case Proprietario:
                this.options.clear();
                this.options.add(MenuInd.Alugueres_Owner);
                this.options.add(MenuInd.Car_Overview);
                this.options.add(MenuInd.Review_Rent);
                this.options.add(MenuInd.Register_Cost);
                this.options.add(MenuInd.Add_Car);
                break;
            case Closest_Car:
            case Cheapest_Car:
            case Cheapest_Near_Car:
            case Specific_Car:
            case Autonomy_Car:
            case Car_Overview:
            case Review_Rent:
            case Register_Cost:
            case Alugueres_Cliente:
            case Pending_Ratings_Cli:
            case Alugueres_Owner:
                this.options.clear();
                break;
        }
    }
}
