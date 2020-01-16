package tv.kinochi.kinochi.model.api;


import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import tv.kinochi.kinochi.model.dto.BlockFilmsDTO;
import tv.kinochi.kinochi.model.dto.BlockFiltersDTO;
import tv.kinochi.kinochi.model.dto.FilmCardDTO;
import tv.kinochi.kinochi.model.dto.VideoDTO;

public interface ApiInterface {


    //Список фильмов
    @GET("api/films/list_by_categories/")
    Observable<List<BlockFilmsDTO>> getFilms(@Query("page") int page);

    //Карточка фильма
    @GET("api/films/{uid}/")
    Observable<FilmCardDTO> getFilmCard(@Query("uid") String uid);

    //Получение видео
    @GET("api/films/{uid}/videos/")
    Observable<List<VideoDTO>> getStreamingVideos(@Query("uid") String uidFilm);




    //Фильтры
    @GET("api/filters/")
    Observable<List<BlockFiltersDTO>> getFilters();




}
