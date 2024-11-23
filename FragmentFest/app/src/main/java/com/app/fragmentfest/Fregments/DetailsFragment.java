package com.app.fragmentfest.Fregments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.fragmentfest.R;

public class DetailsFragment extends Fragment {
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.detailed_fragment_view, container, false);
        TextView textView = view.findViewById(R.id.detailed_fragment_view);
        if (getArguments() != null && getArguments().containsKey("monthDays")) {
            textView.setText(getArguments().getString("monthDays"));
        }
        return container;
    }
}
