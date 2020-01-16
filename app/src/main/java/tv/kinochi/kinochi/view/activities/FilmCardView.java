package tv.kinochi.kinochi.view.activities;

import tv.kinochi.kinochi.view.fragments.FilmDescriptionFragment;
import tv.kinochi.kinochi.view.fragments.StreamingVideoFragment;

public interface FilmCardView {

    void setFilmDescriptionFragment(FilmDescriptionFragment filmDescriptionFragment);
    void setStreamingVideoFragment(StreamingVideoFragment streamingVideoFragment);
}
