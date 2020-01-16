package tv.kinochi.kinochi.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmCardDTO {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("name_ru")
    @Expose
    private String nameRu;
    @SerializedName("poster_url")
    @Expose
    private String posterUrl;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("rating_kinochi_positive")
    @Expose
    private int ratingKinochiPositive;
    @SerializedName("rating_kinochi_negative")
    @Expose
    private int ratingKinochiNegative;
    @SerializedName("rating_imdb")
    @Expose
    private double ratingImdb;
    @SerializedName("rating_kinopoisk")
    @Expose
    private double ratingKinopoisk;
    @SerializedName("update_datetime")
    @Expose
    private String updateDatetime;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("stills")
    @Expose
    private List<String> stills = null;
    @SerializedName("related")
    @Expose
    private List<ItemFilmDTO> filmsRelated = null;
    @SerializedName("genres")
    @Expose
    private List<ItemCategoryDTO> categories = null;
    @SerializedName("media_type")
    @Expose
    private int mediaType;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }


    public int getRatingKinochiPositive() {
        return ratingKinochiPositive;
    }

    public void setRatingKinochiPositive(int ratingKinochiPositive) {
        this.ratingKinochiPositive = ratingKinochiPositive;
    }

    public int getRatingKinochiNegative() {
        return ratingKinochiNegative;
    }

    public void setRatingKinochiNegative(int ratingKinochiNegative) {
        this.ratingKinochiNegative = ratingKinochiNegative;
    }

    public double getRatingImdb() {
        return ratingImdb;
    }

    public void setRatingImdb(double ratingImdb) {
        this.ratingImdb = ratingImdb;
    }

    public double getRatingKinopoisk() {
        return ratingKinopoisk;
    }

    public void setRatingKinopoisk(double ratingKinopoisk) {
        this.ratingKinopoisk = ratingKinopoisk;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getStills() {
        return stills;
    }

    public void setStills(List<String> stills) {
        this.stills = stills;
    }

    public List<ItemFilmDTO> getFilmsRelated() {
        return filmsRelated;
    }

    public void setFilmsRelated(List<ItemFilmDTO> filmsRelated) {
        this.filmsRelated = filmsRelated;
    }

    public List<ItemCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<ItemCategoryDTO> categories) {
        this.categories = categories;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }
}
