package com.abhiarya.myapplication;

public class Words {
    private final String eng;
    private final String hin;
    private final int aud;
    private int img= NO_IMAGE_PROVIDED;
    private  static  final int NO_IMAGE_PROVIDED=-1;


    public  Words(String eng, String hin,int aud){
        this.eng=eng;
        this.hin=hin;
        this.aud=aud;

    }

    public  Words(String eng, String hin, int img, int aud) {
        this.eng = eng;
        this.hin = hin;
        this.img = img;
        this.aud= aud;
    }
    public String geteng(){
        return eng;
    }

    public String getHin(){
        return  hin;
    }
    public int getImg(){
        return  img;
    }
    public int getAud(){
        return aud;
    }
    public  boolean hasImage() {
        return img != NO_IMAGE_PROVIDED;
    }
}
