package com.shpota.users;

import org.springframework.util.Assert;

public class User {
    private int id;
    private String lastName, firstName, middleName;

    public User(int id, String lastName, String firstName, String middleName) {
        this(lastName, firstName, middleName);
        Assert.isTrue(0 <= id, "id must be positive");
        this.id = id;
    }

    public User(String lastName, String firstName, String middleName) {
        Assert.hasText(lastName, "lastName must not be null");
        Assert.hasText(firstName, "firstName must not be null");
        Assert.hasText(middleName, "middleName must not be null");
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (!getLastName().equals(user.getLastName())) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        return getMiddleName().equals(user.getMiddleName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getMiddleName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
