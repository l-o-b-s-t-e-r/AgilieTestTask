package com.company.my.ui.users;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.company.my.R;
import com.company.my.app.App;
import com.company.my.databinding.FragmentUsersBinding;
import com.company.my.model.entity.User;
import com.company.my.ui.base.BaseFragment;
import com.company.my.ui.users.di.UsersModule;
import com.company.my.ui.users.presenter.IUsersPresenter;
import com.company.my.ui.users.presenter.UsersPresenter;

import java.util.List;

public class UsersFragment extends BaseFragment<UsersPresenter, FragmentUsersBinding> implements IUsersPresenter.View {

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    public interface OnFragmentListener {
        void openUserDetails(User user);

        void saveRecyclerState(Parcelable state);

        Parcelable getRecyclerState();
    }

    private OnFragmentListener mListener;
    private UsersRecyclerAdapter mUsersAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mListener = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUsersAdapter = new UsersRecyclerAdapter(mListener);

        binding.users.setAdapter(mUsersAdapter);
        binding.users.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        presenter.loadUsers();
    }

    @Override
    public void onPause() {
        super.onPause();
        mListener.saveRecyclerState(binding.users.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void showUsers(List<User> users) {
        mUsersAdapter.updateItems(users);
        binding.users.getLayoutManager().onRestoreInstanceState(mListener.getRecyclerState());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_users;
    }

    @Override
    public void inject() {
        App.getComponent()
                .plus(new UsersModule(this))
                .inject(this);
    }
}
