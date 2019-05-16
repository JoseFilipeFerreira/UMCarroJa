package Model;

import Exceptions.CarExistsException;
import Exceptions.UnknownCompareTypeException;
import Utils.Point;

import java.util.*;
import java.util.stream.Collectors;

public class CarBase {
    private Map<String, Car> carBase;

    public CarBase() {
        this.carBase = new HashMap<>();
    }

    public CarBase(CarBase c) {
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
    public void addCar(Car a) throws CarExistsException {
        if(this.carBase
                .putIfAbsent(a.getNumberPlate(), a.clone())
                == null)
            throw new CarExistsException();
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
     * Clona um objeto da classe Model.CarBase
     * @return Clone do objeto
     */
    public CarBase clone() {
        return new CarBase(this);
    }

    /**
     * Obtem a lista de todos os carros no sistema
     * de um determinado tipo
     * @param b Tipo a procurar
     * @return Lista dos carros
     */
    public ArrayList<Car> listOfCarType(Car.CarType b) {
        return this.carBase
                .values()
                .stream()
                .filter((e)-> e.getType() == b)
                .map(Car::clone)
                .collect(Collectors
                        .toCollection(ArrayList::new));
    }

    public Car getCar(String compare, Car.CarType type, Point dest, Point origin) throws UnknownCompareTypeException {
        if(compare.equals("MaisPerto")) return this.carBase
                .values()
                .stream()
                .filter(e -> e.hasRange(dest))
                .sorted(Comparator.comparingDouble(e ->
                        e.getPosition()
                        .distanceBetweenPoints(origin)))
                .collect(Collectors.toList())
                .get(0);

        if(compare.equals("MaisBarato")) return this.carBase
                .values()
                .stream()
                .filter(e -> e.hasRange(dest))
                .sorted(Comparator.comparingDouble(Car::getBasePrice))
                .collect(Collectors.toList())
                .get(0);

        throw new UnknownCompareTypeException();
    }
}
