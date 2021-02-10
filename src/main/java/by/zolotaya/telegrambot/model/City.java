package by.zolotaya.telegrambot.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class City {
    private int id;
    @NotEmpty(message = "City should not be empty")
    @Size(min = 2, max = 30, message = "City should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Description should not be empty")
    private String description;

    public City() {
    }

    public City(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name) &&
                Objects.equals(description, city.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
