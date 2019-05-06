package Model;

import java.time.LocalDateTime;

public class Weather {
    private static final String seasons[] = {
            "Winter", "Winter",
            "Spring", "Spring", "Spring",
            "Summer", "Summer", "Summer",
            "Fall", "Fall", "Fall",
            "Winter"
    };

    public String getSeason() {
        return seasons[LocalDateTime.now().getMonthValue()];
    }

    public double getSeasonDelay() {
        switch (getSeason()){
            case "Summer":
                return 1.0;

            case "Spring":
                return 1.15;

            case "Fall":
                return 1.30;

            default:
                return 1.35;
        }
    }
}
