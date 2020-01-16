package tv.kinochi.kinochi.presenter.mappers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import tv.kinochi.kinochi.model.dto.LinksListDTO;
import tv.kinochi.kinochi.model.dto.VideoDTO;
import tv.kinochi.kinochi.presenter.vo.LinksListVO;
import tv.kinochi.kinochi.presenter.vo.VideoVO;

public class VideosMapper implements Func1<List<VideoDTO>, List<VideoVO>> {

    @Inject
    VideosMapper(){}

    @Override
    public List<VideoVO> call(List<VideoDTO> videoDTOS) {
        return Observable.from(videoDTOS)
                .toList()
                .map(this::mapListChild)
                .toBlocking()
                .first();
    }

    private List<VideoVO> mapListChild(List<VideoDTO> videoDTOS) {
        if (videoDTOS != null) {
            return Observable.from(videoDTOS)
                    .map(videoDTO -> new VideoVO(videoDTO.getLabel(), videoDTO.getGroupLabel(),
                            mapListChild(videoDTO.getVideoDTOList()), mapLinksList(videoDTO.getLinksList()),
                            videoDTO.getDetail()))
                    .toList()
                    .toBlocking()
                    .first();
        } else return null;

    }

    private List<LinksListVO> mapLinksList(List<LinksListDTO> linksListDTOS) {
        if (linksListDTOS != null) {
            return Observable.from(linksListDTOS)
                    .map(linksListDTO -> new LinksListVO(linksListDTO.getLabel(), linksListDTO.getDescription(), linksListDTO.getSource()))
                    .toList()
                    .toBlocking()
                    .first();
        } else return null;
    }
}
