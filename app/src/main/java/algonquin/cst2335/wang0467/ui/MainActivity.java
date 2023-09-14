package algonquin.cst2335.wang0467.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.wang0467.data.MainViewModel;
import algonquin.cst2335.wang0467.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

////        setContentView(R.layout.activity_main);
//        TextView myText = variableBinding.textview;
//        Button btn = variableBinding.myButton;
//        EditText myedit = variableBinding.myedittext;
//        String editString = myedit.getText().toString();
////        myText.setText("Your edit text has: " + editString);
//
//        if(btn != null) btn.setOnClickListener(    vw  ->  myText.setText("Your edit text has: " + editString)   );

//        variableBinding.textview.setText(model.editString);
        variableBinding.myButton.setOnClickListener(click ->
        {
//            model.editString = variableBinding.myedittext.getText().toString();
//            variableBinding.textview.setText("Your edit text has: " + model.editString);

            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has " + s);

        });

        model.isSelected.observe(this, selected ->
        {
            variableBinding.myCheckbox.setChecked(selected);
            variableBinding.mySwitch.setChecked(selected);
            variableBinding.myRadioButton.setChecked(selected);
        });

        variableBinding.myCheckbox.setOnCheckedChangeListener((btn, isChecked) -> {
//            model.isSelected.postValue(isChecked);
            if (isChecked) {
                variableBinding.mySwitch.setChecked(false);
                variableBinding.myRadioButton.setChecked(false);
            }
            Toast.makeText(this, "Checkbox value is now: " + isChecked, Toast.LENGTH_SHORT).show();
        });

        variableBinding.mySwitch.setOnCheckedChangeListener((btn, isChecked) -> {
//            model.isSelected.postValue(isChecked);
            if (isChecked) {
                variableBinding.myCheckbox.setChecked(false);
                variableBinding.myRadioButton.setChecked(false);
            }
            Toast.makeText(this, "Switch value is now: " + isChecked, Toast.LENGTH_SHORT).show();
        });

        variableBinding.myRadioButton.setOnCheckedChangeListener((btn, isChecked) -> {
//            model.isSelected.postValue(isChecked);
            if (isChecked) {
                variableBinding.myCheckbox.setChecked(false);
                variableBinding.mySwitch.setChecked(false);
            }
            Toast.makeText(this, "RadioButton value is now: " + isChecked, Toast.LENGTH_SHORT).show();
        });

//        variableBinding.logo.setOnClickListener(view -> {
////            Toast.makeText(this, "Logo clicked!", Toast.LENGTH_SHORT).show();
//        });

        variableBinding.myimagebutton.setOnClickListener(view ->
        {
            int width = view.getWidth();
            int height = view.getHeight();
            Toast.makeText(this, "The width = " + width + " and height = " + height, Toast.LENGTH_SHORT).show();
        });

    }

}