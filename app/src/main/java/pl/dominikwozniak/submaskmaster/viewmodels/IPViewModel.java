package pl.dominikwozniak.submaskmaster.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.library.baseAdapters.BR;

import java.util.Arrays;

import pl.dominikwozniak.submaskmaster.R;
import pl.dominikwozniak.submaskmaster.models.IPModel;

public class IPViewModel extends BaseObservable {
    private IPModel IP;

    @Bindable
    private String toastMessage = null;

    public IPViewModel(){
        IP = new IPModel();
    }

    public void setIpAddress(String ipAddress){
        String[] splitIpAddress = ipAddress.split("\\.");
        if(splitIpAddress.length == 4 && !splitIpAddress[0].equals("") && !splitIpAddress[1].equals("") && !splitIpAddress[2].equals("") && !splitIpAddress[3].equals("")) {
            if(!isNan(splitIpAddress) && Integer.parseInt(splitIpAddress[0]) <= 255 && Integer.parseInt(splitIpAddress[1]) <= 255 && Integer.parseInt(splitIpAddress[2]) <= 255 && Integer.parseInt(splitIpAddress[3]) <= 255) {
                IP.setIpAddress(ipAddress);
                notifyPropertyChanged(BR.ipAddress);
                toastMessage = "Success";
            }else{
                toastMessage = "IP Address or subnet mask is not valid. Displaying results for "+IP.getIpAddress()+", "+IP.getSubnetMask();
            }
        }else{
            toastMessage = "IP Address or subnet mask is not valid. Displaying results for "+IP.getIpAddress()+", "+IP.getSubnetMask();
        }
    }

    public void setSubnetMask(String subnetMask){
        String[] splitSubnetMask = subnetMask.split("\\.");
        if(splitSubnetMask.length == 4 && !splitSubnetMask[0].equals("") && !splitSubnetMask[1].equals("") && !splitSubnetMask[2].equals("") && !splitSubnetMask[3].equals("")) {
            if(!isNan(splitSubnetMask) && Arrays.asList(128, 192, 224, 240, 248, 252, 254, 255).contains(Integer.parseInt(splitSubnetMask[0])) && Arrays.asList(0, 128, 192, 224, 240, 248, 252, 254, 255).contains(Integer.parseInt(splitSubnetMask[1])) && Arrays.asList(0, 128, 192, 224, 240, 248, 252, 254, 255).contains(Integer.parseInt(splitSubnetMask[2])) && Arrays.asList(0, 128, 192, 224, 240, 248, 252, 254, 255).contains(Integer.parseInt(splitSubnetMask[3])) && Integer.parseInt(splitSubnetMask[1]) <= Integer.parseInt(splitSubnetMask[0]) && Integer.parseInt(splitSubnetMask[2]) <= Integer.parseInt(splitSubnetMask[0]) && Integer.parseInt(splitSubnetMask[3]) <= Integer.parseInt(splitSubnetMask[0]) && Integer.parseInt(splitSubnetMask[2]) <= Integer.parseInt(splitSubnetMask[1]) && Integer.parseInt(splitSubnetMask[3]) <= Integer.parseInt(splitSubnetMask[1]) && Integer.parseInt(splitSubnetMask[3]) <= Integer.parseInt(splitSubnetMask[2]) && isSubnetMaskValid(splitSubnetMask)) {
                IP.setSubnetMask(subnetMask);
                notifyPropertyChanged(BR.subnetMask);
                toastMessage = "Success";
            }else{
                toastMessage = "IP Address or subnet mask is not valid. Displaying results for "+IP.getIpAddress()+", "+IP.getSubnetMask();
            }
        }else{
            toastMessage = "IP Address or subnet mask is not valid. Displaying results for "+IP.getIpAddress()+", "+IP.getSubnetMask();
        }
    }

    public String getToastMessage() {
        return toastMessage;
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
        notifyPropertyChanged(BR.toastMessage);
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

    public boolean isSubnetMaskValid(String[] splitSubnetMask){
        boolean result;
        if(Integer.parseInt(splitSubnetMask[0]) != 255){
            if(Integer.parseInt(splitSubnetMask[1]) == 0){
                if(Integer.parseInt(splitSubnetMask[2]) == 0){
                    if(Integer.parseInt(splitSubnetMask[3]) == 0){
                        result = true;
                    }else{
                        result = false;
                    }
                }else{
                    result = false;
                }
            }else{
                result = false;
            }
        }else{
            if(Integer.parseInt(splitSubnetMask[1]) != 255){
                if (Integer.parseInt(splitSubnetMask[2]) == 0){
                    if (Integer.parseInt(splitSubnetMask[3]) == 0){
                        result = true;
                    }else{
                        result = false;
                    }
                }else{
                    result = false;
                }
            }else{
                if(Integer.parseInt(splitSubnetMask[2]) != 255){
                    if(Integer.parseInt(splitSubnetMask[3]) == 0){
                        result = true;
                    }else{
                        result = false;
                    }
                }else{
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isNan(String[] addressOrMask){
        boolean result = false;
        for(int i=0; i<4; i++){
            for (int j=0; j<addressOrMask[i].length(); j++){
                if(!Arrays.asList('0','1','2','3','4','5','6','7','8','9').contains(addressOrMask[i].charAt(j))){
                    result = true;
                }
            }
        }
        return result;
    }
}
