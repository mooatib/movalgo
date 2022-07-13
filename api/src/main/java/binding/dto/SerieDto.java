package binding.dto;

import java.util.List;

public class SerieDto extends VideoDto {
    private int numberOfEpisodes;

    public SerieDto(int id, String title, List<String> labels) {
        super(id, title, labels);
    }
    public SerieDto(){

    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
