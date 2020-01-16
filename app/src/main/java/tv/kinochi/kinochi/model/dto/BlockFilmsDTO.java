package tv.kinochi.kinochi.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlockFilmsDTO {
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("values")
    @Expose
    private List<ValueDTO> values = null;
    @SerializedName("films")
    @Expose
    private List<ItemFilmDTO> films = null;
    @SerializedName("view_type")
    @Expose
    private String viewType;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ValueDTO> getValues() {
        return values;
    }

    public void setValues(List<ValueDTO> values) {
        this.values = values;
    }

    public List<ItemFilmDTO> getFilms() {
        return films;
    }

    public void setFilms(List<ItemFilmDTO> films) {
        this.films = films;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}

