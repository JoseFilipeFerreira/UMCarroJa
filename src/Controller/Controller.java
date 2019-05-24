package Controller;

import Exceptions.*;
import Model.*;
import View.Menu;
import View.ViewModel.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Controller {
    private final UMCarroJa model;
    private User user;
    private final Menu menu;

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
                        user = model.logIn(r.getKey(), r.getValue());
                        menu.selectOption((user instanceof Client)? Menu.MenuInd.Cliente : Menu.MenuInd.Proprietario);
                        error = "";
                    }
                    catch (InvalidUserException e){ error = "Invalid Username"; }
                    catch (WrongPasswordExecption e){ error = "Invalid Password"; }
                    break;
                case Registar_Cliente:
                    try {
                        RegisterUser registerUserCli = menu.newRegisterUser(error);
                        Client client = new Client(
                                registerUserCli.getPos(),
                                registerUserCli.getEmail(),
                                registerUserCli.getPasswd(),
                                registerUserCli.getName(),
                                registerUserCli.getAddress(),
                                registerUserCli.getNif()
                        );
                        this.model.addUser(client);
                        menu.back();
                        error = "";
                    }
                    catch (InvalidNewRegisterException e){ error = "Parametros Inválidos"; }
                    catch (UserExistsException e){ error = "Utilizador já existe"; }
                    break;
                case Registar_Proprietario:
                    try {
                        RegisterUser registerUserProp = menu.newRegisterUser(error);
                        Owner owner = new Owner(
                                registerUserProp.getEmail(),
                                registerUserProp.getName(),
                                registerUserProp.getAddress(),
                                registerUserProp.getNif(),
                                registerUserProp.getPasswd()
                        );
                        this.model.addUser(owner);
                        menu.back();
                        error = "";
                    }
                    catch (InvalidNewRegisterException e){ error = "Parametros Inválidos"; }
                    catch (UserExistsException e){ error = "Utilizador já existe"; }
                    break;
                case Closest_Car:
                    try{
                        RentCarSimple rent = menu.simpleRentCarShow(error);
                        Rental rental = model.rental(
                                (Client)user,
                                rent.getPoint(),
                                "MaisPerto",
                                rent.getCarType());
                        menu.showRental(rental);
                        menu.back();
                        error = "";
                    }
                    catch (UnknownCompareTypeException e) {}
                    catch (NoCarAvaliableException e) { error = "No cars availables"; }
                    catch (InvalidNewRentalException e){error = "Novo Rental inválido"; }
                    break;
                case Cheapest_Car:
                    try{
                        RentCarSimple rent = menu.simpleRentCarShow(error);
                        Rental rental = model.rental(
                                (Client)user,
                                rent.getPoint(),
                                "MaisBarato",
                                rent.getCarType());
                        menu.showRental(rental);
                        menu.back();
                        error = "";
                    }
                    catch (UnknownCompareTypeException e) {}
                    catch (NoCarAvaliableException e) { error = "No cars availables"; }
                    catch (InvalidNewRentalException e){error = "Novo Rental inválido"; }
                    break;
                case Review_Rent:
                    Owner owner = (Owner)this.user;
                    ArrayList<Rental> lR = owner.getPending();
                    if (lR.size() == 0){
                        this.menu.back();
                        break;
                    }
                    String v = menu.reviewRentShow(
                            error,
                            lR.stream()
                                    .map(Rental::toString)
                                    .collect(Collectors.toList()));
                    try {
                        switch (v.charAt(0)) {
                            case 'a':
                                this.model.rent(lR.get(Integer.parseInt(v.substring(1)) - 1));
                                break;
                            case 'r':
                                this.model.refuse(owner, lR.get(Integer.parseInt(v.substring(1)) - 1));
                                break;
                            case 'b':
                                this.menu.back();
                                break;
                        }
                        error = "";
                    }
                    catch(NumberFormatException | IndexOutOfBoundsException e){error = "Input Inválido";}
                    break;

                case Cheapest_Near_Car:
                    try{
                        CheapestNearCar walkCar = menu.walkingDistanceShow(error);

                        Rental rental = model.rental(
                                (Client)user,
                                walkCar.getPoint(),
                                walkCar.getWalkDistance(),
                                walkCar.getType()
                        );

                        this.menu.showRental(rental);
                        this.menu.back();
                        error = "";
                    }
                    catch (InvalidNewRentalException e){error = "New rental inválido";}
                    catch (NoCarAvaliableException e)  {error = "No cars availables"; }
                    break;

                case Autonomy_Car:
                    try{
                        AutonomyCar autoCar = menu.autonomyCarShow(error);

                        Rental rental = model.rental(
                                autoCar.getPoint(),
                                autoCar.getAutonomy(),
                                autoCar.getType(),
                                (Client)user);

                        menu.showRental(rental);
                        this.menu.back();
                        error = "";
                    }
                    catch (InvalidNewRentalException e){error = "New rental inválido";}
                    catch (NoCarAvaliableException e) { error = "No cars availables"; }
                    break;

                case Specific_Car:
                    try {
                        SpecificCar sc = this.menu.specificRentCarShow(error);
                        Rental rental = this.model.rental(sc.getPoint(), sc.getNumberPlate(), (Client)user);
                        this.menu.showRental(rental);
                        this.menu.back();
                        error = "";
                    }
                    catch (NoCarAvaliableException e) { error = "Carro não está disponível"; }
                    catch (InvalidCarException e) { error = "Carro não existe"; }
                    catch (InvalidNewRentalException e) { error = "Invalid Parameters"; }
                    break;

                case Add_Car:
                    try {
                        RegisterCar registerCar = menu.newRegisterCar(error);
                        Owner ownerCar = (Owner)this.user;
                        model.addCar(
                                ownerCar,
                                registerCar.getNumberPlate(),
                                registerCar.getType(),
                                registerCar.getAvgSpeed(),
                                registerCar.getBasePrice(),
                                registerCar.getGasMileage(),
                                registerCar.getRange(),
                                registerCar.getPos(),
                                registerCar.getBrand()
                        );
                        menu.back();
                        error = "";
                    }
                    catch (InvalidNewRegisterException e){ error = "Parametros Inválidos"; }
                    catch (CarExistsException e){ error = "Carro já existe"; }
                    catch (InvalidUserException ignored) {}
                    break;

                case Top_10_Clients:
                    menu.top10ClientsShow(this.model.getBestClients()
                            .stream()
                            .map(x ->
                                    Arrays.asList(
                                            x.getKey(),
                                            String.format("%.2f", x.getValue()))
                            ).collect(Collectors.toList()));
                    this.menu.back();
                    break;

                case Car_Overview:
                    Owner ownerCar = (Owner)this.user;
                    String action = this.menu.carOverviewShow(error,
                            ownerCar.getCars().stream()
                            .map(x -> Arrays.asList(x.toString().split("\n")))
                            .collect(Collectors.toList()));
                    try {
                        switch (action.charAt(0)) {
                            case ' ':
                                break;
                            case 'r':
                                model.refil(ownerCar, Integer.parseInt(action.substring(1)) - 1);
                                break;
                            case'c':
                                String [] s = action.substring(1).split(" ");
                                if (s.length != 2)
                                    throw new InvalidNumberOfArgumentsException();
                                model.setBasePrice(ownerCar, Integer.parseInt(s[0]) - 1, Double.parseDouble(s[1]));
                                break;
                            case 'd':
                                model.swapState(ownerCar, Integer.parseInt(action.substring(1)) - 1);
                                break;
                            case 'b':
                                this.menu.back();
                                break;
                        }
                        error = "";
                    }
                    catch (IndexOutOfBoundsException e){ error = "Valor de carro inválido"; }
                    catch (NumberFormatException e){ error = "Posição inválida"; }
                    catch (InvalidNumberOfArgumentsException e) {error = "Invalid number of parameters";}
                    break;

                case Pending_Ratings_Cli:
                    try {
                        Client cli = (Client) user;

                        List<Rental> pR = cli.getPendingRates();

                        if (pR.size() == 0) {
                            this.menu.back();
                            break;
                        }
                        List<Rental> pR = cli.getPendingRates();

                        AbstractMap.SimpleEntry<Integer, Integer> r =
                                this.menu.pendingRateShow(error, pR.get(0).toString(), pR.size());
                      
                        model.rate(cli, pR.get(0), r.getKey(), r.getValue());

                        error = "";
                    }
                    catch (InvalidRatingException e){error = "Parametros Invalidos";}
                    break;

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
                        break;
            }
        }
    }
}
