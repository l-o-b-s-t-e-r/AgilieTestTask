package com.company.my.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.company.my.R;
import com.company.my.model.entity.User;
import com.company.my.ui.details.UserDetailsFragment;
import com.company.my.ui.users.UsersFragment;

import static com.company.my.ui.details.UserDetailsFragment.USER_ID;

public class MainActivity extends AppCompatActivity implements UsersFragment.OnFragmentListener {

    public static final String RECYCLER_STATE = "recycler_state";

    private User mSelectedUser;
    private Parcelable mRecyclerState;
    private FragmentManager mFragmentManager;
    private UserDetailsFragment mUserDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getBoolean(R.bool.landscape_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(RECYCLER_STATE)) {
            mRecyclerState = savedInstanceState.getParcelable(RECYCLER_STATE);
        }

        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.users_fragment);
        if (fragment == null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.users_fragment, UsersFragment.newInstance())
                    .commit();
        } else {
            if (fragment instanceof UserDetailsFragment) {
                mFragmentManager.popBackStack();
            }
        }

        if (findViewById(R.id.user_details_fragment) != null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.user_details_fragment, mUserDetailsFragment = UserDetailsFragment.newInstance(savedInstanceState))
                    .commit();
        } else {
            Fragment userDetailsFragment = mFragmentManager.findFragmentById(R.id.user_details_fragment);
            if (userDetailsFragment != null) {
                mFragmentManager.beginTransaction()
                        .remove(userDetailsFragment)
                        .commit();
            }
        }
    }

    @Override
    public void openUserDetails(User user) {
        mSelectedUser = user;

        if (mUserDetailsFragment == null || !mUserDetailsFragment.isAdded()) {
            Bundle args = new Bundle();
            args.putLong(USER_ID, user.getId());

            mFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.users_fragment, mUserDetailsFragment = UserDetailsFragment.newInstance(args))
                    .commit();
        } else {
            mUserDetailsFragment.showUserDetails(user);
        }
    }

    /**
     * Keep RecyclerView state inside Activity
     * because {@link Fragment#onSaveInstanceState(Bundle)} not called in case of replacing
     */
    @Override
    public void saveRecyclerState(Parcelable state) {
        mRecyclerState = state;
    }

    @Override
    public Parcelable getRecyclerState() {
        return mRecyclerState;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(RECYCLER_STATE, mRecyclerState);
        if (mSelectedUser != null && mUserDetailsFragment != null && mUserDetailsFragment.isVisible()) {
            outState.putLong(USER_ID, mSelectedUser.getId());
        }

        super.onSaveInstanceState(outState);
    }
}
