package com.example.myteamproject_chanmin.DTO;

public class TrainerDTO {

    String gym_name , trainer_name , phone_number , price , trainer_picture;

    public TrainerDTO() {}

    public TrainerDTO(String gym_name, String trainer_name, String phone_number, String price, String trainer_picture) {
        this.gym_name = gym_name;
        this.trainer_name = trainer_name;
        this.phone_number = phone_number;
        this.price = price;
        this.trainer_picture = trainer_picture;
    }

    public String getGym_name() {
        return gym_name;
    }

    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTrainer_picture() {
        return trainer_picture;
    }

    public void setTrainer_picture(String trainer_picture) {
        this.trainer_picture = trainer_picture;
    }
}
