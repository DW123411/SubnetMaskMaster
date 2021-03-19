package pl.dominikwozniak.submaskmaster.models;

public class IPModel {
    private int[] ipAddress, subnetMask;
    private String[] binaryIpAddress, binarySubnetMask;
    private int cidrNotation, totalHosts, usableHosts;
    private String networkAddress, wildcardMask, ipClass, sixToFour;

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

    public String getTotalHosts(){
        return Integer.toString(totalHosts);
    }

    public String getUsableHosts(){
        return Integer.toString(usableHosts);
    }

    public String getWildcardMask(){
        return wildcardMask;
    }

    public String getBinarySubnetMask(){
        if(binarySubnetMask == null){
            calculateToBinary();
        }
        return binarySubnetMask[0] + "." + binarySubnetMask[1] + "." + binarySubnetMask[2] + "." + binarySubnetMask[3];
    }

    public String getIpClass(){
        return ipClass;
    }

    public String getCidrNotation(){
        if(cidrNotation == -1){
            calculateCidrNotation();
        }
        return "/"+cidrNotation;
    }

    public String getShortNotation(){
        if(cidrNotation == -1){
            calculateCidrNotation();
        }
        return ipAddress[0] + "." + ipAddress[1] + "." + ipAddress[2] + "." + ipAddress[3] + "/" + cidrNotation;
    }

    public String getInAddrArpa(){
        return ipAddress[3] + "." + ipAddress[2] + "." + ipAddress[1] + "." + ipAddress[0] + ".in-addr.arpa";
    }

    public String getSixToFour(){
        return sixToFour;
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

    public void calculateTotalHosts(){
        String binaryMask = binarySubnetMask[0] + "." + binarySubnetMask[1] + "." + binarySubnetMask[2] + "." + binarySubnetMask[3];
        int subnetCount = 0;
        char one = '1';
        for(int i=0; i<binaryMask.length(); i++){
            if(binaryMask.charAt(i) == one){
                subnetCount++;
            }
        }
        int hostsCount = 32 - subnetCount;
        String binaryHosts = "";
        for(int i=0; i<hostsCount; i++){
            binaryHosts += "1";
        }
        totalHosts = Integer.parseInt(binaryHosts,2);
    }

    public void calculateUsableHosts(){
        usableHosts = totalHosts - 2;
    }

    public void calculateWildcardMask(){
        String[] wildcardMask = new String[4];
        for(int i=0; i<4; i++){
            wildcardMask[i] = "";
            for(int j=0; j<binarySubnetMask[i].length(); j++){
                if(binarySubnetMask[i].charAt(j) == '1'){
                    wildcardMask[i] += "0";
                }else{
                    wildcardMask[i] += "1";
                }
            }
        }
        this.wildcardMask = Integer.parseInt(wildcardMask[0],2) + "." + Integer.parseInt(wildcardMask[1],2) + "." + Integer.parseInt(wildcardMask[2],2) + "." + Integer.parseInt(wildcardMask[3],2);
    }

    public void calculateIpClass(){
        String ipClass;
        if(cidrNotation <= 8){
            ipClass = "A";
        }else if(cidrNotation > 8 && cidrNotation <= 16){
            ipClass = "B";
        }else{
            ipClass = "C";
        }
        if(ipAddress[0] > 223 && ipAddress[0] <= 239){
            ipClass = "D";
        }else if(ipAddress[0] > 239){
            ipClass = "E";
        }
        this.ipClass = ipClass;
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

    public void calculateSixToFour(){
        String[] hexIpAddress = new String[4];
        hexIpAddress[0] = Integer.toHexString(ipAddress[0]);
        hexIpAddress[1] = Integer.toHexString(ipAddress[1]);
        hexIpAddress[2] = Integer.toHexString(ipAddress[2]);
        hexIpAddress[3] = Integer.toHexString(ipAddress[3]);
        String temp;
        for(int i=0; i<hexIpAddress.length; i++){
            if(hexIpAddress[i].length() == 1){
                temp = hexIpAddress[i];
                hexIpAddress[i] = "0" + temp;
            }
        }
        sixToFour = "2002:" + hexIpAddress[0] + hexIpAddress[1] + ":" + hexIpAddress[2] + hexIpAddress[3] + "::/48";
    }

    public void calculateToBinary(){
        binaryIpAddress = new String[4];
        binarySubnetMask = new String[4];
        for (int i=0; i<4; i++){
            binaryIpAddress[i] = Integer.toBinaryString(ipAddress[i]);
            int zeroCount = 8 - binaryIpAddress[i].length();
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
