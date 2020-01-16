package tv.kinochi.kinochi.presenter.mappers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import tv.kinochi.kinochi.model.dto.BlockFiltersDTO;
import tv.kinochi.kinochi.model.dto.ItemCategoryDTO;
import tv.kinochi.kinochi.presenter.vo.BlockFiltersVO;
import tv.kinochi.kinochi.presenter.vo.ItemCategoryVO;

public class BlockFiltersMapper implements Func1<List<BlockFiltersDTO>, List<BlockFiltersVO>> {

    @Inject
    BlockFiltersMapper() {}

    @Override
    public List<BlockFiltersVO> call(List<BlockFiltersDTO> blockFiltersDTOS) {
        return Observable.from(blockFiltersDTOS)
                .map(blockFiltersDTO -> new BlockFiltersVO(blockFiltersDTO.getUid(),
                        blockFiltersDTO.getName(), mapFilter(blockFiltersDTO.getItemCategoryDTOList())))
                .toList()
                .toBlocking()
                .first();
    }


    private List<ItemCategoryVO> mapFilter(List<ItemCategoryDTO> itemCategoryDTOList) {

        return Observable.from(itemCategoryDTOList)
                .map(filmCategoryDTO -> new ItemCategoryVO(filmCategoryDTO.getUid(), filmCategoryDTO.getName()))
                .toList()
                .toBlocking()
                .first();
    }
}
