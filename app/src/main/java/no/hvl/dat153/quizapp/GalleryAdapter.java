package no.hvl.dat153.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.Comparator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<GalleryItem> galleryItemList;

    private boolean isAscendingOrder = true;

    public GalleryAdapter(List<GalleryItem> galleryItemList) {
        this.galleryItemList = galleryItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryItem galleryItem = galleryItemList.get(position);

        holder.galleryImageView.setImageResource(galleryItem.getImageResource());
        holder.galleryTextView.setText(galleryItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return galleryItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryImageView;
        TextView galleryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryImageView = itemView.findViewById(R.id.galleryImageView);
            galleryTextView = itemView.findViewById(R.id.galleryTextView);
        }
    }
    public void sortByAlphabet() {
        galleryItemList.sort(new Comparator<GalleryItem>() {
            @Override
            public int compare(GalleryItem item1, GalleryItem item2) {
                int result = item1.getDescription().compareTo(item2.getDescription());

                return isAscendingOrder ? result : -result;
            }
        });

        notifyDataSetChanged();
    }

    public void sortByReverseAlphabet() {
        galleryItemList.sort(new Comparator<GalleryItem>() {
            @Override
            public int compare(GalleryItem item1, GalleryItem item2) {
                int result = item1.getDescription().compareTo(item2.getDescription());

                return isAscendingOrder ? -result : result;
            }
        });

        notifyDataSetChanged();
    }

    // Metode for å bytte mellom A-Z og Z-A
    public void toggleSortOrder() {
        isAscendingOrder = !isAscendingOrder;
        sortByAlphabet();
    }
}

