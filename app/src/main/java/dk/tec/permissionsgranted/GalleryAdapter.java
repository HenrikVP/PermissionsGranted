package dk.tec.permissionsgranted;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImageViewHolder> {

    private final Context context;
    private final List<File> imageFiles;

    public GalleryAdapter(Context context, List<File> imageFiles) {
        this.context = context;
        this.imageFiles = imageFiles;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        File imageFile = imageFiles.get(position);
        Uri imageUri = Uri.fromFile(imageFile);

        // Load image using Glide
        Glide.with(context)
                .load(imageUri)
                .centerCrop()
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> showImageDialog(imageUri));
    }

    @Override
    public int getItemCount() {
        return imageFiles.size();
    }

    private void showImageDialog(Uri imageUri) {
        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_with_image, null);

        ImageView imageView = dialogView.findViewById(R.id.dialog_image);
        Button btnOption1 = dialogView.findViewById(R.id.button_choice_1);
        Button btnOption2 = dialogView.findViewById(R.id.button_choice_2);

        imageView.setImageURI(imageUri);


        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        // Set button listeners
        btnOption1.setOnClickListener(v -> {
            Toast.makeText(context, "Option 1 clicked", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        btnOption2.setOnClickListener(v -> {
            Toast.makeText(context, "Option 2 clicked", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // Show the dialog
        dialog.show();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

