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

    public MyListAdapter(Context context, ArrayList<File> files,MainActivity activity) {
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
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(R.layout.my_list_layout,null);
            viewHolder=new ViewHolder();
            viewHolder.nameText=(TextView) view.findViewById(R.id.file_name);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.nameText.setText(files.get(position).getName());

        return view;
    }


    class ViewHolder{
        TextView nameText;
    }

}
