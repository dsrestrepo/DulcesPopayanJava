package edu.unicauca.appsmoviles.dulces_popayan_java2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Disfruta de una dulce guía conociendo Popayán");
    }

    public LiveData<String> getText() {
        return mText;
    }
}