package pl.sda.todoapp.model;

import javax.validation.constraints.*;

public class TodoDto {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 255)
    private String text;

    private String createDate;


    public TodoDto(Long id, String text, String createDate) {
        this.id = id;
        this.text = text;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
