package tv.kinochi.kinochi.model;

import java.util.List;

import rx.Observable;
import tv.kinochi.kinochi.model.dto.BlockFilmsDTO;
import tv.kinochi.kinochi.model.dto.BlockFiltersDTO;
import tv.kinochi.kinochi.model.dto.FilmCardDTO;
import tv.kinochi.kinochi.model.dto.VideoDTO;

public interface Model {

    //Список фильмов
    Observable<List<BlockFilmsDTO>> getFilms(int page);

    //Карточка фильма
    Observable<FilmCardDTO> getFilmCard(String uid);

    //Фильтры
    Observable<List<BlockFiltersDTO>> getFilters();

    //Получение видео
    Observable<List<VideoDTO>> getStreamingVideos(String uidFilm);

}
