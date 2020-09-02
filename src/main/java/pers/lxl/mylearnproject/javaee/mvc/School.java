package pers.lxl.mylearnproject.javaee.mvc;

public class School {
    public String name;
    public String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
