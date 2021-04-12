package pl.dominikwozniak.submaskmaster.views;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import pl.dominikwozniak.submaskmaster.R;
import pl.dominikwozniak.submaskmaster.databinding.ActivityMainBinding;
import pl.dominikwozniak.submaskmaster.viewmodels.IPViewModel;

public class MainActivity extends AppCompatActivity {

    private static int shortAnimationDuration;
    private static View networkAddressValue;
    private static View totalHostsValue;
    private static View usableHostsValue;
    private static View wildcardMaskValue;
    private static View binarySubnetMaskValue;
    private static View ipClassValue;
    private static View cidrNotationValue;
    private static View shortNotationValue;
    private static View inAddrArpaValue;
    private static View sixToFour;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new IPViewModel());
        activityMainBinding.executePendingBindings();

        networkAddressValue = findViewById(R.id.networkAddressValue);
        networkAddressValue.setVisibility(View.GONE);
        totalHostsValue = findViewById(R.id.totalHostsValue);
        totalHostsValue.setVisibility(View.GONE);
        usableHostsValue = findViewById(R.id.usableHostsValue);
        usableHostsValue.setVisibility(View.GONE);
        wildcardMaskValue = findViewById(R.id.wildcardMaskValue);
        wildcardMaskValue.setVisibility(View.GONE);
        binarySubnetMaskValue = findViewById(R.id.binarySubnetMaskValue);
        binarySubnetMaskValue.setVisibility(View.GONE);
        ipClassValue = findViewById(R.id.ipClassValue);
        ipClassValue.setVisibility(View.GONE);
        cidrNotationValue = findViewById(R.id.cidrNotationValue);
        cidrNotationValue.setVisibility(View.GONE);
        shortNotationValue = findViewById(R.id.shortNotationValue);
        shortNotationValue.setVisibility(View.GONE);
        inAddrArpaValue = findViewById(R.id.inAddrArpaValue);
        inAddrArpaValue.setVisibility(View.GONE);
        sixToFour = findViewById(R.id.sixToFourValue);
        sixToFour.setVisibility(View.GONE);

        shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        crossfade();
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private static void crossfade() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        if(networkAddressValue != null) {
            networkAddressValue.setAlpha(0f);
        }
        if(networkAddressValue != null) {
            networkAddressValue.setVisibility(View.VISIBLE);
        }
        if(totalHostsValue != null) {
            totalHostsValue.setAlpha(0f);
        }
        if(totalHostsValue != null) {
            totalHostsValue.setVisibility(View.VISIBLE);
        }
        if(usableHostsValue != null) {
            usableHostsValue.setAlpha(0f);
        }
        if(usableHostsValue != null) {
            usableHostsValue.setVisibility(View.VISIBLE);
        }
        if(wildcardMaskValue != null) {
            wildcardMaskValue.setAlpha(0f);
        }
        if(wildcardMaskValue != null) {
            wildcardMaskValue.setVisibility(View.VISIBLE);
        }
        if(binarySubnetMaskValue != null) {
            binarySubnetMaskValue.setAlpha(0f);
        }
        if(binarySubnetMaskValue != null) {
            binarySubnetMaskValue.setVisibility(View.VISIBLE);
        }
        if(ipClassValue != null) {
            ipClassValue.setAlpha(0f);
        }
        if(ipClassValue != null) {
            ipClassValue.setVisibility(View.VISIBLE);
        }
        if(cidrNotationValue != null) {
            cidrNotationValue.setAlpha(0f);
        }
        if(cidrNotationValue != null) {
            cidrNotationValue.setVisibility(View.VISIBLE);
        }
        if(shortNotationValue != null) {
            shortNotationValue.setAlpha(0f);
        }
        if(shortNotationValue != null) {
            shortNotationValue.setVisibility(View.VISIBLE);
        }
        if(inAddrArpaValue != null) {
            inAddrArpaValue.setAlpha(0f);
        }
        if(inAddrArpaValue != null) {
            inAddrArpaValue.setVisibility(View.VISIBLE);
        }
        if(sixToFour != null) {
            sixToFour.setAlpha(0f);
        }
        if(sixToFour != null) {
            sixToFour.setVisibility(View.VISIBLE);
        }

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        if(networkAddressValue != null) {
            networkAddressValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(totalHostsValue != null) {
            totalHostsValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(usableHostsValue != null) {
            usableHostsValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(wildcardMaskValue != null) {
            wildcardMaskValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(binarySubnetMaskValue != null) {
            binarySubnetMaskValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(ipClassValue != null) {
            ipClassValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(cidrNotationValue != null) {
            cidrNotationValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(shortNotationValue != null) {
            shortNotationValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(inAddrArpaValue != null) {
            inAddrArpaValue.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
        if(sixToFour != null) {
            sixToFour.animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(null);
        }
    }

}