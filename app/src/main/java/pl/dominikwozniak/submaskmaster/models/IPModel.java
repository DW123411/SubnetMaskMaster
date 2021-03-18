package pl.dominikwozniak.submaskmaster.models;

public class IPModel {
    private int[] ipAddress;
    private int[] subnetMask;
    private String[] binaryIpAddress;
    private String[] binarySubnetMask;
    private int cidrNotation;
    private String networkAddress;

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
        for (int i = 0; i < 4; i++) {
            this.subnetMask[i] = Integer.parseInt(splitSubnetMask[i]);
        }
    }

    public String getIpAddress(){
        return ipAddress[0]+"."+ipAddress[1]+"."+ipAddress[2]+"."+ipAddress[3];
    }

    public String getSubnetMask(){
        return subnetMask[0]+"."+subnetMask[1]+"."+subnetMask[2]+"."+subnetMask[3];
    }

    public String getNetworkAddress(){
        return networkAddress;
    }

    public String getCidrNotation(){
        if(cidrNotation == -1){
            calculateCidrNotation();
        }
        return "/"+cidrNotation;
    }

    public void calculateNetworkAddress(){
        String networkAddress = "";
        Integer temp;
        for(int i=0; i<4; i++){
            temp = andBinary(new Integer(binaryIpAddress[i]),new Integer(binarySubnetMask[i]));
            networkAddress += Integer.parseInt(temp.toString(),2);
            if(i!=3){
                networkAddress += ".";
            }
        }
        this.networkAddress = networkAddress;
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

    public void calculateToBinary(){
        binaryIpAddress = new String[4];
        binarySubnetMask = new String[4];
        for (int i=0; i<4; i++){
            binaryIpAddress[i] = Integer.toBinaryString(ipAddress[i]);
            int zeroCount = 0;
            zeroCount = 8 - binaryIpAddress[i].length();
            String tmp;
            for(int j=0; j<zeroCount; j++){
                tmp = binaryIpAddress[i];
                binaryIpAddress[i] = "0" + tmp;
            }
            binarySubnetMask[i] = Integer.toBinaryString(subnetMask[i]);
            zeroCount = 8 - binarySubnetMask[i].length();
            for(int j=0; j<zeroCount; j++){
                tmp = binarySubnetMask[i];
                binarySubnetMask[i] = "0" + tmp;
            }
        }
    }

    public Integer andBinary(Integer firstNum, Integer secondNum){
        //int first = Integer.parseInt(firstNum,2);
        StringBuilder output = new StringBuilder();
        int temp;
        while (firstNum != 0 || secondNum != 0) {
            temp = firstNum % 10 & secondNum % 10;
            output.append(temp);

            firstNum = firstNum / 10;
            secondNum = secondNum / 10;
        }
        return Integer.valueOf(output.reverse().toString());
    }
}
