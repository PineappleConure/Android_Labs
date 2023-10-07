package algonquin.cst2335.wang0467;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        TextView emailTextView = findViewById(R.id.topTextView);
        emailTextView.setText("Welcome back " + emailAddress);

        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(v -> {
            EditText phoneNumberEditText = findViewById(R.id.phoneEditText);
            String phoneNumber = phoneNumberEditText.getText().toString();

            if (!phoneNumber.isEmpty()) {
                if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    initiateCall(phoneNumber);
                }
            } else {
                Toast.makeText(SecondActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            }
        });

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ImageView profileImage = findViewById(R.id.cameraImageView);
        ActivityResultLauncher <Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            profileImage.setImageBitmap(thumbnail);
                        }
                    }
                });
        Button changePictureButton = findViewById(R.id.changePictureButton);
        changePictureButton.setOnClickListener(v -> {
            cameraResult.launch(cameraIntent);
        });
    }

    private void initiateCall(String phoneNumber) {
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(call);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                EditText phoneNumberEditText = findViewById(R.id.phoneEditText);
                String phoneNumber = phoneNumberEditText.getText().toString();
                initiateCall(phoneNumber);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


}