package model;

import java.util.List;
import java.util.Objects;

public class Video {
    private String id;
    private final String title;
    private final List<String> labels;

    public Video(String title, List<String> labels) {
        this.title = title;
        this.labels = labels;
    }

    public Video(String id, String title, List<String> labels) {
        this.id = id;
        this.title = title;
        this.labels = labels;
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

    public List<String> getLabels() {
        return labels;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", labels=" + labels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(title, video.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
