package com.example.bookstore.mainclasses;

public class Book {
    private int id;   // primary
    private String name;
    private String description;
    private int count;
    private double cost;
    private String coverurl;
    private String downloadurl;
    private boolean hasDiscount = false;
    private double discount;
    private boolean isdeleted = false;

    public boolean isDeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }


    public Book(int id, String name, String description, int count, double cost, String coverurl, String downloadurl, boolean hasDiscount, double discount, boolean isdeleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.count = count;
        this.cost = cost;
        this.coverurl = coverurl;
        this.downloadurl = downloadurl;
        this.hasDiscount = hasDiscount;
        this.discount = discount;
        this.isdeleted = isdeleted;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCost() {
        return cost;
    }

    public double getFinalCost() {
        int factor;
        if (hasDiscount) {
            factor = 1;
        } else {
            factor = 0;
        }
        return cost - cost * (factor * (discount / 100));
    }

    public String getCoverurl() {
        if (coverurl.charAt(0) == '\"')
            coverurl = coverurl.substring(1, coverurl.length() - 1);
        if (coverurl.charAt(coverurl.length() - 1) == '\"')
            coverurl = coverurl.substring(0, coverurl.length() - 2);
        return coverurl;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void decreaseCount() {
        count--;
    }
}
