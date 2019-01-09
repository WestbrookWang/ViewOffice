package westbrook.wang.viewoffice;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import static westbrook.wang.viewoffice.Constant.VIEW_OFFICE_URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView dataListView;
    private MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataListView = findViewById(R.id.data_list);
        initData();
    }


    private void initData() {

        final String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/ViewOfficeDemo");

        File file = new File(folderPath);

        if (!file.exists()) {
            if (!file.mkdir()) {
                Log.d(TAG, "mkdir error");
            }
        }

        File[] files = file.listFiles();

        if (files != null) {

            ArrayList<File> fileArrayList = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                fileArrayList.add(files[i]);
            }

            if (listAdapter == null) {
                listAdapter = new MyListAdapter(MainActivity.this, fileArrayList, MainActivity.this);
            }

            dataListView.setAdapter(listAdapter);
        }

    }


    public void onFileClick(File file) {
        String mHTMLPath = FileConverter.get().getHTMLpath(file);


        gotoView("");
    }

    private void gotoView(String url) {
        Intent intent = new Intent(MainActivity.this, WebviewForOfficeActivity.class);
        intent.putExtra(VIEW_OFFICE_URL, url);
        startActivity(intent);
    }

}
