package com.shadowappdev.seizurealert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.extractors.AccessTokenExtractor;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;

import org.scribe.builder.api.FitbitApi;

public class Oauth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        potato();
    }

    public void potato(){
   final OAuth20Service service = new ServiceBuilder()
            .apiKey("9866aaac0667eb8540429cd21b7d5101") //replaced my app key with * for security
            .apiSecret("3f4c8702c3d99b4b34ba1045b3c80eda") //replace my app secret with * for security
            .callback("https://www.fitbit.com/oauth2/authorize?")
            .debug() //for more verbose messages while testing
            .build(FitbitApi.java);

    Verifier v = new Verifier("verifier you got from the user");
    final Token requestToken = service.(v);
    String authUrl = "https://www.fitbit.com/oauth2/authorize?";


    final AccessTokenExtractor accessToken = service.getAccessToken(requestToken,v);

    final OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.fitbit.com/oauth2/authorize?", service);
    // the access token from step 4
    final Response response = request.send();

        System.out.println(response.getBody());
    }

}
