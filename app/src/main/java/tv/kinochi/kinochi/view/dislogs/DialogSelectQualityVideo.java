package tv.kinochi.kinochi.view.dislogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.Objects;
import java.util.regex.Pattern;

import tv.kinochi.kinochi.R;

public class DialogSelectQualityVideo extends DialogFragment {


    private CharSequence selectedQuality;


    public static DialogSelectQualityVideo newInstance(String source) {
        Bundle args = new Bundle();
        args.putString("source", source);

        DialogSelectQualityVideo dialogSelectQualityVideo = new DialogSelectQualityVideo();
        dialogSelectQualityVideo.setArguments(args);

        return dialogSelectQualityVideo;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        assert getArguments() != null;
        CharSequence[] qualities = Pattern.compile(",")
                .split(Objects.requireNonNull(getArguments().getString("source"))
                        .replaceAll(".*\\{|\\}.*", ""));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.quality)
                .setSingleChoiceItems(qualities, 0,
                        (dialogInterface, i) -> {
                            assert qualities != null;
                            selectedQuality = qualities[i];
                        })
                .setPositiveButton(getString(R.string.play), (dialogInterface, i) -> {

                    Log.d("myLog"," check = " + (selectedQuality == null ? qualities[0] : selectedQuality));

                }).setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dismiss());
        return builder.create();
    }
}
