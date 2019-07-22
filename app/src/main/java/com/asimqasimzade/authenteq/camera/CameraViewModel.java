package com.asimqasimzade.authenteq.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.ViewModel;

import com.camerakit.CameraKitView;

class CameraViewModel extends ViewModel {

    Bitmap createBitmapFromCapturedByteArray(CameraKitView cameraView, byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,
                0, byteArray != null ? byteArray.length : 0);
        return Bitmap.createScaledBitmap(bitmap,
                cameraView != null ? cameraView.getWidth() : 0,
                cameraView != null ? cameraView.getHeight() : 0,
                false);
    }
}
