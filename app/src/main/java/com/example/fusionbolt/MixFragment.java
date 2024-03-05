package com.example.fusionbolt;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fusionbolt.databinding.FragmentMixBinding;

public class MixFragment extends Fragment {

    private FragmentMixBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMixBinding.inflate(inflater, container, false);

        setupDragAndDrop(binding.feu);
        setupDragAndDrop(binding.eau);
        setupDragAndDrop(binding.terre);
        setupDragAndDrop(binding.vent);

        return binding.getRoot();
    }

    private void setupDragAndDrop(ImageView imageView) {
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDragAndDrop(null, shadowBuilder, v, 0);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.Mix.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DROP:
                        handleDropEvent((ImageView) event.getLocalState());
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    private void handleDropEvent(ImageView draggedImageView) {
        String elementName = (String) draggedImageView.getTag();

        // Display the name of the dropped element in the Mix TextView
        String currentText = binding.Mix.getText().toString();
        if (!currentText.isEmpty()) {
            currentText += ", ";
        }
        currentText += elementName;

        binding.Mix.setText(currentText);
    }
}

