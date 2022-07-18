package exception;

import org.slf4j.LoggerFactory;

public class SerieException extends Exception {

    public SerieException(String message) {
        super(message);
        LoggerFactory.getLogger(getClass()).warn(message);
    }

    public static class SerieNotFound extends SerieException {
        static String message = "Serie not found";

        public SerieNotFound() {
            super(message);
            LoggerFactory.getLogger(getClass()).warn(message);
        }
    }

    public static class SerieExists extends SerieException {
        static String message = "This serie already exists";

        public SerieExists() {
            super(message);
            LoggerFactory.getLogger(getClass()).warn(message);
        }
    }
}
