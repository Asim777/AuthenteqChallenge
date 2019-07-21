package com.asimqasimzade.authenteq.result;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class ResultViewModel extends ViewModel {

    MutableLiveData<Drawable> resultImage = new MutableLiveData<>();

    void setResultImage(Drawable drawable) {
        resultImage.setValue(drawable);
    }
}
