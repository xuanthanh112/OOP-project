package com.example.backend.Model.NftFloor;

public class NFT {
    private String id,name,url;
    private double floorPrice,volume,volumeChange;
    private int numOwners,totalSupply;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public double getFloorPrice() {
        return floorPrice;
    }

    public double getVolume() {
        return volume;
    }

    public double getVolumeChange() {
        return volumeChange;
    }


    public int getNumOwners() {
        return numOwners;
    }

    public int getTotalSupply() {
        return totalSupply;
    }

    public NFT(String id, String name, String url, double floorPrice, double volume, double volumeChange, int numOfSales, int numOwners, int totalSupply) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.floorPrice = floorPrice;
        this.volume = volume;
        this.volumeChange = volumeChange;
        this.numOwners = numOwners;
        this.totalSupply = totalSupply;
    }


}
