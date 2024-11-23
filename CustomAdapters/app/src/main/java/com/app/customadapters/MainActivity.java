package com.app.customadapters;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.customadapters.model.AndroidVersions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.test_list_view);

        CustomAdapters adapters = new CustomAdapters(this, getAndroidVersions());
        listView.setAdapter(adapters);

    }

    private List<AndroidVersions> getAndroidVersions() {
        AndroidVersions version1 = setAndroidVersions("4.0", "GingerBread");
        AndroidVersions version2 = setAndroidVersions("4.1", "HoneyComb");
        AndroidVersions version3 = setAndroidVersions("4.2", "Ice-cream Sandwich");
        AndroidVersions version4 = setAndroidVersions("4.3", "Jelly-Bean");
        AndroidVersions version5 = setAndroidVersions("4.4", "Kitkat");
        AndroidVersions version6 = setAndroidVersions("5.0", "LollyPop");
        AndroidVersions version7 = setAndroidVersions("6.0", "MarshMallow");
        AndroidVersions version8 = setAndroidVersions("7.0", "Nougat");
        AndroidVersions version9 = setAndroidVersions("8.0", "Oreo");
        AndroidVersions version10 = setAndroidVersions("9.0", "Pie");
        AndroidVersions version11 = setAndroidVersions("10.0", "Quests dish");
        AndroidVersions version12 = setAndroidVersions("11.0", "Rough flakes");
        return List.of(version1, version2, version3, version4, version5, version6, version7,
                version8, version9, version10, version11, version12);
    }

    private AndroidVersions setAndroidVersions(String vnum, String vname) {
        return new AndroidVersions(vnum, vname);
    }
}