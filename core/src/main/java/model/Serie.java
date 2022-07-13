package model;

import java.util.List;

public class Serie extends Video {
    private final int numberOfEpisodes;

    public Serie(String title, List<String> labels, int numberOfEpisodes) {
        super(title, labels);
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Serie(int id, String title, List<String> labels, int numberOfEpisodes) {
        super(id, title, labels);
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "numberOfEpisodes=" + numberOfEpisodes +
                "} " + super.toString();
    }
}
