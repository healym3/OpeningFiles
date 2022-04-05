package healym3.openingfiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private final int CHOOSE_BMP_FROM_DEVICE = 1000;
    TextView textView;
    Button button;
    private Uri imageUri;
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(view -> {
            //FileAccess.openFile(getApplicationContext());
            chooseBmpFromDevice();
        });
    }

    private void chooseBmpFromDevice(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_BMP_FROM_DEVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHOOSE_BMP_FROM_DEVICE && resultCode == Activity.RESULT_OK){

            if(data != null){
                imageUri = data.getData();
                Log.d("TAG", "onActivityResult: " + imageUri.getPath());
                textView.setText(imageUri.getEncodedPath());
                Glide.with(getApplicationContext())
                        .load(imageUri)
                        .sizeMultiplier((float)0.5)
                        .into(imageView);
                //imageUri.
                //bitmap
            }
        }
    }
}