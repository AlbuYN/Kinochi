package tv.kinochi.kinochi.presenter;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.HttpException;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.view.fragments.MyView;

public class ErrorProcessing {

    private Context context;

    public ErrorProcessing(Context context) {
        this.context = context;
    }

    public void onError(Throwable e, MyView view) {

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            try {
                String str = httpException.response().errorBody().string();
                JSONObject post = new JSONObject(str);
                view.showError(post.getString("detail"));
            } catch (IOException e1) {
                e1.printStackTrace();
                view.showError(e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
                view.showError(e.getMessage());
            }
        } else if (e.getMessage().contains("Unable to resolve host")) {
            view.showError(context.getString(R.string.unable_to_resolve_host));
        } else view.showError(e.getMessage());

    }
}
