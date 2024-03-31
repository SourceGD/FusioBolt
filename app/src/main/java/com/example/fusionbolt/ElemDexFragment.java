package com.example.fusionbolt;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ElemDexFragment extends Fragment {

    public static ElemDexFragment newInstance() {
        return new ElemDexFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elemdex, container, false);

        GridLayout gridLayout = view.findViewById(R.id.elements_grid);
        final int numberOfColumns = 4;
        gridLayout.setColumnCount(numberOfColumns);

        FrameLayout constraintLayout = view.findViewById(R.id.elemdexlayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(7500);
        animationDrawable.start();

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int spacing = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        int padding = gridLayout.getPaddingLeft() + gridLayout.getPaddingRight();
        int imageWidth = (screenWidth - padding - (spacing * (numberOfColumns - 1))) / numberOfColumns;

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        List<Element> elements = dbHelper.getAllElementsWithOrder();

        for (Element element : elements) {
            int credits = dbHelper.getCredits();
            String creditsText = String.valueOf(credits);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            GridLayout.LayoutParams linearParams = new GridLayout.LayoutParams();
            linearParams.width = imageWidth;
            linearParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            linearParams.setMargins(spacing / 2, spacing / 2, spacing / 2, spacing / 2);
            linearLayout.setLayoutParams(linearParams);

            ImageView imageView = new ImageView(getContext());
            int resId = getResources().getIdentifier(element.getLogo(), "drawable", getContext().getPackageName());
            imageView.setImageResource(resId);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    getResources().getDimensionPixelSize(R.dimen.image_height)
            );
            imageView.setLayoutParams(imageParams);

            TextView textView = new TextView(getContext());
            textView.setText(element.getName());
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textView.setShadowLayer(
                    1.5f,
                    1,
                    1,
                    Color.BLACK
            );

            textParams.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setLayoutParams(textParams);

            if (!element.isUsed()) {
                textView.setVisibility(View.GONE);

            }

            if (!element.isUsed()) {
                imageView.setColorFilter(Color.GRAY);
                imageView.setOnClickListener(v -> {
                    if(textView.getVisibility() != View.VISIBLE) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Débloquer nom d'élément")
                                .setMessage("Voulez-vous dépenser 20 crédits pour révéler le nom de cet élement ? \n\n (Vous avez actuellement " + creditsText + " crédits)")
                                .setPositiveButton("Oui", (dialog, which) -> {
                                    if (dbHelper.spendCredits(20)) {
                                        textView.setVisibility(View.VISIBLE);

                                    } else {
                                        Toast.makeText(getContext(), "Crédits insuffisants.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Non", null)
                                .show();
                    }else{
                        new AlertDialog.Builder(getContext())
                                .setTitle("Débloquer un des parents")
                                .setMessage("Voulez-vous dépenser 50 crédits pour révéler un des parents de cet élément ? \n\n (Vous avez actuellement " + creditsText + " crédits)")
                                .setPositiveButton("Oui", ((dialog1, which1) -> {
                                    if (dbHelper.spendCredits(50)) {
                                        String parentName = dbHelper.getOneParentName(element.getName());
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setMessage(parentName);
                                        builder.show();
                                    } else {
                                        Toast.makeText(getContext(), "Crédits insuffisants.", Toast.LENGTH_SHORT).show();
                                    }
                                }))
                                .setNegativeButton("Non", null)
                                .show();

                    }
                });

            } else {
                imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            dbHelper.updateElementAccessTime(element.getName());
                            return true;
                        }
                        return false;
                    }
                });
            }



            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            gridLayout.addView(linearLayout);
        }

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}