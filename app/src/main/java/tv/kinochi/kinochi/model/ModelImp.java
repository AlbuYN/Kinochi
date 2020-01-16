package tv.kinochi.kinochi.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import tv.kinochi.kinochi.model.api.ApiInterface;
import tv.kinochi.kinochi.model.dto.BlockFilmsDTO;
import tv.kinochi.kinochi.model.dto.BlockFiltersDTO;
import tv.kinochi.kinochi.model.dto.FilmCardDTO;
import tv.kinochi.kinochi.model.dto.ItemCategoryDTO;
import tv.kinochi.kinochi.model.dto.ItemFilmDTO;
import tv.kinochi.kinochi.model.dto.LinksListDTO;
import tv.kinochi.kinochi.model.dto.VideoDTO;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.other.Const;

public class ModelImp implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    ApiInterface apiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;


    public ModelImp() {
        App.getComponent().inject(this);
        schedulersTransformer = observable -> ((Observable) observable)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }


    @Override
    public Observable<List<BlockFilmsDTO>> getFilms(int page) {


        List<BlockFilmsDTO> blockFilmsDTOList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            BlockFilmsDTO blockFilmsDTO = new BlockFilmsDTO();
            blockFilmsDTO.setName("Фильмы");

            if (i == 1) {
                blockFilmsDTO.setViewType("horizontal");
            } else blockFilmsDTO.setViewType("vertical");

            List<ItemFilmDTO> itemFilmDTOList = new ArrayList<>();

            for (int g = 1; g <= 10; g++) {
               ItemFilmDTO itemFilmDTO = new ItemFilmDTO();
               itemFilmDTO.setNameEn("Venom");
               itemFilmDTO.setNameRu("Веном");
               itemFilmDTO.setPosterUrl("https://upload.wikimedia.org/wikipedia/ru/0/05/Venom_poster.jpg");
               itemFilmDTO.setQuality("WEB-RIP");
               itemFilmDTO.setUid("12313232123");

               itemFilmDTOList.add(itemFilmDTO);
            }

            blockFilmsDTO.setFilms(itemFilmDTOList);

            blockFilmsDTOList.add(blockFilmsDTO);
        }

        return Observable.create(subscriber -> {
                    subscriber.onNext(blockFilmsDTOList);
                    subscriber.onCompleted();
                }
        );
    }

    @Override
    public Observable<FilmCardDTO> getFilmCard(String uid) {

        FilmCardDTO filmCardDTO = new FilmCardDTO();
        filmCardDTO.setCountry("США/Канада");
        filmCardDTO.setDescription("Прошли тысячелетия после того, как мир настиг апокалипсис. Человечество адаптировалось и теперь живет по новым правилам. Гигантские движущиеся мегаполисы рассекают пустоши и поглощают маленькие города ради ресурсов. Том Нэтсуорти из нижнего уровня великого Лондона оказывается в смертельной опасности, когда на его пути появляется скрывающаяся от закона бунтарка Эстер Шоу. Они не должны были встретиться, но им суждено изменить будущее.\n<br>\n" +
                "Режиссер <a href='#Стивен_Спилберг'>Стивен Спилбенг</a>\n<br>\nВ ролях <a href='#Джонни_Деп'>Джонни Деп</a>, <a href='#Анджелина_Джоли'>Анджелина Джоли</a>\n");
        filmCardDTO.setNameEn("Kinochi");
        filmCardDTO.setNameRu("Киночи");
        filmCardDTO.setPosterUrl("https://upload.wikimedia.org/wikipedia/ru/0/05/Venom_poster.jpg");
        filmCardDTO.setQuality("CamRip");
        filmCardDTO.setRatingImdb(7.2);
        filmCardDTO.setRatingKinopoisk(8.3);
        filmCardDTO.setRatingKinochiPositive(1234);
        filmCardDTO.setRatingKinochiNegative(567);
        filmCardDTO.setUpdateDatetime("7 ДЕК 2018");
        filmCardDTO.setReleaseDate("2018");

        List<String> stills = new ArrayList<>();
        stills.add("https://upload.wikimedia.org/wikipedia/ru/0/05/Venom_poster.jpg");
        stills.add("https://upload.wikimedia.org/wikipedia/ru/0/05/Venom_poster.jpg");

        filmCardDTO.setStills(stills);

        List<ItemFilmDTO> itemFilmDTOList = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            ItemFilmDTO itemFilmDTO = new ItemFilmDTO();
            itemFilmDTO.setUid("332332");
            itemFilmDTO.setQuality("CamRip");
            itemFilmDTO.setPosterUrl("https://upload.wikimedia.org/wikipedia/ru/0/05/Venom_poster.jpg");
            itemFilmDTO.setNameRu("Веном");
            itemFilmDTO.setNameEn("Venom");
            itemFilmDTOList.add(itemFilmDTO);
        }

        filmCardDTO.setFilmsRelated(itemFilmDTOList);

        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
            itemCategoryDTO.setName("Комедия");
            itemCategoryDTO.setUid("323223");

            itemCategoryDTOList.add(itemCategoryDTO);
        }

        filmCardDTO.setCategories(itemCategoryDTOList);


        return Observable.create(subscriber -> {
            subscriber.onNext(filmCardDTO);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<BlockFiltersDTO>> getFilters() {

        List<BlockFiltersDTO> blockFiltersDTOList = new ArrayList<>();



        BlockFiltersDTO blockFiltersDTO = new BlockFiltersDTO();

        for (int i = 1; i <= 5; i++) {


            blockFiltersDTO.setName("Жанры");
            blockFiltersDTO.setUid("23231223434");

            List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();

            for (int g = 1; g <= 10; g ++) {

                ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
                itemCategoryDTO.setName("Комедии");
                itemCategoryDTO.setUid("34242423");

                itemCategoryDTOList.add(itemCategoryDTO);
            }

            blockFiltersDTO.setItemCategoryDTOList(itemCategoryDTOList);

            blockFiltersDTOList.add(blockFiltersDTO);

        }

        return Observable.create(subscriber -> {
            subscriber.onNext(blockFiltersDTOList);
            subscriber.onCompleted();

        });
    }

    @Override
    public Observable<List<VideoDTO>> getStreamingVideos(String uidFilm) {


        List<VideoDTO> seasons = new ArrayList<>();

        for (int i = 1; i < 20; i++) {
            VideoDTO season = new VideoDTO();
            season.setLabel(String.valueOf(i));
            season.setGroupLabel("Сезоны");

            List<VideoDTO> translaters = new ArrayList<>();
            for (int g = 1; g < 8; g++) {
                VideoDTO translate = new VideoDTO();
                translate.setLabel("LostFilm");
                translate.setDetail("15 серий");
                translate.setGroupLabel("Переводы");

                List<LinksListDTO> seriesList = new ArrayList<>();
                for (int p = 1; p < 15; p++) {
                    LinksListDTO linksListDTO = new LinksListDTO();
                    linksListDTO.setDescription("Описание");
                    linksListDTO.setLabel("Серия 1");
                    linksListDTO.setSource("https://v.filmix.me/film{320,480}.mp4");

                    seriesList.add(linksListDTO);
                }

                translate.setLinksList(seriesList);
                translaters.add(translate);
            }
            season.setVideoDTOList(translaters);
            seasons.add(season);
        }

        return Observable.create(subscriber -> {
            subscriber.onNext(seasons);
            subscriber.onCompleted();
        });

        /*


        StreamingVideoDTO streamingVideoDTO = new StreamingVideoDTO();
        streamingVideoDTO.setLabel("Filmix");

        List<SeasonDTO> seasonDTOList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            SeasonDTO seasonDTO = new SeasonDTO();
            seasonDTO.setSeason("1");

            List<TranslationDTO> translationDTOList = new ArrayList<>();
            for (int g = 1; g < 6; g++) {
                TranslationDTO translationDTO = new TranslationDTO();
                translationDTO.setTranslation("LostFilm");

                List<SeriesDTO> seriesDTOList = new ArrayList<>();

                for (int p = 1; p < 10; p++) {
                    SeriesDTO seriesDTO = new SeriesDTO();
                    seriesDTO.setTitle("Серия 2");
                    seriesDTO.setDescription("Опсание");
                    seriesDTO.setUrl("https://www.youtube.com/watch?v=-u1EC0fxr98");
                    seriesDTOList.add(seriesDTO);
                }

                translationDTO.setSeries(seriesDTOList);
                translationDTOList.add(translationDTO);
            }

            seasonDTO.setTranslations(translationDTOList);
            seasonDTOList.add(seasonDTO);
        }


        streamingVideoDTO.setSeasons(seasonDTOList);


        return Observable.create(subscriber -> {
            subscriber.onNext(streamingVideoDTO);
            subscriber.onCompleted();
        });

        */
    }



    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
