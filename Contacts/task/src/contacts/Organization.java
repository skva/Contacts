package contacts;

import java.io.Serializable;

public class Organization extends Contact implements Serializable {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getType() {
        return "organization";
    }

    @Override
    public String getFullName() {
        return getName();
    }

    @Override
    public String toString() {
        return "Organization name: " + getName() + "\n"
                + "Address: " + getAddress() + "\n"
                + "Number: " + getPhone() + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdit() + "\n";
    }

    @Override
    public String getAllFields() {
        return getName().toLowerCase() + " " + getAddress().toLowerCase() + " "
                + getPhone().toLowerCase();
    }
}
