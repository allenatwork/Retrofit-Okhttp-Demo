package vn.ntq.android.projecttest4.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.ntq.android.projecttest4.R;
import vn.ntq.android.projecttest4.RecyclerItemClickListener;
import vn.ntq.android.projecttest4.adapter.ListPhotoAdapter;
import vn.ntq.android.projecttest4.network.CatService;
import vn.ntq.android.projecttest4.network.LoveCatClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPhotoFragment extends BaseFragment {
    public static final String EXTRA_LIST_PHOTO = "extra_list_photo";
    private List<CatService.CatEntity> listCat;
    private RecyclerView mRecyclerView;
    private ListPhotoAdapter adapter;


    public ListPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            listCat = savedInstanceState.getParcelableArrayList(EXTRA_LIST_PHOTO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DisplayPhotoFragment fragment = DisplayPhotoFragment.newInstance(listCat.get(position).getUrl());
                showPage(fragment, true);
            }
        }));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (listCat != null) {
            adapter = new ListPhotoAdapter(listCat);
            mRecyclerView.setAdapter(adapter);
        } else {
            getListCat();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_LIST_PHOTO, (ArrayList<? extends Parcelable>) listCat);
    }

    public void getListCat() {
        Retrofit retrofit = LoveCatClient.getClient();
        CatService catService = retrofit.create(CatService.class);
        catService.getListCat().enqueue(new Callback<CatService.Response>() {
            @Override
            public void onResponse(Call<CatService.Response> call, Response<CatService.Response> response) {
                listCat = response.body().getListCat().getListCat();
                adapter = new ListPhotoAdapter(listCat);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CatService.Response> call, Throwable t) {
                Log.d("Retrofit", t.getMessage());
            }
        });
    }
}
