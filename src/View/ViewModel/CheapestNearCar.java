package View.ViewModel;

import Exceptions.InvalidNewRentalException;
import Model.Car;
import Utils.Point;

public class CheapestNearCar {
        private final Point point;
        private final int walkDistance;
        private final Car.CarType type;

        public CheapestNearCar(Point point, int walkDistance, String type) throws InvalidNewRentalException {
            try {
                this.type = Car.CarType.valueOf(type.toLowerCase());
            }
            catch (IllegalArgumentException e){
                throw new InvalidNewRentalException();
            }
            this.point = point;
            this.walkDistance = walkDistance;
        }

        public Point getPoint() {
            return point;
        }

        public int getWalkDistance() {
            return walkDistance;
        }

        public Car.CarType getType() { return type; }
    }
