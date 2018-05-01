package org.techtown.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by kimjunhyeong on 2018. 5. 1..
 */

public class ViewerFragment extends Fragment {
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container,false);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
}
