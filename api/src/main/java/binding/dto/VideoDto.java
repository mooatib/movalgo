package binding.dto;

import java.util.List;

public class VideoDto {
    private int id;
    private String title;
    private List<String> labels;

    public VideoDto(int id, String title, List<String> labels) {
        this.id = id;
        this.title = title;
        this.labels = labels;
    }
    public VideoDto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}