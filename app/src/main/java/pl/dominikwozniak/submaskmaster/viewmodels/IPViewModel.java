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
    public String getTotalHosts(){
        return IP.getTotalHosts();
    }

    @Bindable
    public String getUsableHosts(){
        return IP.getUsableHosts();
    }

    @Bindable
    public String getWildcardMask(){
        return IP.getWildcardMask();
    }

    @Bindable
    public String getBinarySubnetMask(){
        return IP.getBinarySubnetMask();
    }

    @Bindable
    public String getIpClass(){
        return IP.getIpClass();
    }

    @Bindable
    public String getCidrNotation(){
        return IP.getCidrNotation();
    }

    @Bindable
    public String getShortNotation(){
        return IP.getShortNotation();
    }

    @Bindable
    public String getInAddrArpa(){
        return IP.getInAddrArpa();
    }

    @Bindable
    public String getSixToFour(){
        return IP.getSixToFour();
    }

    public void onCalculateClicked() {
        /*if (isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);*/
        IP.calculateToBinary();
        notifyPropertyChanged(BR.binarySubnetMask);
        IP.calculateNetworkAddress();
        notifyPropertyChanged(BR.networkAddress);
        IP.calculateTotalHosts();
        notifyPropertyChanged(BR.totalHosts);
        IP.calculateUsableHosts();
        notifyPropertyChanged(BR.usableHosts);
        IP.calculateWildcardMask();
        notifyPropertyChanged(BR.wildcardMask);
        IP.calculateCidrNotation();
        notifyPropertyChanged(BR.cidrNotation);
        notifyPropertyChanged(BR.shortNotation);
        IP.calculateIpClass();
        notifyPropertyChanged(BR.ipClass);
        notifyPropertyChanged(BR.inAddrArpa);
        IP.calculateSixToFour();
        notifyPropertyChanged(BR.sixToFour);
    }

    public boolean isInputDataValid() {
        return true;
        //return !TextUtils.isEmpty(getIpAddress()) && Patterns.EMAIL_ADDRESS.matcher(getIpAddress()).matches() && !TextUtils.isEmpty(getSubnetMask()) && Patterns.EMAIL_ADDRESS.matcher(getSubnetMask()).matches();
    }
}
