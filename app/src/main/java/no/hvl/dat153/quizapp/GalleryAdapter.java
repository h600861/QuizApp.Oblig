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
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

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

        if(galleryItem.getItemType() == GalleryItem.ItemType.RESOURCE_ID) {
            holder.imageView.setImageResource(galleryItem.getImageResource());
        } else holder.imageView.setImageBitmap(galleryItem.getImageBitmap());
        holder.textView.setText(galleryItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return galleryItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.galleryImageView);
            textView = itemView.findViewById(R.id.galleryTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
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
}
