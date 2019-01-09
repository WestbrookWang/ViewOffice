package westbrook.wang.viewoffice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<File> files;
    private MainActivity activity;

    public MyListAdapter(Context context, ArrayList<File> files, MainActivity activity) {
        this.context = context;
        this.files = files;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final File file = files.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.my_list_layout, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.nameText = (TextView) convertView.findViewById(R.id.file_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFileClick(file);
                Log.d("MyListAdapter", file.getName());
            }
        });
        viewHolder.nameText.setText(file.getName());

        return convertView;
    }


    class ViewHolder {
        TextView nameText;
    }

}
