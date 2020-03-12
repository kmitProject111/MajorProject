package com.sih.majorproject.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sih.majorproject.FarmerHome;
import com.sih.majorproject.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;
FarmerHome fh=new FarmerHome();
String n=fh.getPhone();
String p = fh.getPword();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        final TextView user_name = root.findViewById(R.id.profile_name_val);
        user_name.setText(n);
        final TextView user_pword = root.findViewById(R.id.profile_phone_val);
        user_pword.setText(p);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                System.out.println(p);
            }
        });
        return root;
    }
}