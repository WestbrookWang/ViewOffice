package westbrook.wang.viewoffice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

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

        if(!file.exists()){
            if(!file.mkdir()){
                Log.d(TAG,"mkdir error");
            }
        }

        File[] files = file.listFiles();

        if(files!=null){

            ArrayList<File> fileArrayList = new ArrayList<>();
            for(int i =0;i<files.length;i++){
                fileArrayList.add(files[i]);
            }

            if(listAdapter == null){
                listAdapter = new MyListAdapter(MainActivity.this,fileArrayList,MainActivity.this);
            }

            dataListView.setAdapter(listAdapter);
        }

    }


    public void onFileClick(File file){
        openFile(file);
    }

    private void openFile(File file){

    }

}
