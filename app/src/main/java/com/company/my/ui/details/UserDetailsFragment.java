package com.company.my.ui.details;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.company.my.R;
import com.company.my.app.App;
import com.company.my.databinding.FragmentUserDetailsBinding;
import com.company.my.model.entity.User;
import com.company.my.ui.base.BaseFragment;
import com.company.my.ui.details.di.UserDetailsModule;
import com.company.my.ui.details.presenter.IUserDetailsPresenter;
import com.company.my.ui.details.presenter.UserDetailsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class UserDetailsFragment extends BaseFragment<UserDetailsPresenter, FragmentUserDetailsBinding> implements
        IUserDetailsPresenter.View,
        OnMapReadyCallback {

    public static UserDetailsFragment newInstance(Bundle args) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static final String USER_ID = "user_id";
    private User mUser;
    private GoogleMap mMap;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map))
                .getMapAsync(this);

        if (getArguments() != null && getArguments().containsKey(USER_ID)) {
            presenter.getUser(getArguments().getLong(USER_ID));
        }

        binding.email.setOnClickListener(view1 -> sendEmail());
        binding.phone.setOnClickListener(view2 -> makeCall());
    }

    @Override
    public void showUserDetails(User user) {
        mUser = user;

        binding.info.setVisibility(View.VISIBLE);
        binding.name.setText(user.getName());
        binding.email.setText(user.getEmail());
        binding.phone.setText(user.getPhone());
        binding.web.loadUrl(String.format(getString(R.string.https), user.getWebsite()));

        showLocation();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_user_details;
    }

    @Override
    public void inject() {
        App.getComponent()
                .plus(new UserDetailsModule(this))
                .inject(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        showLocation();
    }

    private void showLocation() {
        if (mUser != null && mMap != null) {
            MarkerOptions markerOptions = new MarkerOptions().position(mUser.getAddress().getLatLng());
            Marker marker = mMap.addMarker(markerOptions);
            marker.setTitle(mUser.getAddress().getStreet());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 12));
        }
    }

    private void sendEmail() {
        if (mUser != null) {
            Intent emailIntent = new Intent(Intent.ACTION_VIEW);
            emailIntent.setData(Uri.parse(String.format(getString(R.string.mailto), mUser.getEmail())));

            startImplicitIntent(emailIntent);
        }
    }

    private void makeCall() {
        if (mUser != null) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(String.format(getString(R.string.tel), mUser.getPhone())));

            startImplicitIntent(callIntent);
        }
    }

    private void startImplicitIntent(Intent intent) {
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            showToast(getString(R.string.error_intent));
        }
    }
}
