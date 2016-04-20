package vn.ntq.android.projecttest4.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.ntq.android.projecttest4.R;
import vn.ntq.android.projecttest4.entity.CatCategory;


/**
 * Created by Allen on 19-Apr-16.
 */
public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.CatCategoryHolder> {
    private List<CatCategory> listCatCategories;

    public ListCategoryAdapter(@NonNull List<CatCategory> listCatCategories) {
        this.listCatCategories = listCatCategories;
    }

    @Override
    public CatCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_view, parent, false);
        return new CatCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CatCategoryHolder holder, int position) {
        holder.display(listCatCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return listCatCategories.size();
    }

    public static class CatCategoryHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView imageView;
        private TextView textView;

        public CatCategoryHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void display(@NonNull CatCategory catCategory) {
            textView.setText(catCategory.getName());
            Picasso.with(view.getContext())
                    .load(catCategory.getUrl())
                    .placeholder(R.drawable.empty_photo)
                    .into(imageView);
        }
    }
}
