import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {

    public static void configurarLoggerGlobal() {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        for (Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }

        try {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter());

            FileHandler fileHandler = new FileHandler("Logs/Servidor.log", true); 
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());

            rootLogger.addHandler(consoleHandler);
            rootLogger.addHandler(fileHandler);
            rootLogger.setLevel(Level.ALL);

        } catch (IOException e) {
            rootLogger.log(Level.SEVERE, "No se pudo crear archivo de log", e);
        }
    }
}
