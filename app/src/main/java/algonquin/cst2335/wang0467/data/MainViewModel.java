package algonquin.cst2335.wang0467.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class MainViewModel extends ViewModel {

//    public String editString;

    public MutableLiveData<String> editString = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();


}