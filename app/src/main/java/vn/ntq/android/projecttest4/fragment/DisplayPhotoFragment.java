package vn.ntq.android.projecttest4.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vn.ntq.android.projecttest4.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayPhotoFragment extends BaseFragment {
    public static final String IMAGE_URL = "image_url";
    private String url;
    private ImageView mImageView;

    public DisplayPhotoFragment() {
        // Required empty public constructor
    }

    public static DisplayPhotoFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(IMAGE_URL, url);
        DisplayPhotoFragment fragment = new DisplayPhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            url = savedInstanceState.getString(IMAGE_URL);
        } else if (getArguments() != null) {
            url = getArguments().getString(IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView = (ImageView) view.findViewById(R.id.imageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(url))
            Picasso.with(getContext())
                    .load(url)
                    .placeholder(R.drawable.empty_photo)
                    .into(mImageView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URL, url);
    }
}
