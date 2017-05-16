package com.teamproject_recomm.lxl.teamapplication;

/**
 * Created by lxl on 5/2/2017.
 */

public class Users {
    private String Email;
    private String cnRate;
    private String jpRate;
    private String indRate;
    private String mexRate;
    private String UserId;
    private String CNFrequency;
    private String JPFrequency;
    private String INDFrequency;

    public String getCNFrequency() {
        return CNFrequency;
    }

    public void setCNFrequency(String CNFrequency) {
        this.CNFrequency = CNFrequency;
    }

    public String getJPFrequency() {
        return JPFrequency;
    }

    public void setJPFrequency(String JPFrequency) {
        this.JPFrequency = JPFrequency;
    }

    public String getINDFrequency() {
        return INDFrequency;
    }

    public void setINDFrequency(String INDFrequency) {
        this.INDFrequency = INDFrequency;
    }

    public String getMEXFrequency() {
        return MEXFrequency;
    }

    public void setMEXFrequency(String MEXFrequency) {
        this.MEXFrequency = MEXFrequency;
    }

    private String MEXFrequency;
    //    private String ItalianRate;
    //    private String usRate;
    //    private String frenchRate;
    //    private String taiRate;
    public Users(){};


    public Users(String email,  String userId, String cnRate, String jpRate, String indRate, String mexRate,
    String cFre,String jFre, String INfre, String MEfre) {
        this.Email = email;
        this.cnRate = cnRate;
        this.jpRate = jpRate;
        this.indRate = indRate;
        this.mexRate = mexRate;
        this.UserId = userId;
        this.CNFrequency=cFre;
        this.JPFrequency=jFre;
        this.INDFrequency=INfre;
        this.MEXFrequency=MEfre;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCnRate() {
        return cnRate;
    }

    public void setCnRate(String cnRate) {
        this.cnRate = cnRate;
    }

    public String getJpRate() {
        return jpRate;
    }

    public void setJpRate(String jpRate) {
        this.jpRate = jpRate;
    }

    public String getIndRate() {
        return indRate;
    }

    public void setIndRate(String indRate) {
        this.indRate = indRate;
    }

    public String getMexRate() {
        return mexRate;
    }

    public void setMexRate(String mexRate) {
        this.mexRate = mexRate;
    }

    public String getUserId() {
        return UserId;
    }




}

