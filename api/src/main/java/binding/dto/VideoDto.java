package binding.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class VideoDto {
    private String id;
    @NotNull(message = "A title must be specified")
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Labels must be specified")
    @NotEmpty(message = "Labels cannot be empty")
    private List<String> labels;

    public VideoDto(String id, String title, List<String> labels) {
        this.id = id;
        this.title = title;
        this.labels = labels;
    }

    public VideoDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
