package animalmanagementsystem.katekelly.com.animalmanagement.pet;

/**
 * Created by katekelly on 05/02/2018.
 */

public class Pet {

    private int id;
    private String petOwnerName;
    private String petOwnerPhone;
    private String petName;
    private String petGender;
    private String petAge;


    public Pet() {
    }

    public Pet(int id, String petOwnerName, String petOwnerPhone, String petName, String petGender, String petAge) {
        this.id = id;
        this.petOwnerName = petOwnerName;
        this.petOwnerPhone = petOwnerPhone;
        this.petName = petName;
        this.petGender = petGender;
        this.petAge = petAge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet(String petOwnerName, String petOwnerPhone, String petName, String petGender, String petAge) {
        this.petOwnerName = petOwnerName;
        this.petOwnerPhone = petOwnerPhone;
        this.petName = petName;
        this.petGender = petGender;
        this.petAge = petAge;
    }

    public String getPetOwnerName() {
        return petOwnerName;
    }

    public void setPetOwnerName(String petOwnerName) {
        this.petOwnerName = petOwnerName;
    }

    public String getPetOwnerPhone() {
        return petOwnerPhone;
    }

    public void setPetOwnerPhone(String petOwnerPhone) {
        this.petOwnerPhone = petOwnerPhone;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }
}
