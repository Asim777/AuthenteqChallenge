package com.asimqasimzade.authenteq.result;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.asimqasimzade.authenteq.R;
import com.asimqasimzade.authenteq.databinding.FragmentResultBinding;
import com.google.gson.Gson;

public class ResultFragment extends Fragment {

    private ResultViewModel resultViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentResultBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result,
                container, false);
        binding.setLifecycleOwner(this);
        binding.setResultImage(resultViewModel.resultImage);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find resultImage with provided id
        if (getArguments() != null) {
            Bitmap bitmap = new Gson().fromJson(getArguments().getString(getString(R.string.result_image_key)), Bitmap.class);
            Drawable resultImage = new BitmapDrawable(getResources(), bitmap);
            resultViewModel.setResultImage(resultImage);
        }
    }
}
