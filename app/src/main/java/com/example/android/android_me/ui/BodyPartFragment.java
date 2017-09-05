package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lakshmiprasad on 05-09-2017.
 */

public class BodyPartFragment extends Fragment {

    private static final String IMAGE_IDS = "imageIds";
    private static final String IMAGE_INDEX = "imageIndex";
    private List<Integer> imageIds;
    private Integer imageIndex;

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);
        if (null != savedInstanceState) {
            imageIndex = savedInstanceState.getInt(IMAGE_INDEX);
            imageIds = savedInstanceState.getIntegerArrayList(IMAGE_IDS);
        }
        imageView.setImageResource(imageIds.get(imageIndex == null ? 0 : imageIndex));

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (imageIndex < imageIds.size() - 1) {
                    imageIndex++;
                } else {
                    imageIndex = 0;
                }
                imageView.setImageResource(imageIds.get(imageIndex == null ? 0 : imageIndex));

            }
        });
        return rootView;
    }

    public void setImageIds(List<Integer> imageIds) {
        this.imageIds = imageIds;
    }

    public void setImageIndex(Integer imageIndex) {
        this.imageIndex = imageIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(IMAGE_IDS, (ArrayList<Integer>) imageIds);
        outState.putInt(IMAGE_INDEX, imageIndex);
    }
}
