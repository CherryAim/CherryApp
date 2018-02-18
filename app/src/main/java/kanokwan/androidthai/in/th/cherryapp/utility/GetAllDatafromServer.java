package kanokwan.androidthai.in.th.cherryapp.utility;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by MasterUNG on 2/17/2018.
 */

public class GetAllDatafromServer extends AsyncTask<String, Void, String>{

     private Context context;

    public GetAllDatafromServer(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[0]).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
