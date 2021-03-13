package pl.dominikwozniak.submaskmaster.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.library.baseAdapters.BR;
import pl.dominikwozniak.submaskmaster.models.IPModel;

public class IPViewModel extends BaseObservable {
    private IPModel IP;

    public IPViewModel(){
        IP = new IPModel();
    }

    public void setIpAddress(String ipAddress){
        IP.setIpAddress(ipAddress);
        notifyPropertyChanged(BR.ipAddress);
    }

    public void setSubnetMask(String subnetMask){
        IP.setSubnetMask(subnetMask);
        notifyPropertyChanged(BR.subnetMask);
    }

    @Bindable
    public String getIpAddress(){
        return IP.getIpAddress();
    }

    @Bindable
    public String getSubnetMask(){
        return IP.getSubnetMask();
    }

    @Bindable
    public String getCidrNotation(){
        return IP.getCidrNotation();
    }

    public void onCalculateClicked() {
        /*if (isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);*/
        IP.calculateCidrNotation();
        notifyPropertyChanged(BR.cidrNotation);
    }

    public boolean isInputDataValid() {
        return true;
        //return !TextUtils.isEmpty(getIpAddress()) && Patterns.EMAIL_ADDRESS.matcher(getIpAddress()).matches() && !TextUtils.isEmpty(getSubnetMask()) && Patterns.EMAIL_ADDRESS.matcher(getSubnetMask()).matches();
    }
}
