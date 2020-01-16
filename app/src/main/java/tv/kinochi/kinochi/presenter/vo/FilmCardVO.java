package tv.kinochi.kinochi.presenter.vo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;



public class FilmCardVO implements Serializable {

    private String uid;
    private String nameEn;
    private String nameRu;
    private String posterUrl;
    private String quality;
    private int ratingKinochiPositive;
    private int ratingKinochiNegative;
    private double ratingImdb;
    private double ratingKinopoisk;
    private String updateDatetime;
    private String releaseDateAndcountry;
    private String description;
    private List<String> stills;
    private List<ItemFilmVO> filmsRelated;
    private List<ItemCategoryVO> categories;
    private int mediaType;


    public FilmCardVO(String uid, String nameEn, String nameRu, String posterUrl, String quality,
                      int ratingKinochiPositive, int ratingKinochiNegative, double ratingImdb,
                      double ratingKinopoisk, String updateDatetime, String description,
                      List<String> stills, List<ItemFilmVO> filmsRelated,
                      List<ItemCategoryVO> categories, String releaseDateAndcountry, int mediaType) {

        this.uid = uid;
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.posterUrl = posterUrl;
        this.quality = quality;
        this.ratingKinochiPositive = ratingKinochiPositive;
        this.ratingKinochiNegative = ratingKinochiNegative;
        this.ratingImdb = ratingImdb;
        this.ratingKinopoisk = ratingKinopoisk;
        this.updateDatetime = updateDatetime;
        this.releaseDateAndcountry = releaseDateAndcountry;
        this.description = description;
        this.stills = stills;
        this.filmsRelated = filmsRelated;
        this.categories = categories;
        this.mediaType = mediaType;
    }

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

    public String getReleaseDateAndcountry() {
        return releaseDateAndcountry;
    }

    public void setReleaseDateAndcountry(String releaseDateAndcountry) {
        this.releaseDateAndcountry = releaseDateAndcountry;
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

    public List<ItemFilmVO> getFilmsRelated() {
        return filmsRelated;
    }

    public void setFilmsRelated(List<ItemFilmVO> filmsRelated) {
        this.filmsRelated = filmsRelated;
    }

    public List<ItemCategoryVO> getCategories() {
        return categories;
    }

    public void setCategories(List<ItemCategoryVO> categories) {
        this.categories = categories;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }
}
