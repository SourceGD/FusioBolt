package com.example.fusionbolt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fusionbolt.databinding.FragmentMixBinding;
import com.example.fusionbolt.databinding.FragmentWelcomeBinding;

public class MixFragment extends Fragment {

    FragmentMixBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMixBinding.inflate(inflater, container, false);

        return binding.getRoot();


    }
}