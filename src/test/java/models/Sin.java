package models;

import java.util.ArrayList;
import java.util.List;

public class Sin {

    private String title;
    private String author;
    private String message;
    private List<String> tags;

    public Sin(String title, String author, String message) {
        this.title = title;
        this.author = author;
        this.message = message;
        tags = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
