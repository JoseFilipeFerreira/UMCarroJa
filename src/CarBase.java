import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.stream.Collectors;

public class CarBase {
    private HashMap<String, Car> carBase;

    public CarBase() {
        this.carBase = new HashMap<>();
    }

    public CarBase(@NotNull CarBase c) {
        this.carBase = (HashMap<String, Car>) c.carBase
                .values()
                .stream()
                .collect(Collectors
                        .toMap(Car::getNumberPlate, Car::clone));
    }

    /**
     * \brief Adiciona um carro à base de dados
     * @param a Carro a adicionar
     * @return True se adicionou, False se já existe
     */
    public boolean addCar(@NotNull Car a) {
        return this.carBase
                .putIfAbsent(a.getNumberPlate(), a.clone())
                != null;
    }

    /**
     * \brief Procura um carro na base de dados
     * @param numberPlate Matricula do carro a procurar
     * @return Clone do carro, Null se não existir
     */
    public Car searchCar(String numberPlate) {
        return this.carBase.get(numberPlate).clone();
    }

    /**
     * Verifica se um carro existe na base de dados
     * @param numberPlate Matricula a procurar
     * @return Se o carro existe ou não
     */
    public boolean carExixts(String numberPlate) {
        return this.carBase.containsKey(numberPlate);
    }

    /**
     * Clona um objeto da classe CarBase
     * @return Clone do objeto
     */
    public CarBase clone() {
        return new CarBase(this);
    }
}
