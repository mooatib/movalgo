package exception;

import org.slf4j.LoggerFactory;

public class FilmException extends Exception {

    public FilmException(String message) {
        super(message);
        LoggerFactory.getLogger(getClass()).warn(message);
    }

    public static class FilmNotFound extends FilmException {
        static String message = "Film not found";

        public FilmNotFound() {
            super(message);
            LoggerFactory.getLogger(getClass()).warn(message);
        }
    }

    public static class FilmExists extends FilmException {
        static String message = "This film already exists";

        public FilmExists() {
            super(message);
            LoggerFactory.getLogger(getClass()).warn(message);
        }
    }
}
