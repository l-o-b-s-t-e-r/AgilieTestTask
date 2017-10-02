package com.company.my.ui.users;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.company.my.R;
import com.company.my.app.App;
import com.company.my.databinding.ItemUserBinding;
import com.company.my.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lobster on 30.09.17.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    private List<User> mItems = new ArrayList<>();
    private UsersFragment.OnFragmentListener mListener;

    public UsersRecyclerAdapter(UsersFragment.OnFragmentListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        User user = mItems.get(position);
        ItemUserBinding binding = DataBindingUtil.bind(holder.getView());

        binding.getRoot().setOnClickListener(view -> mListener.openUserDetails(user));
        binding.email.setText(user.getEmail());
        binding.name.setText(user.getName());
        binding.info.setText(user.getCompany().getCatchPhrase());
        Glide.with(App.getInstance())
                .setDefaultRequestOptions(new RequestOptions().error(R.mipmap.ic_launcher))
                .load(user.getAvatarUrl())
                .into(binding.avatar);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(List<User> items) {
        mItems = items;
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;

        ViewHolder(View view) {
            super(view);
            mView = view;
        }

        public View getView() {
            return mView;
        }
    }
}
