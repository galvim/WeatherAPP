package com.example.rent.myapplication;

// BUILDER

public class SimpleModel {
    private String name;
    private String surname;
    private String address;
    private String phone;

    public SimpleModel(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }

    public static class Builder {
        private String name;
        private String surname;
        private String address;
        private String phone;

        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withSurname(String surname){
            this.surname = surname;
            return this;
        }
        public Builder withAddress(String address){
            this.address = address;
            return this;
        } public Builder withPhone(String phone){
            this.phone = phone;
            return this;
        }

        public SimpleModel build(){
            return new SimpleModel(name,surname,address,phone);
        }



    }
}
