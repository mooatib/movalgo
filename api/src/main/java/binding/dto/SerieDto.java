package binding.dto;

import java.util.List;

public class SerieDto extends VideoDto {
    private int numberOfEpisodes;

    public SerieDto(String id, String title, List<String> labels, int numberOfEpisodes) {
        super(id, title, labels);
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public SerieDto() {

    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
