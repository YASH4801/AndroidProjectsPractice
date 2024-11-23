package com.app.fragmentfest.Fregments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.fragmentfest.R;

import java.util.Objects;

public class MonthNames extends Fragment {

    private AndroidVersionListner androidListner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            androidListner = (AndroidVersionListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement AndroidVersionListner");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contextView = LayoutInflater.from(getContext()).inflate(R.layout.month_fragment_layout, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.months_array));
        ListView listView = contextView.findViewById(R.id.fragment_layout);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String monthName = ((TextView) view).getText().toString();
                androidListner.onVersionItemClick(getMonthDays(monthName));
            }
        });
        return contextView;
    }

    private String getMonthDays(String monthName) {
        return switch (monthName) {
            case "January", "March", "May", "July", "August", "October", "December" -> "31";
            case "April", "June", "September", "November" -> "30";
            case "February" -> "28";
            default -> throw new IllegalStateException("Unexpected value: " + monthName);
        };
    }
}
