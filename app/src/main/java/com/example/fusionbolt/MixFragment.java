package com.example.fusionbolt;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fusionbolt.databinding.FragmentMixBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MixFragment extends Fragment {

    private ArrayList<Element> elements;
    private ArrayList<Element> usedElements;
    private FragmentMixBinding binding;
    private int displayed = 0;
    private int dialogueCount = 0;
    private int initialTouchX, initialTouchY;
    private DatabaseHelper dbHelper;
    private Map<String, String> relations;


    public HashMap<Element, ArrayList<int[]>> onDisplay = new HashMap<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbHelper = new DatabaseHelper(getContext());
        loadElementsAndRelations();
        loadusedElements();




        binding = FragmentMixBinding.inflate(inflater, container, false);

        displayUsedElements();

        LinearLayout constraintLayout = binding.MixContainer;
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(7500);
        animationDrawable.start();


        ProgressBar progressBar = binding.progressBar;
        progressBar.setMax(elements.size());
        progressBar.setProgress(usedElements.size());

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ElemDexFragment elemDexFragment = new ElemDexFragment();
                fragmentTransaction.replace(R.id.fragment_container_view_uy, elemDexFragment);
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }
    private void loadusedElements() {
        usedElements = new ArrayList<>();
        for (Element element : elements) {
            if (element.isUsed()) {
                usedElements.add(element);
            }
        }
    }
    private void loadElementsAndRelations() {
        if (dbHelper != null) {
            elements = (ArrayList<Element>) dbHelper.getElementsSortedByLastAccessed(); // Charger les éléments
            relations = dbHelper.getAllRelations(); // Charger les relations
        } else {
            elements = new ArrayList<>();
            relations = new HashMap<>();
        }
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

        TextView tvCredits = view.findViewById(R.id.tvCredits);

        int currentCredits = dbHelper.getCredits();

        tvCredits.setText(String.valueOf(currentCredits));

        if(usedElements.size() == 4){
            ImageView characterImageView = view.findViewById(R.id.characterImageView);
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.move_up);
            characterImageView.startAnimation(animation);


            float destinationY = (view.getHeight() - characterImageView.getHeight()) / 2.0f - characterImageView.getTop();
            ObjectAnimator moveToCenter = ObjectAnimator.ofFloat(characterImageView, "translationY", 0f, destinationY);
            moveToCenter.setDuration(1000);
            ObjectAnimator breatheAnimation = ObjectAnimator.ofFloat(characterImageView, "translationY", destinationY, destinationY - 20f, destinationY);
            breatheAnimation.setDuration(1000);
            breatheAnimation.setRepeatCount(ValueAnimator.INFINITE);
            breatheAnimation.setRepeatMode(ValueAnimator.REVERSE);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(moveToCenter, breatheAnimation);
            animatorSet.start();

            characterImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogueCount < 8) {
                        showDialogue();
                        dialogueCount++;
                    } else {
                        dialogueCount = 0;
                        v.setVisibility(View.GONE);
                        animatorSet.end();
                        characterImageView.clearAnimation();
                    }
                }
            });
        }else {
            ImageView characterImageView = view.findViewById(R.id.characterImageView);
            characterImageView.setVisibility(View.GONE);
        }
    }




    private void showDialogue() {
        String message = "";

        switch (dialogueCount) {
            case 0:
                message = "Bonjour ! je suis dieu !";
                break;
            case 1:
                message = " J'ai détruit mon univers suite a un mauvais mélange.";
                break;
            case 2:
                message = "Mes supérieur désirent me transformer en Quasar mais je les ai supplié de me laisser une chance..";
                break;
            case 3:
                message = "Alors on m'a donné pour mission de t'aider ! C'est à ton tour maintenant de créer ton univers !";
                break;
            case 4:
                message = "Quoi ? Tu ne sais pas comment faire ?!";
                break;
            case 5:
                message = "Mais enfin, c'est très simple !";
                break;
            case 6:
                message = "Il suffit de mélanger des trucs avec des machins, et ça donne des bidules en somme !";
                break;
            case 7:
                message = "Allez ! T'apprendras sur le tas ! C'est partit !";
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(true);

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }


    public Element mixThem(Element e1, Element e2){
        String s1 = e1.getName()+"+"+e2.getName();
        String s2 = e2.getName()+"+"+e1.getName();

        if(relations.containsKey(s1)) {
            return findElementByName(relations.get(s1));
        }
        else if(relations.containsKey(s2)) {
            return findElementByName(relations.get(s2));
        }
        else {
            return null;
        }

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

    private void checkProgressBarProgress() {
        ProgressBar progressBar = binding.progressBar;
        if (progressBar.getProgress() == progressBar.getMax()/2) {
            showImageDialogue();
        }
    }

    private void removeLeftmostImageView(LinearLayout linearLayout) {
        int childCount = linearLayout.getChildCount();
        if (childCount > 0) {
            View leftmostChild = linearLayout.getChildAt(0);
            if (leftmostChild instanceof ImageView) {
                linearLayout.removeViewAt(0);
            }
        }
    }


    private void showImageDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom_layout, null);
        builder.setView(dialogView);

        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
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
                int fff = indexxx[2];
                removeImageView(indexxx[2]);
                remove1.remove(ind);
                onDisplay.put(existingElement, remove1);

                if (onDisplay.containsKey(newElement)) {
                    int idd = displayElementInView(newElement, x, y);
                    ArrayList<int[]> updaton = onDisplay.get(newElement);
                    updaton.add(new int[]{(int) x, (int) y, idd});
                    ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);
                    Animation fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                    imageView.startAnimation(fadeInAnimation);
                    setupTouchListener(imageView);
                    onDisplay.put(newElement, updaton);
                } else {
                    if(displayed >= 7){
                        removeLeftmostImageView(binding.linearLayoutContainer);
                    }
                    displayed++;
                    ProgressBar progressBar = binding.progressBar;
                    int f = progressBar.getProgress();
                    progressBar.setProgress(f + 1);
                    checkProgressBarProgress();
                    int idd = displayElementInView(newElement, x, y);
                    ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);

                    setupTouchListener(imageView);
                    ArrayList<int[]> nouvau = new ArrayList<>();
                    nouvau.add(new int[]{(int) x, (int) y, idd});
                    newElement.setUsed(true);
                    if(!usedElements.contains(newElement)){
                        dbHelper.setElementUsed(newElement.getName());
                        addImageViewDynamically(newElement);
                        dbHelper.addCredits(10);
                        int currentCredits = dbHelper.getCredits();
                        binding.tvCredits.setText(String.valueOf(currentCredits));
                        binding.decouverte.setText("Vous avez découvert : "+newElement.getName()+" !");
                    }
                    onDisplay.put(newElement, nouvau);
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

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
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
                    Animation fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                    imageView.startAnimation(fadeInAnimation);
                    setupTouchListener(imageView);
                    onDisplay.put(newElement, updaton);
                } else {
                    if(displayed >= 7){
                        removeLeftmostImageView(binding.linearLayoutContainer);
                    }
                    displayed++;
                    ProgressBar progressBar = binding.progressBar;
                    int f = progressBar.getProgress();
                    progressBar.setProgress(f + 1);
                    checkProgressBarProgress();
                    int idd = displayElementInView(newElement, x, y);
                    ImageView imageView = (ImageView) binding.imageContainer.findViewById(idd);

                    setupTouchListener(imageView);
                    ArrayList<int[]> nouvau = new ArrayList<>();
                    nouvau.add(new int[]{(int) x, (int) y, idd});
                    newElement.setUsed(true);

                    if(!usedElements.contains(newElement)){
                        dbHelper.setElementUsed(newElement.getName());
                        addImageViewDynamically(newElement);
                        dbHelper.addCredits(10);
                        int currentCredits = dbHelper.getCredits();
                        binding.tvCredits.setText(String.valueOf(currentCredits));
                        binding.decouverte.setText("Vous avez découvert : "+newElement.getName()+" !");

                    }
                    onDisplay.put(newElement, nouvau);
                }
            }
        }
    }
    private void addImageViewDynamically(Element element) {
        View view = getView();
        if (view != null && element != null && getContext() != null) {
            LinearLayout linearLayout = view.findViewById(R.id.linearLayoutContainer);
            ImageView imageView = new ImageView(getContext());

            String drawableName = element.getLogo();
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

    private void displayUsedElements() {
        LinearLayout linearLayout = binding.linearLayoutContainer;
        linearLayout.removeAllViews(); // Nettoyer pour éviter les doublons

        for (Element element : usedElements) {
            if(element.isUsed() && displayed <= 8) { // Vérifiez si l'élément est utilisé
                displayed++;
                ImageView imageView = new ImageView(getContext());
                int resId = getResources().getIdentifier(element.getLogo(), "drawable", getContext().getPackageName());
                if (resId != 0) {
                    imageView.setImageResource(resId);

                    // Configuration des LayoutParams avec un poids
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1.0f
                    );

                    imageView.setLayoutParams(layoutParams);

                    imageView.setTag(element.getName());

                    setupDragAndDrop(imageView);

                    linearLayout.addView(imageView);
                }
            }
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
    private void loadElementsFromDatabase() {
        elements = (ArrayList<Element>) dbHelper.getAllElements();
    }



}

