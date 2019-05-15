import Controller.Controller;
import Model.Parser;
import Model.UMCarroJa;


public class Main {
    public static void main(String[] args) {
        UMCarroJa model = new UMCarroJa();
        new Parser("db/logsPOO_carregamentoInicial.bak", model);

        model.number();
        new Controller().run();
    }
}
