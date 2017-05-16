package com.teamproject_recomm.lxl.teamapplication;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lxl on 5/2/2017.
 */

public class UserInfoManagment {
    private List<String> stringList;
    private Users currentUser;
    private String currentUserE;
    private List<Users> wholeUserInfoList;
    private HashMap<String, Double> cosMap=new HashMap<>();

    public UserInfoManagment(String CurrentUser, List<Users> userInfoList) {

        currentUserE = CurrentUser;
        wholeUserInfoList = userInfoList;

    }

    public List<Users> newListWithoutCurrentUser() {

        int currentUserIndex=-1;
        for(int i=0; i<wholeUserInfoList.size();i++){
            if (currentUserE.equalsIgnoreCase(wholeUserInfoList.get(i).getEmail())){
                currentUserIndex=i;
                break;
            }
        }
        currentUser=wholeUserInfoList.get(currentUserIndex);
        wholeUserInfoList.remove(wholeUserInfoList.get(currentUserIndex));

        return wholeUserInfoList;
    }
    public Users getRecommandUserInfor(List<Users> tempList,String Stringvalue){
        int currentUserIndex=-1;
        for(int i=0; i<tempList.size();i++) {
            if (Stringvalue.equalsIgnoreCase(tempList.get(i).getEmail())) {
                currentUserIndex = i;
                break;
            }
        }
            Users recommandUser=tempList.get(currentUserIndex);
            return recommandUser;

    }
    public void CosimMap(){
        Cossim cos_sim=new Cossim();

        String currename =currentUser.getEmail();
        double currencnRate= Double.parseDouble(currentUser.getCnRate());
        double currentjpRate=Double.parseDouble(currentUser.getJpRate());
        double currentIndRate=Double.parseDouble(currentUser.getIndRate());
        double currentMexRate=Double.parseDouble(currentUser.getMexRate());
        double tempCn=Double.parseDouble(currentUser.getCNFrequency());
        double tempjp=Double.parseDouble(currentUser.getJPFrequency());
        double tempInd=Double.parseDouble(currentUser.getINDFrequency());
        double tempMex=Double.parseDouble(currentUser.getMEXFrequency());

        double[] currentUserInfo=findDoubleArrayRate(currencnRate,currentjpRate,currentIndRate,currentMexRate,
        tempCn,tempjp,tempInd,tempMex);
        for(int i=0; i<wholeUserInfoList.size();i++){
            String uname =wholeUserInfoList.get(i).getEmail();
            double cnRate=Double.parseDouble(wholeUserInfoList.get(i).getCnRate());
            double jpRate=Double.parseDouble(wholeUserInfoList.get(i).getJpRate());
            double IndRate=Double.parseDouble(wholeUserInfoList.get(i).getIndRate());
            double MexRate=Double.parseDouble(wholeUserInfoList.get(i).getMexRate());
            double cnf=Double.parseDouble(wholeUserInfoList.get(i).getCNFrequency());
            double jpf=Double.parseDouble(wholeUserInfoList.get(i).getJPFrequency());
            double indf=Double.parseDouble(wholeUserInfoList.get(i).getINDFrequency());
            double mf=Double.parseDouble(wholeUserInfoList.get(i).getMEXFrequency());
            double[] templeUserInfo=findDoubleArrayRate(cnRate,jpRate,IndRate,MexRate,
            cnf,jpf,indf,mf);
            cosMap.put(uname,cos_sim.cossim(currentUserInfo,templeUserInfo));

        }

    }
    public String simiUser() {
        Similar_User smus = new Similar_User(cosMap);
        return smus.Similar_User();
    }
    public double[] findDoubleArrayRate(double cnRate,double jpRate,double indRate, double mexRate,
                                        double cnFre, double jpFre, double indFre, double mexFre){
        double sum=cnFre+jpFre+indFre+mexFre;
        double tempCN=cnFre/sum;
        double tempJP=jpFre/sum;
        double tempIN=indFre/sum;
        double tempMX=mexFre/sum;
        double[] temp={cnRate*tempCN,jpRate*tempJP,indRate*tempIN,mexRate*tempMX};
        return temp;
    }

}
