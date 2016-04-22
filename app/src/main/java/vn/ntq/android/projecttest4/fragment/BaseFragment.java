package vn.ntq.android.projecttest4.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import vn.ntq.android.projecttest4.R;


/**
 * Created by Allen on 19-Apr-16.
 */
public class BaseFragment extends Fragment {
    public void showPage(Fragment fragment, boolean isAddtoBackstack) {
        if (isAdded()) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            if (isAddtoBackstack) transaction.addToBackStack(fragment.getClass().toString());
            transaction.commit();
        }
    }
}