import Controller.Controller;
import Model.Parser;
import View.Navigator;
import View.Table;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        new Parser().parser("db/logsPOO_carregamentoInicial.bak");

        new Controller().run();
    }
}
