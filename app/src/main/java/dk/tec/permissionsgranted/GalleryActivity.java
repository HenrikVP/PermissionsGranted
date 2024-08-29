package dk.tec.permissionsgranted;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private List<File> imageFiles = new ArrayList<>();
    private GalleryAdapter galleryAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        // Set up RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        galleryAdapter = new GalleryAdapter(this, imageFiles);
        recyclerView.setAdapter(galleryAdapter);

        loadImagesFromStorage();
    }

    private void loadImagesFromStorage() {
        File storageDir = getExternalFilesDir("Pictures");
        if (storageDir != null && storageDir.exists()) {
            File[] files = storageDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    imageFiles.add(file);
                }
                galleryAdapter.notifyDataSetChanged();
            }
        }
    }
}