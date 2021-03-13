package pl.dominikwozniak.submaskmaster.models;

public class IPModel {
    private int[] ipAddress;
    private int[] subnetMask;
    private int cidrNotation;

    public IPModel(){
        ipAddress = new int[4];
        subnetMask = new int[4];
        ipAddress[0] = 192;
        ipAddress[1] = 168;
        ipAddress[2] = 1;
        ipAddress[3] = 1;
        subnetMask[0] = 255;
        subnetMask[1] = 255;
        subnetMask[2] = 255;
        subnetMask[3] = 0;
        cidrNotation = -1;
    }

    public void setIpAddress(String ipAddress) {
        String[] splitIpAddress = ipAddress.split("\\.");
        for (int i=0; i<4; i++) {
            this.ipAddress[i] = Integer.parseInt(splitIpAddress[i]);
        }
    }

    public void setSubnetMask(String subnetMask){
        String[] splitSubnetMask = subnetMask.split("\\.");
        for (int i=0; i<4; i++) {
            this.subnetMask[i] = Integer.parseInt(splitSubnetMask[i]);
        }
    }

    public String getIpAddress(){
        return ipAddress[0]+"."+ipAddress[1]+"."+ipAddress[2]+"."+ipAddress[3];
    }

    public String getSubnetMask(){
        return subnetMask[0]+"."+subnetMask[1]+"."+subnetMask[2]+"."+subnetMask[3];
    }

    public String getCidrNotation(){
        /*if(cidrNotation == -1){
            calculateCidrNotation();
        }*/
        return "/"+cidrNotation;
    }

    public String calculateNetworkAddress(){
        return "";
    }

    public void calculateCidrNotation(){
        String binaryMask = Integer.toBinaryString(subnetMask[0])+Integer.toBinaryString(subnetMask[1])+Integer.toBinaryString(subnetMask[2])+Integer.toBinaryString(subnetMask[3]);
        int cidr = 0;
        char one = '1';
        for(int i=0; i<binaryMask.length(); i++){
            if(binaryMask.charAt(i) == one){
                cidr++;
            }
        }
        cidrNotation = cidr;
    }
}
