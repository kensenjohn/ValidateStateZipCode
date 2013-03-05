package com.validate;

/**
 * Created with IntelliJ IDEA.
 * User: kensen
 * Date: 1/12/13
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZipCodeRanges {

    private Integer startZip;
    private Integer endZip;
    private boolean isSingleZip = false;

    public Integer getStartZip() {
        return startZip;
    }

    public void setStartZip(Integer startZip) {
        this.startZip = startZip;
    }

    public Integer getEndZip() {
        return endZip;
    }

    public void setEndZip(Integer endZip) {
        this.endZip = endZip;
    }

    public boolean isSingleZip() {
        return isSingleZip;
    }

    public void setSingleZip(boolean singleZip) {
        isSingleZip = singleZip;
    }

    @Override
    public String toString() {
        return "com.validate.ZipCodeRanges{" +
                "startZip=" + startZip +
                ", endZip=" + endZip +
                ", isSingleZip=" + isSingleZip +
                '}';
    }
}
