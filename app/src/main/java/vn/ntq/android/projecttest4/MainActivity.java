package vn.ntq.android.projecttest4;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import vn.ntq.android.projecttest4.fragment.ListCategoryFragment;


public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.frame_layout) == null) {
            fragmentManager.beginTransaction().replace(R.id.frame_layout, new ListCategoryFragment()).commit();
        }
    }

    public void showDialogRequesting() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getResources().getString(R.string.waiting));
            mProgressDialog.setCancelable(false);
        }

        mProgressDialog.show();
        ;
    }

    public void hideDialogRequesting() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
