package Model;

import Exceptions.CarExistsException;
import Exceptions.NoCarAvaliableException;
import Exceptions.UnknownCompareTypeException;
import Utils.Point;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Cars implements Serializable {
    private final Map<String, Car> carBase;

    public Cars() {
        this.carBase = new HashMap<>();
    }

    private Cars(Cars c) {
        this.carBase = c.carBase
                .values()
                .stream()
                .collect(Collectors
                        .toMap(Car::getNumberPlate, Car::clone));
    }

    /**
     * \brief Adiciona um carro à base de dados
     * @param a Carro a adicionar
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
     * Clona um objeto da classe Model.Cars
     * @return Clone do objeto
     */
    public Cars clone() {
        return new Cars(this);
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

    public Car getCar(String compare, Point dest, Point origin, Car.CarType a) throws UnknownCompareTypeException, NoCarAvaliableException {
        if(compare.equals("MaisPerto")) {
            Car r = this.carBase
                    .values()
                    .stream()
                    .filter(e -> e.getType().equals(a)
                            && e.hasRange(dest))
                    .sorted(Comparator.comparingDouble(e ->
                            e.getPosition()
                            .distanceBetweenPoints(origin)))
                    .collect(Collectors.toList())
                    .get(0);
            if(r == null)
                throw new NoCarAvaliableException();
            return r;
        }

        if(compare.equals("MaisBarato")) {
            Car r = this.carBase
                    .values()
                    .stream()
                    .filter(e -> e.getType().equals(a)
                            && e.hasRange(dest)
                            && e.getPosition().distanceBetweenPoints(dest) != 0)
                    .sorted(Comparator.comparingDouble(e -> e.getBasePrice() * e.getPosition()
                            .distanceBetweenPoints(dest)))
                    .collect(Collectors.toList())
                    .get(0);
            if(r == null)
                throw new NoCarAvaliableException();
            return r;
        }

        throw new UnknownCompareTypeException();
    }

    public Car getCar(Point dest, Point origin, double range, Car.CarType a) throws NoCarAvaliableException {
        Car r = this.carBase
                .values()
                .stream()
                .filter(e -> e.getType().equals(a)
                        && e.hasRange(dest)
                        && origin.distanceBetweenPoints(e.getPosition()) <= range)
                .sorted(Comparator.comparingDouble(e -> e.getBasePrice() * origin.distanceBetweenPoints(dest)))
                .collect(Collectors.toList())
                .get(0);
        if(r == null)
            throw new NoCarAvaliableException();
        return r;
    }

    public Car getCar(Point dest, double range, Car.CarType a) throws NoCarAvaliableException {
        Car r = this.carBase
                .values()
                .stream()
                .filter(e -> e.getType().equals(a)
                        && e.hasRange(dest)
                        && e.getRange() >= range)
                .sorted(Comparator.comparingDouble(e -> e.getBasePrice() * e.getPosition()
                        .distanceBetweenPoints(dest)))
                .collect(Collectors.toList())
                .get(0);
        if(r == null)
            throw new NoCarAvaliableException();
        return r;
    }
}