import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MegaClass {
    private CarBase cars;
    private UserBase users;
    private RentalBase rentals;

    public MegaClass() {
        this.cars = new CarBase();
        this.users = new UserBase();
        this.rentals = new RentalBase();
    }

    public List<String> getBestClients() {
        return this
                .users
                .getClientIDS()
                .stream()
                .collect(Collectors
                        .toMap((e)-> e,
                                (e) -> rentals.getRentalListClient(e)
                                        .stream()
                                        .map(Rental::getDistance)
                                        .reduce(0.0, Double::sum)))
                .entrySet()
                .stream()
                .sorted(Comparator
                        .comparing(Entry::getValue))
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }
}
