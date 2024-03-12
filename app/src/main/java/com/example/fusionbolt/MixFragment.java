package com.example.fusionbolt;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fusionbolt.databinding.FragmentMixBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MixFragment extends Fragment {

    private ArrayList<Element> elements;
    private FragmentMixBinding binding;
    private Element selectedElement = null;

    private float initialX, initialY;
    private int initialTouchX, initialTouchY;

    public HashMap<Element, ArrayList<int[]>> onDisplay = new HashMap<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMixBinding.inflate(inflater, container, false);

        setupDragAndDrop(binding.feu);
        setupDragAndDrop(binding.eau);
        setupDragAndDrop(binding.terre);
        setupDragAndDrop(binding.vent);

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
        if (e1 == null || e2 == null || e1.getPropriety() == null) {
            return null;
        }
        for(Element e : e1.getPropriety().keySet()){
            if(e != null && e.getName().equals(e2.getName())) return e1.getPropriety().get(e);
        }
        return null;
    }


    private int displayElementInView(Element element, float x, float y) {
        int imageResId = getResources().getIdentifier(element.getLogo().replace(".png", ""), "drawable", getActivity().getPackageName());




        if (imageResId != 0) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imageResId);

            int width = getResources().getDimensionPixelSize(R.dimen.image_width);
            int height = getResources().getDimensionPixelSize(R.dimen.image_height);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
            imageView.setLayoutParams(params);

            int viewId = View.generateViewId();
            imageView.setId(viewId);

            binding.imageContainer.addView(imageView);

            imageView.setX(x - width / 2);
            imageView.setY(y - height / 2);
            return viewId;
        }

        return -1;
    }

    public void removeImageView(int imageViewId) {
        View imageViewToRemove = binding.imageContainer.findViewById(imageViewId);
        if (imageViewToRemove != null) {
            binding.imageContainer.removeView(imageViewToRemove);
        }
    }


    private int convertPositionToId(float x, float y) {
        int maxWidth = binding.imageContainer.getWidth();

        int xi = (int)x;
        int yi = (int)y;
        return yi * maxWidth + xi;
    }
    public int findPositionIndex(ArrayList<int[]> positions, int[] targetPosition) {
        for (int i = 0; i < positions.size(); i++) {
            if (Arrays.equals(positions.get(i), targetPosition)) {
                return i;
            }
        }
        return -1;
    }



    @SuppressLint("ClickableViewAccessibility")
    private void handleDropEvent(ImageView draggedImageView, float x, float y) {
        Element droppedElement = findElementByName((String) draggedImageView.getTag());

        int ind = 0;
        Element existingElement = null;
        for (Element element : onDisplay.keySet()) {
            for (int[] position : onDisplay.get(element)) {

                if (position[0] <= (int) x + 100 && position[0] >= (int) x - 100 && position[1] <= (int) y + 100 && position[1] >= (int) y - 100) {
                    existingElement = element;
                    ind = findPositionIndex(onDisplay.get(element), position);
                    break;
                }
            }
        }

        if (existingElement != null) {
            Element newElement = mixThem(existingElement, droppedElement);
            if (newElement != null) {

                ArrayList<int[]> remove1 = onDisplay.get(existingElement);
                int[] indexxx = remove1.get(ind);
                removeImageView(indexxx[2]);
                remove1.remove(ind);
                onDisplay.put(existingElement, remove1);

                if (onDisplay.containsKey(newElement)) {
                    int idd = displayElementInView(newElement, x, y);
                    ArrayList<int[]> updaton = onDisplay.get(newElement);
                    updaton.add(new int[]{(int) x, (int) y, idd});
                    ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);
                    setupTouchListener(imageView);
                    onDisplay.put(newElement, updaton);
                } else {
                    int idd = displayElementInView(newElement, x, y);
                    ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);
                    setupTouchListener(imageView);
                    ArrayList<int[]> nouvau = new ArrayList<>();
                    nouvau.add(new int[]{(int) x, (int) y, idd});
                    onDisplay.put(newElement, nouvau);
                    addImageViewDynamically(newElement);
                }
            }
        } else {
            if (onDisplay.containsKey(droppedElement)) {
                int idd = displayElementInView(droppedElement, x, y);
                ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);
                setupTouchListener(imageView);
                ArrayList<int[]> updaton = onDisplay.get(droppedElement);
                updaton.add(new int[]{(int) x, (int) y, idd});
                onDisplay.put(droppedElement, updaton);
            } else {
                int idd = displayElementInView(droppedElement, x, y);
                ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);
                setupTouchListener(imageView);
                ArrayList<int[]> nouvau = new ArrayList<>();
                nouvau.add(new int[]{(int) x, (int) y, idd});
                onDisplay.put(droppedElement, nouvau);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void handleDropEvent2(ImageView draggedImageView, float x, float y){

        Element droppedElement = null;

        for (Element element : onDisplay.keySet()) {
            for (int[] position : onDisplay.get(element)) {
                if(position[2] == draggedImageView.getId()){
                    droppedElement = element;
                }
            }
        }


        int ind = 0;
        Element existingElement = null;
        for (Element element : onDisplay.keySet()) {
            for (int[] position : onDisplay.get(element)) {

                if (position[0] <= (int) x + 200 && position[0] >= (int) x - 200 && position[1] <= (int) y + 200 && position[1] >= (int) y - 200 && element != droppedElement) {
                    existingElement = element;
                    ind = findPositionIndex(onDisplay.get(element), position);
                    break;
                }
            }
        }

        if (existingElement != null) {
            Element newElement = mixThem(existingElement, droppedElement);
            if (newElement != null) {

                ArrayList<int[]> remove1 = onDisplay.get(existingElement);
                int[] indexxx = remove1.get(ind);
                removeImageView(indexxx[2]);
                remove1.remove(ind);
                onDisplay.put(existingElement, remove1);

                ArrayList<int[]> remove2 = onDisplay.get(droppedElement);
                int ind2 = 0;
                for (int[] position : remove2) {
                    if (position[2] == draggedImageView.getId()) {
                        ind2 = findPositionIndex(onDisplay.get(droppedElement), position);
                        break;
                    }
                }
                int[] indexxx2 = remove2.get(ind2);
                removeImageView(indexxx2[2]);
                remove2.remove(ind2);
                onDisplay.put(droppedElement, remove2);

                if (onDisplay.containsKey(newElement)) {
                    int idd = displayElementInView(newElement, x, y);
                    ArrayList<int[]> updaton = onDisplay.get(newElement);
                    updaton.add(new int[]{(int) x, (int) y, idd});
                    ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);
                    setupTouchListener(imageView);
                    onDisplay.put(newElement, updaton);
                } else {
                    int idd = displayElementInView(newElement, x, y);
                    ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);
                    setupTouchListener(imageView);
                    ArrayList<int[]> nouvau = new ArrayList<>();
                    nouvau.add(new int[]{(int) x, (int) y, idd});
                    onDisplay.put(newElement, nouvau);
                    addImageViewDynamically(newElement);
                }
            }
        }
    }
    private void addImageViewDynamically(Element element) {
        View view = getView();
        if (view != null && element != null && getContext() != null) {
            LinearLayout linearLayout = view.findViewById(R.id.linearLayoutContainer);
            ImageView imageView = new ImageView(getContext());

            String drawableName = element.getDrawableName();
            if (drawableName != null) {
                int resId = getContext().getResources().getIdentifier(drawableName, "drawable", getContext().getPackageName());
                if (resId != 0) {
                    imageView.setImageResource(resId);
                    imageView.setTag(element.getName());
                } else {
                    Log.e("ImageViewDynamic", "Ressource drawable introuvable: " + drawableName);
                    return;
                }
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);
            setupDragAndDrop(imageView);
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setupTouchListener(ImageView imageView) {
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialTouchX = (int)event.getX();
                        initialTouchY = (int)event.getY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getX() - initialTouchX;
                        float deltaY = event.getY() - initialTouchY;
                        float newX = imageView.getX() + deltaX;
                        float newY = imageView.getY() + deltaY;

                        imageView.setX(newX);
                        imageView.setY(newY);

                        updateElementPositionInMap(imageView, newX, newY);
                        return true;

                    case MotionEvent.ACTION_UP:
                        handleDropEvent2(imageView, imageView.getX(), imageView.getY());
                        return true;
                }
                return false;
            }
        });

    }
    private void updateElementPositionInMap(ImageView imageView, float newX, float newY) {
        int imageViewId = imageView.getId();

        for (Map.Entry<Element, ArrayList<int[]>> entry : onDisplay.entrySet()) {
            ArrayList<int[]> positions = entry.getValue();
            for (int[] pos : positions) {
                if (pos[2] == imageViewId) {
                    pos[0] = (int) newX;
                    pos[1] = (int) newY;
                    return;
                }
            }
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

