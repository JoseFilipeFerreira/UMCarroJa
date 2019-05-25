package View.ViewModel;

public class RateOwnerCar {
    private final int ownerRate;
    private final int carRate;

    public RateOwnerCar(int ownerRate, int carRate) {
        this.ownerRate = ownerRate;
        this.carRate = carRate;
    }

    public int getOwnerRate() {
        return this.ownerRate;
    }

    public int getCarRate() {
        return this.carRate;
    }
}
