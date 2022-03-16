package kg.geektech.postapplesson2;

import android.app.ActionBar;
import android.app.Application;

import kg.geektech.postapplesson2.data.remote.PostApi;
import kg.geektech.postapplesson2.data.remote.RetrofitClient;
import retrofit2.Retrofit;

public class App extends Application {

    private RetrofitClient client;
    public static PostApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new RetrofitClient();
        api = client.provideApi();
    }
}
