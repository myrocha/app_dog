package br.com.appdog.view.activity;


import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.appdog.R;
import br.com.appdog.databinding.DogActivityBinding;


public class DogActivity extends AppCompatActivity {
    DogActivityBinding binding;

  //  DogActivityBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView( R.layout.dog_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.dog_activity);
        Bundle bundle = getIntent ().getExtras ();
        Bitmap image = bundle.getParcelable ("image");
        binding.imgDog.setImageBitmap(image);
        //imageView.setImageBitmap (image);
       // binding.setViewModel(loginViewModel);

    }
}
