package com.example.application;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.model.*;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static CCustomSharedPreference mCustomSP;
    public static String URL = "http://192.168.1.102:8080/uaa/create_account";
    private static String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Считывание данных из SharedPreferences
        mCustomSP                                            = ((CCustomApplication)getApplication()).getSharedCustom();
        accessToken                                          = CCustomSharedPreference.getAccessToken();

        if (accessToken.length() == 0) {

            Single.fromCallable(this::OAuthConnection)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<String>() {
                                   @Override
                                   public void onSuccess(String s) {
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                   }
                               }
                    );
        }

    }

    public String OAuthConnection() throws IOException, InterruptedException, ExecutionException
    {
        OAuth20Service service = new ServiceBuilder("smart_home")
                .apiSecret("app")
                .scope("resource.read openid resource.write")
                .callback("com.example.application://callback")
                .build(MyApi.instance());

        OAuth2AccessToken token = service.getAccessTokenPasswordGrant("maxzhuzhgov96@gmail.com", "max_201010");
        CCustomSharedPreference.setAccessToken(token.getAccessToken());

        System.out.println(token);

        return "Connected";
    }
}
