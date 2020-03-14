package bakatd.mc.rualauncher.neko.core.json;

import java.math.BigInteger;

public class PatchDownloadUnit {
    private String name;
    private String sha1;
    private BigInteger size = new BigInteger(0+"");
    private String url;
    private String path;

    public PatchDownloadUnit(String name, String sha1, BigInteger size, String url, String path) {
        this.name = name;
        this.sha1 = sha1;
        this.size = size;
        this.url = url;
        this.path = path;
    }

    public PatchDownloadUnit(String name, String sha1, BigInteger size, String url) {
        this.name = name;
        this.sha1 = sha1;
        this.size = size;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
