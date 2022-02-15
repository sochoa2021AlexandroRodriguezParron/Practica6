package net.iessochoa.alexandrorodriguez.practica6.ui.pokemon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PokemonsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PokemonsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}