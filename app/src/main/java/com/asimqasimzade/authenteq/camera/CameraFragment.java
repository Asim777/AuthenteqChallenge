package com.asimqasimzade.authenteq.camera;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.asimqasimzade.authenteq.R;
import com.asimqasimzade.authenteq.databinding.FragmentCameraBinding;
import com.camerakit.CameraKitView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.gson.Gson;

import java.util.List;

public class CameraFragment extends Fragment {

    private CameraKitView cameraView;
    private View graphicOverlay;
    private CameraViewModel cameraViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraViewModel = ViewModelProviders.of(this).get(CameraViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentCameraBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_camera, container, false);
        binding.setHandler(new CameraFragmentEventHandler());
        cameraView = binding.getRoot().findViewById(R.id.camera_view);

        return binding.getRoot();
    }

    public class CameraFragmentEventHandler {
        public void onTakePhotoButtonClick() {
            cameraView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView cameraKitView, byte[] byteArray) {
                    runDetector(cameraViewModel.createBitmapFromCapturedByteArray(cameraKitView, byteArray));
                }
            });
        }

        void runDetector(final Bitmap bitmap) {
            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
            FirebaseVisionFaceDetectorOptions options = new FirebaseVisionFaceDetectorOptions.Builder().build();
            FirebaseVisionFaceDetector detector = FirebaseVision.getInstance().getVisionFaceDetector(options);

            detector.detectInImage(image)
                    .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
                        @Override
                        public void onSuccess(List<FirebaseVisionFace> faces) {
                            CameraFragment.this.processFaceResult(bitmap, faces);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    private void processFaceResult(Bitmap sourceBitmap, List<FirebaseVisionFace> faces) {
        //Getting our face from Firebase response
        Rect boundingBox = faces.get(0).getBoundingBox();

        //Cropping the bitmap and putting it into a bundle to send to ResultFragment
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, boundingBox.left, boundingBox.top, boundingBox.width(), boundingBox.height());
        Bundle resultBundle = new Bundle();
        resultBundle.putString(getString(R.string.result_image_key), new Gson().toJson(resultBitmap));

        //Launching ResultFragment with resultBundle as argument
        if (getActivity() != null && getView() != null) Navigation.findNavController(getView())
                .navigate(R.id.action_cameraFragment_to_resultFragment, resultBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraView.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        cameraView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        cameraView.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
