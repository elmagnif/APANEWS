package app.com.apanews.apanews;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog pDialog;


//MBAYEC CAMARA


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        final WebView browser = (WebView) findViewById(R.id.webview);


        //Enable Javascript
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDomStorageEnabled(true);
        //End Enable Javascript


        //GARDER LES LIENS SUR L'APP
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //END


        pDialog = new ProgressDialog(this);
        pDialog.getProgress();
        // Showing progress dialog before making http request
        pDialog.setMessage("Chargement...");
        //pDialog.show();


        browser.loadUrl("http://mobile.apanews.net/app2/");

        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //browser.loadUrl("file:///android_asset/missing.html");
            }
        });

        //CHARGEMENT
        browser.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("WebView", "onPageStarted " + url);
                pDialog.show();
                pDialog.setCancelable(false);
                pDialog.setCanceledOnTouchOutside(false);

                //SPLASH SCREEN

                //END SPLASH
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "onPageFinished " + url);
                pDialog.hide();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
              //loadUrl("file:///assets/error.html");
                browser.loadUrl("file:///android_asset/error1.html");
                Toast.makeText(getApplicationContext(), "AUCUNE CONNEXION INTERNET " ,Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(MainActivity.this, ArticleRead.class);
             //   startActivity(intent);
            }
        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
