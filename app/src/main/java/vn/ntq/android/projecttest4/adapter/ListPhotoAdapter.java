package vn.ntq.android.projecttest4.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.ntq.android.projecttest4.R;
import vn.ntq.android.projecttest4.network.CatService;


/**
 * Created by Allen on 19-Apr-16.
 */
public class ListPhotoAdapter extends RecyclerView.Adapter<ListPhotoAdapter.CatPhotoViewHolder> {
    private List<CatService.CatEntity> listCat;

    public ListPhotoAdapter(List<CatService.CatEntity> listCat) {
        this.listCat = listCat;
    }

    @Override
    public CatPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_photo_item, parent, false);
        return new CatPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatPhotoViewHolder holder, int position) {
        holder.display(listCat.get(position));
    }

    @Override
    public int getItemCount() {
        return listCat.size();
    }

    public static class CatPhotoViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;

        public CatPhotoViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

        public void display(CatService.CatEntity cat) {
            Picasso.with(view.getContext())
                    .load(cat.getUrl())
                    .placeholder(R.drawable.empty_photo)
                    .into(imageView);
        }
    }
}
