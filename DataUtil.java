package classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DataUtil {

    private static final DateTimeFormatter DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter DATA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private DataUtil() {
        // Impede instanciação
    }

    public static String getDataAtual() {
        return LocalDate.now().format(DATA);
    }

    public static String getDataHoraAtual() {
        return LocalDateTime.now().format(DATA_HORA);
    }
}