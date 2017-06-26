package demo.json;

import java.io.Serializable;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/26.
 * Java EE_1702.
 */
public class Weather implements Serializable {
    private String tmp;
    private String hum;
    private String spd;

    public Weather(String tmp, String hum, String spd) {
        this.tmp = tmp;
        this.hum = hum;
        this.spd = spd;
    }

    public Weather(String tmp) {
        this.tmp = tmp;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    @Override
    public String toString() {
        return "{" +
                "tmp='" + tmp + '\'' +
                ", hum='" + hum + '\'' +
                ", spd='" + spd + '\'' +
                '}';
    }
}
