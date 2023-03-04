package contacts;

public class Organization extends Contact {
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
}
