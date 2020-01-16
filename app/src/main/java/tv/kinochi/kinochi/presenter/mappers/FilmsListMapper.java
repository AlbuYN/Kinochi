package tv.kinochi.kinochi.presenter.mappers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import tv.kinochi.kinochi.model.dto.BlockFilmsDTO;
import tv.kinochi.kinochi.model.dto.ItemFilmDTO;
import tv.kinochi.kinochi.presenter.vo.BlockFilmsVO;
import tv.kinochi.kinochi.presenter.vo.ItemFilmVO;

public class FilmsListMapper implements Func1<List<BlockFilmsDTO>, List<BlockFilmsVO>> {

    @Inject
    FilmsListMapper() {}


    @Override
    public List<BlockFilmsVO> call(List<BlockFilmsDTO> blockFilmsDTOS) {
        return Observable.from(blockFilmsDTOS)
                .map(blockFilmsDTO -> new BlockFilmsVO(blockFilmsDTO.getUid(),
                        blockFilmsDTO.getName(), mapFilms(blockFilmsDTO.getFilms()),
                        blockFilmsDTO.getViewType()))
                .toList()
                .toBlocking()
                .first();

    }

    private List<ItemFilmVO> mapFilms(List<ItemFilmDTO> itemFilmDTOList) {
        return Observable.from(itemFilmDTOList)
                .map(itemFilmDTO -> new ItemFilmVO(itemFilmDTO.getUid(), itemFilmDTO.getNameEn(),
                        itemFilmDTO.getNameRu(), itemFilmDTO.getPosterUrl(),
                        itemFilmDTO.getQuality()))
                .toList()
                .toBlocking()
                .first();
    }
}
