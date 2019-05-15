package Model;

import Model.Car;
import Model.Client;
import Model.Owner;
import Utils.Point;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.lang.System.out;

public class Parser {
    private List<String> file;

    public Parser() {
        this.file = new ArrayList<>();
    }

    public Parser(String db, UMCarroJa model) {
        try {
            this.file = Files
                    .readAllLines(Paths.get(db), StandardCharsets.UTF_8)
                    .stream()
                    .map(String::trim)
                    .filter(s -> !s.startsWith("--"))
                    .filter(s -> s.contains(":") && s.contains(","))
                    .map(e -> this.parseLine(e, model))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            out.println(e);
        }
    }

    private String parseLine(String l, UMCarroJa model){
        String[] pLine = l.split(":");
        String categoria = pLine[0];
        String[] content = pLine[1].split(",");
        try {
            switch (categoria) {
                case "NovoProp":
                    if (content.length != 5)
                        break;
                    model.addUser(new Owner(
                            content[2],
                            content[0],
                            content[3],
                            Integer.parseInt(content[1]),
                            content[2]
                    ));
                    break;
                case "NovoCliente":
                    if (content.length != 7)
                        break;
                    model.addUser(new Client(
                            new Point(Double.parseDouble(content[4]), Double.parseDouble(content[5])),
                            content[2],
                            content[2],
                            content[0],
                            content[3],
                            Integer.parseInt(content[1])
                    ));
                    break;
                case "NovoCarro":
                    if (content.length != 10) {
                        break;
                    }
                    model.addCar(new Car(
                            content[2],
                            Integer.parseInt(content[3]),
                            Car.fromString(content[0]),
                            Double.parseDouble(content[4]),
                            Double.parseDouble(content[5]),
                            Double.parseDouble(content[6]),
                            Integer.parseInt(content[7]),
                            new Point(Double.parseDouble(content[8]), Double.parseDouble(content[9])),
                            content[1]
                    ));
                    break;
                case "Aluguer":
                    if (content.length != 6)
                        break;
                    model.rental(Integer.parseInt(content[0]),
                            new Point(Double.parseDouble(content[1]), Double.parseDouble(content[2])),
                            Car.fromString(content[3]),
                            content[4]);
                    break;
                case "Classificar":
                    if (content.length != 3)
                        break;
                    break;
            }
        }
        catch (UserExistsException | CarExistsException | UnknownCarTypeException | UnknownCompareType i) {}
        return l;
    }

}
