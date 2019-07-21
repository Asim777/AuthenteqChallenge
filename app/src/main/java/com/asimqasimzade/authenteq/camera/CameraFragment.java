package com.asimqasimzade.authenteq.camera;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.asimqasimzade.authenteq.R;
import com.asimqasimzade.authenteq.databinding.FragmentCameraBinding;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

public class CameraFragment extends Fragment {

    private FragmentCameraBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bitmap bitmap;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.test_image, null);

        if (bitmapDrawable != null) {
            bitmap = bitmapDrawable.getBitmap();
            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false);
        binding.setHandler(new CameraFragmentEventHandler());
        return binding.getRoot();
    }

    public class CameraFragmentEventHandler {
        public void onTakePhotoButtonClick (View view) {
            if (getActivity() != null) {
                Navigation.findNavController(view)
                        .navigate(R.id.action_cameraFragment_to_resultFragment);
            }
        }
    }
}
