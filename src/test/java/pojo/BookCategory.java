package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookCategory {

    @JsonProperty ("id")
    private String category_id;
    @JsonProperty("name")
    private String category_name;

    public String getId() {
        return category_id;
    }

    public void setId(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return category_name;
    }

    public void setName(String category_name) {
        this.category_name = category_name;
    }


    @Override
    public String toString() {
        return "BookCategory{" +
                "id='" + category_id + '\'' +
                ", name='" + category_name + '\'' +
                '}';
    }
}
