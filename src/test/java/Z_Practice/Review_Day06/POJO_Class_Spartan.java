package Z_Practice.Review_Day06;

public class POJO_Class_Spartan {

    private String name;
    private String gender;
    private long phone;

    public POJO_Class_Spartan () {

    }

    public POJO_Class_Spartan (String name, String gender, long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setGender (String gender) {
        this.gender = gender;
    }

    public String getGender () {
        return gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "POJO_Class_Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
