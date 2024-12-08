package com.app.customadapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.customadapters.model.AndroidVersions;
import java.util.List;

public class CustomAdapters extends BaseAdapter {

    private Context context;
    private List<AndroidVersions> adaptersList;

    public CustomAdapters(Context context, List<AndroidVersions> adaptersList) {
        this.context = context;
        this.adaptersList = adaptersList;
    }

    @Override
    public int getCount() {
        if(adaptersList == null || adaptersList.isEmpty())
            return 0;
        return adaptersList.size();
    }

    @Override
    public Object getItem(int position) {
        return adaptersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_list_item, parent, false);
        }
        TextView versionNumber = convertView.findViewById(R.id.tv_android_version_num);
        TextView versionName = convertView.findViewById(R.id.tv_android_version_name);
        LinearLayout mainLayout = convertView.findViewById(R.id.list_item_layout);

        versionNumber.setText(adaptersList.get(position).getVersion());
        versionName.setText(adaptersList.get(position).getName());

        mainLayout.setOnClickListener( v -> {
            Toast.makeText(context, "Android Version " + adaptersList.get(position).getVersion()
                     + "\nAndroid Name " + adaptersList.get(position).getName(), Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
