package Model;

import Utils.Point;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Parser {
    private List<String> file;

    public Parser() {
        this.file = new ArrayList<>();
    }

    public void parser(String db) {
        try {
            this.file = Files
                    .readAllLines(Paths.get(db), StandardCharsets.UTF_8)
                    .stream()
                    .map(String::trim)
                    .filter(s -> !s.startsWith("--"))
                    .filter(s -> s.contains(":") && s.contains(","))
                    .collect(Collectors
                            .toList());
            out.println(file);
        } catch (IOException e) {
            out.println(e);
        }
    }

    private void parseLine(String l){
        String[] pLine = l.split(":");
        String categoria = pLine[0];
        String[] content = pLine[1].split(",");
        switch(categoria){
            case "NovoProp":
                if (content.length != 4)
                    break;
                new Owner(
                        content[2],
                        content[0],
                        content[3],
                        Integer.parseInt(content[1])
                );
                break;
            case "NovoCliente":
                if (content.length != 6)
                    break;
                new Client(
                        new Point(Integer.parseInt(content[4]), Integer.parseInt(content[5])),
                        content[2],
                        content[0],
                        content[3],
                        Integer.parseInt(content[1])
                        );
                break;
            case "NovoCarro":
                if (content.length != 9)
                    break;
                new Car(
                        content[2],
                        Integer.parseInt(content[3]),
                        content[0],
                        Double.parseDouble(content[4]),
                        Double.parseDouble(content[5]),
                        Double.parseDouble(content[6]),
                        Integer.parseInt(content[7]),
                        new Point(Integer.parseInt(content[8]), Integer.parseInt(content[9])),
                        content[1]
                        );
                break;
            case "Aluguer":
                if (content.length != 5)
                    break;
                /*nif cliente, X destino, Y destino, tipoCombustivel , preferencia*/
                break;
            case"Classificar":
                if (content.length != 2)
                    break;
                break;


        }
    }

}
