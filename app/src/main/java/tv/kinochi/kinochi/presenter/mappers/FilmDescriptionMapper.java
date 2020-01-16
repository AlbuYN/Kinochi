package tv.kinochi.kinochi.presenter.mappers;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;


import rx.Observable;
import rx.functions.Func1;
import tv.kinochi.kinochi.model.dto.FilmCardDTO;
import tv.kinochi.kinochi.model.dto.ItemCategoryDTO;
import tv.kinochi.kinochi.model.dto.ItemFilmDTO;
import tv.kinochi.kinochi.presenter.vo.FilmCardVO;
import tv.kinochi.kinochi.presenter.vo.ItemCategoryVO;
import tv.kinochi.kinochi.presenter.vo.ItemFilmVO;

public class FilmDescriptionMapper implements Func1<FilmCardDTO, FilmCardVO> {

    @Inject
    FilmDescriptionMapper(){}

    @Override
    public FilmCardVO call(FilmCardDTO filmCardDTO) {
        return new FilmCardVO(filmCardDTO.getUid(), filmCardDTO.getNameEn(), filmCardDTO.getNameRu(),
                filmCardDTO.getPosterUrl(), filmCardDTO.getQuality(),
                filmCardDTO.getRatingKinochiPositive(), filmCardDTO.getRatingKinochiNegative(),
                filmCardDTO.getRatingImdb(), filmCardDTO.getRatingKinopoisk(),
                filmCardDTO.getUpdateDatetime(), filmCardDTO.getDescription(),
                filmCardDTO.getStills(), mapFilms(filmCardDTO.getFilmsRelated()),
                mapCategory(filmCardDTO.getCategories()), filmCardDTO.getReleaseDate() + ", "
                + filmCardDTO.getCountry(), filmCardDTO.getMediaType());
    }

    private List<ItemFilmVO> mapFilms(List<ItemFilmDTO> itemFilmDTOList) {
        if (itemFilmDTOList != null) {
            return Observable.from(itemFilmDTOList)
                    .map(itemFilmDTO -> new ItemFilmVO(itemFilmDTO.getUid(), itemFilmDTO.getNameEn(),
                            itemFilmDTO.getNameRu(), itemFilmDTO.getPosterUrl(), itemFilmDTO.getQuality()))
                    .toList()
                    .toBlocking()
                    .first();
        }
        return null;
    }

    private List<ItemCategoryVO> mapCategory(List<ItemCategoryDTO> itemCategoryDTOList) {
        if (itemCategoryDTOList != null) {
            return Observable.from(itemCategoryDTOList)
                    .map(itemCategoryDTO -> new ItemCategoryVO(itemCategoryDTO.getUid(), itemCategoryDTO.getName()))
                    .toList()
                    .toBlocking()
                    .first();
        }
        return null;
    }
}
