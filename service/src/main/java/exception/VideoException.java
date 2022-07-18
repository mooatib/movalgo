package exception;

import org.slf4j.LoggerFactory;

public class VideoException extends Exception {

    public VideoException(String message) {
        super(message);
        LoggerFactory.getLogger(getClass()).warn(message);
    }

    public static class VideoNotFound extends VideoException {
        static String message = "Video not found";

        public VideoNotFound() {
            super(message);
            LoggerFactory.getLogger(getClass()).warn(message);
        }
    }

    public static class VideoExists extends VideoException {
        static String message = "This video already exists";

        public VideoExists() {
            super(message);
            LoggerFactory.getLogger(getClass()).warn(message);
        }
    }
}
