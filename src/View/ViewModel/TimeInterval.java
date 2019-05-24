package View.ViewModel;

import java.time.LocalDateTime;

public class TimeInterval {
    private final LocalDateTime inicio;
    private final LocalDateTime fim;

    public TimeInterval(LocalDateTime inicio, LocalDateTime fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }
}
