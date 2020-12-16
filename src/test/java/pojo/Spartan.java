package pojo;

// A POJO (Plain Old Java Object)
// is used to create object that represent data
// it must have encapuslated fields and public constructor (no arg)
public class Spartan {

    private String name;
    private String gender;
    private String phone;


    // this is the required constructor
    public Spartan () {

    }

    public Spartan(String name, String gender, String phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
