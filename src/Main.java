import Controller.Controller;
import Model.Parser;
import Model.UMCarroJa;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        UMCarroJa model = new UMCarroJa();
        try {
            model = UMCarroJa.read(".tmp");
        }
        catch (IOException | ClassNotFoundException e) {
            new Parser("db/logsPOO_carregamentoInicial.bak", model);
        }
        finally {
            new Controller(model).run();
            try {
                model.save(".tmp");
            }
            catch (IOException ignored) {}
       }
    }
}
