package pl.dominikwozniak.submaskmaster.views;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pl.dominikwozniak.submaskmaster.R;
import pl.dominikwozniak.submaskmaster.databinding.ActivityMainBinding;
import pl.dominikwozniak.submaskmaster.viewmodels.IPViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new IPViewModel());
        activityMainBinding.executePendingBindings();
    }
}