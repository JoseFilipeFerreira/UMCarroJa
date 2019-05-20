package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class RentalBase implements Serializable {
    private final ArrayList<Rental> rentalBase;

    static private int id;

    public RentalBase() {
        this.rentalBase = new ArrayList<>();
        id = -1;
    }

    public void addRental(Rental r) {
        id++;
        this.rentalBase.add(r);
    }

    /**
     * Calcula o total faturado por um carro num intervalo de tempo
     * @param carID Id do carro
     * @param init Data de inicio
     * @param end Data de fim
     * @return Total faturado
     */
    public double getTotalBilledCar(String carID, LocalDateTime init, LocalDateTime end) {
        return this.rentalBase
                .stream()
                .filter(e -> e.getCarID().equals(carID)
                        && e.getDate().isAfter(init)
                        && e.getDate().isBefore(end))
                .map(Rental::getPrice)
                .reduce(0.0, Double::sum);
    }

    /**
     * Calcula a lista de alugueres que um cliente fez num intervalo de tempo
     * @param clientID Id do cliente
     * @param init Data de inicio
     * @param end Data de fim
     * @return Lista dos alugueres
     */
    public List<Rental> getRentalListClient(String clientID, LocalDateTime init, LocalDateTime end) {
        return this.rentalBase.stream()
                .filter(e -> e.getClientID().equals(clientID)
                        && e.getDate().isBefore(end)
                        && e.getDate().isAfter(init))
                .collect(Collectors.toList());
    }

    /**
     * Calcula a lista de alugueres que um cliente
     * @param clientID Id do cliente
     * @return Lista dos alugueres
     */
    public List<Rental> getRentalListClient(String clientID) {
        return this.rentalBase
                .stream()
                .filter(e -> e.getClientID().equals(clientID))
                .collect(Collectors.toList());
    }

    /**
     * Calcula a lista de alugueres de um carro num intervalo de tempo
     * @param carID Id do carro
     * @param init Data de inicio
     * @param end Data de fim
     * @return Lista de alugueres
     */
    public List<Rental> getRentalListCar(String carID, LocalDateTime init, LocalDateTime end) {
        return this.rentalBase
                .stream()
                .filter(e -> e.getCarID().equals(carID)
                        && e.getDate().isBefore(end)
                        && e.getDate().isAfter(init))
                .collect(Collectors.toList());
    }

    public List<Rental> getRentalListCar(String carID) {
        return this.rentalBase
                .stream()
                .filter(e -> e.getCarID().equals(carID))
                .collect(Collectors.toList());
    }
}
