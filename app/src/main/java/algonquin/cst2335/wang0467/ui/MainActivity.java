package algonquin.cst2335.wang0467.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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







    }

}