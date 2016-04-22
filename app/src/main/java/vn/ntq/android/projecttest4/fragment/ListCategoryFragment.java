package vn.ntq.android.projecttest4.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import vn.ntq.android.projecttest4.adapter.ListCategoryAdapter;
import vn.ntq.android.projecttest4.entity.CatCategory;
import vn.ntq.android.projecttest4.network.CatService;
import vn.ntq.android.projecttest4.network.LoveCatClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCategoryFragment extends BaseFragment {
    public static final String EXTRA_LIST = "extra_list";
    private RecyclerView mRecycleView;
    private ListCategoryAdapter adapter;
    private List<CatCategory> catCategoryList;

    public ListCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            catCategoryList = savedInstanceState.getParcelableArrayList(EXTRA_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycleView = (RecyclerView) view.findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showPage(new ListPhotoFragment(), true);
            }
        }));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (catCategoryList != null) {
            adapter = new ListCategoryAdapter(catCategoryList);
            mRecycleView.setAdapter(adapter);
        } else {
            getCatList();
        }

    }

    private void getCatList() {
        showLoading();
        Retrofit retrofit = LoveCatClient.getClient();
        CatService catService = retrofit.create(CatService.class);
        catService.getListCat().enqueue(new Callback<CatService.Response>() {
            @Override
            public void onResponse(Call<CatService.Response> call, Response<CatService.Response> response) {
                catCategoryList = new ArrayList<>();
                List<CatService.CatEntity> listCat = response.body().getListCat().getListCat();
                int index = 0;
                for (CatService.CatEntity cat : listCat) {
                    catCategoryList.add(new CatCategory(index, "Cat category " + (index + 1), cat.getUrl()));
                    index++;
                }
                adapter = new ListCategoryAdapter(catCategoryList);
                mRecycleView.setAdapter(adapter);
                hideLoading();
            }

            @Override
            public void onFailure(Call<CatService.Response> call, Throwable t) {
                Log.d("Retrofit", t.getMessage());
                hideLoading();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_LIST, (ArrayList<? extends Parcelable>) catCategoryList);
    }

    @Override
    public String getName() {
        return "ListCatFragment";
    }
}
