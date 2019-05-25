package View.ViewModel;

public class RateOwnerCar {
    private int ownerRate;
    private int carRate;

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
