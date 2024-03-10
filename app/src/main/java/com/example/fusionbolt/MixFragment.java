package com.example.fusionbolt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fusionbolt.databinding.FragmentMixBinding;

import java.util.ArrayList;

public class MixFragment extends Fragment {

    private ArrayList<Element> elements;
    private FragmentMixBinding binding;
    private Element selectedElement = null;

    private float initialX, initialY;
    private int initialTouchX, initialTouchY;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMixBinding.inflate(inflater, container, false);

        setupDragAndDrop(binding.feu);
        setupDragAndDrop(binding.eau);
        setupDragAndDrop(binding.terre);
        setupDragAndDrop(binding.vent);
        setupDragAndDrop(binding.boue);
        setupDragAndDrop(binding.dust);
        setupDragAndDrop(binding.fume);
        setupDragAndDrop(binding.lave);
        setupDragAndDrop(binding.terre);
        setupDragAndDrop(binding.vague);
        setupDragAndDrop(binding.vapeur);

        elements = Element.initData();

        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
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

        binding.imageContainer.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DROP:
                        float x = event.getX();
                        float y = event.getY();

                        handleDropEvent((ImageView) event.getLocalState(), x, y);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    public Element mixThem(Element e1, Element e2){
        for( Element e : e1.getPropriety().keySet()){
            if( e.getName().equals(e2.getName())) return e1.getPropriety().get(e);
        }
        return null;
    }
    private void displayElementInView(Element element, float x, float y) {
        int imageResId = getResources().getIdentifier(element.getLogo().replace(".png", ""), "drawable", getActivity().getPackageName());
        if (imageResId != 0) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imageResId);

            int width = getResources().getDimensionPixelSize(R.dimen.image_width);
            int height = getResources().getDimensionPixelSize(R.dimen.image_height);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
            imageView.setLayoutParams(params);

            binding.imageContainer.addView(imageView);

            imageView.setX(x - width / 2);
            imageView.setY(y - height / 2);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void handleDropEvent(ImageView draggedImageView, float x, float y) {
        Element droppedElement = findElementByName((String) draggedImageView.getTag());

        if (selectedElement == null) {
            selectedElement = droppedElement;
            displayElementInView(droppedElement, x, y);
        } else {
            Element newElement = mixThem(selectedElement, droppedElement);

            if (newElement != null) {
                int imageResId = getResources().getIdentifier(newElement.getLogo().replace(".png", ""), "drawable", getActivity().getPackageName());
                if (imageResId != 0) {
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setImageResource(imageResId);

                    int width = getResources().getDimensionPixelSize(R.dimen.image_width);
                    int height = getResources().getDimensionPixelSize(R.dimen.image_height);

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
                    imageView.setLayoutParams(params);

                    imageView.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint("ClickableViewAccessibility")
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    initialX = v.getX();
                                    initialY = v.getY();
                                    initialTouchX = (int) event.getRawX();
                                    initialTouchY = (int) event.getRawY();
                                    return true;
                                case MotionEvent.ACTION_MOVE:
                                    float deltaX = event.getRawX() - initialTouchX;
                                    float deltaY = event.getRawY() - initialTouchY;
                                    v.setX(initialX + deltaX);
                                    v.setY(initialY + deltaY);
                                    return true;
                            }
                            return false;
                        }
                    });

                    binding.imageContainer.addView(imageView);

                    imageView.setX(x - width / 2);
                    imageView.setY(y - height / 2);
                }
            }

            selectedElement = null;
        }
    }

    private Element findElementByName(String elementName) {
        if (elements == null) {
            return null;
        }
        for (Element element : elements) {
            if (element.getName().equals(elementName)) {
                return element;
            }
        }
        return null;
    }
}

