package com.rok.demoproject.PojoClass;

public class AppliancesListPojoCalss {

    String name,model,purchaseDate,warrantyDate,nextServiceDate;

    public AppliancesListPojoCalss(String name, String model, String purchaseDate, String warrantyDate) {
        this.name = name;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.warrantyDate = warrantyDate;
        this.nextServiceDate = nextServiceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(String warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public String getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(String nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }
}
