package bakatd.mc.rualauncher.neko.core;

import java.math.BigInteger;

public class AssetIndexUnit {
    /*
    * "id": "1.12",
		"sha1": "1584b57c1a0b5e593fad1f5b8f78536ca640547b",
		"size": 143138,
		"totalSize": 129336389,
		"url": "https://launchermeta.mojang.com/v1/packages/1584b57c1a0b5e593fad1f5b8f78536ca640547b/1.12.json"*/

    private String id;
    private String sha1;
    private BigInteger size;
    private BigInteger totalSize;
    private String url;

    public AssetIndexUnit(String id, String sha1, BigInteger size, BigInteger totalSize, String url) {
        this.id = id;
        this.sha1 = sha1;
        this.size = size;
        this.totalSize = totalSize;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigInteger getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(BigInteger totalSize) {
        this.totalSize = totalSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
