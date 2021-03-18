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
        String[] splitIpAddress = ipAddress.split("\\.");
        if(splitIpAddress.length == 4 && !splitIpAddress[0].equals("") && !splitIpAddress[1].equals("") && !splitIpAddress[2].equals("") && !splitIpAddress[3].equals("")) {
            IP.setIpAddress(ipAddress);
            notifyPropertyChanged(BR.ipAddress);
        }else{

        }
    }

    public void setSubnetMask(String subnetMask){
        String[] splitSubnetMask = subnetMask.split("\\.");
        if(splitSubnetMask.length == 4 && !splitSubnetMask[0].equals("") && !splitSubnetMask[1].equals("") && !splitSubnetMask[2].equals("") && !splitSubnetMask[3].equals("")) {
            IP.setSubnetMask(subnetMask);
            notifyPropertyChanged(BR.subnetMask);
        }else{

        }
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
    public String getNetworkAddress(){
        return IP.getNetworkAddress();
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
        IP.calculateToBinary();
        IP.calculateNetworkAddress();
        notifyPropertyChanged(BR.networkAddress);
        IP.calculateCidrNotation();
        notifyPropertyChanged(BR.cidrNotation);
    }

    public boolean isInputDataValid() {
        return true;
        //return !TextUtils.isEmpty(getIpAddress()) && Patterns.EMAIL_ADDRESS.matcher(getIpAddress()).matches() && !TextUtils.isEmpty(getSubnetMask()) && Patterns.EMAIL_ADDRESS.matcher(getSubnetMask()).matches();
    }
}
