package westbrook.wang.viewoffice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.net.URL;

import static westbrook.wang.viewoffice.Constant.APPLICATION_NAME;
import static westbrook.wang.viewoffice.Constant.VIEW_OFFICE_URL;

public class WebviewForOfficeActivity extends AppCompatActivity {

    private WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_for_office);
        String url = getIntent().getStringExtra(VIEW_OFFICE_URL);
//        String url = Environment.getDataDirectory().getAbsolutePath().concat("/data/").concat(APPLICATION_NAME).concat("/officeViewTemp/untitled.html");
        url = "File:///".concat(url);
        webview = (WebView) findViewById(R.id.view_office_webview);
        webview.loadUrl(url);
    }
}
