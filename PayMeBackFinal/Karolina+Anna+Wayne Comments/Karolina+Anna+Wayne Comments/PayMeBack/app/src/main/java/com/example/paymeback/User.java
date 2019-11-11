package com.example.paymeback;

/**
 *
 * User class
 *
 * A user class stores information about the user.
 *
 *
 * @author Karolina
 */

import java.io.Serializable;

public class User implements Serializable {

    /**
     * Username of user. User uses those to lof in.
     */

    String userName;

    /**
     * First name of the user.
     */
    String firstName;

    /**
    Last name of the user.
     */
    String lastName;

    /**
     * Password of the user.
     */
    String password;
    /**
     * Email of the user.
     */
    String email;
    /**
     * Answer to security question. This will let user reset the password.
     */
    String answer;
    /**
     * User id.
     */
    int id;

    /**
     * A constructor function with the parameters.
     *
     * This function let creates user while passing all the information about them.
     * @param userName
     * @param firstName
     * @param lastName
     * @param password
     * @param email
     * @param answer
     * @param id
     */

    public User(String userName, String firstName, String lastName, String password, String email, String answer, int id )
    {
        this.email = email;
        this.firstName= firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.answer = answer;

    }

    /**
     * An empty constructor. It lets create empty user.
     */
    public User()
    {

    }

    /**
     * Getter method for email.
     * @return
     */


    public String getEmail() {return this.email;}
    /**
     * Getter method for firstname.
     * @return
     */

    public String getFirstName()
    {
        return this.firstName;
    }
    /**
     * Getter method for answer
     * @return
     */

    public String getAnswer() {return this.answer;}

    /**
     * Getter method for lastname.
     * @return
     */


    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * Getter method for username.
     * @return
     */


    public String getUserName()
    {
        return this.userName;
    }

    /**
     * Getter method for password.
     * @return
     */

    public String getPassword()
    {
        return this.password;
    }

    /**
     * Getter method for ID.
     * @return
     */


    public int getID()
    {
        return this.id;
    }
    /**
     * Setter method for password.
     * @return
     */


    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Method equals.
     *
     * A method enables to compare to users based on their ids.
     *
     * @param u
     * @return
     */


    public boolean equals(User u) {
        if(this.id == u.getID()) {
            return true;
        }
        else {return false;}
    }

    public String toString(){
        return this.firstName;
    }

}





