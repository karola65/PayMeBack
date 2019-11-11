package com.example.paymeback;

import java.util.ArrayList;

/**
 * This is the Balance Class.
 *
 * Purpose of this Balance Class is to calculate the total amount of money owed between two members of a group,
 * given a list of transactions that involve the two members where one member is the payee and the other the payer.
 *
 *
 * @author Wayne
 */
public class Balance {
    /**
     * The user who is using the app.
     */
    private User pov_user;

    /**
     * The other user that shares a group with pov_user.
     */
    private User other_user;
    /**
     * List containing all transactions between the pov_user and the other_user.
     */
    private ArrayList<Transaction> transaction_list;
    /**
     * Currency of the transaction between the pov_user and the other_user.
     */
    private Currency currency;

    /**
     * Constructor of the Balance class that takes in the following parameters:
     *
     * @param pov_user
     * @param other_user
     * @param transaction_list
     * @param currency
     *
     */
    Balance (User pov_user, User other_user, ArrayList<Transaction> transaction_list, Currency currency){
        this.pov_user = pov_user;
        this.other_user = other_user;
        this.transaction_list = transaction_list;
        this.currency = currency;
    }

    /**
     * displayBalance does the calculations to display the overall amount of money owed between the pov_user and the other_user.
     * @return balance
     */
    public double displayBalance (){
        double balance = 0;
        for (Transaction t :transaction_list){
            if (t.isPayer(this.pov_user)) {
                balance += t.getAmountPerson();
            }
            else {
                balance -= t.getAmountPerson();
            }
        }
        return balance;
    }

    /**
     * getPOVuser returns the pov_user attribute of the Balance class.
     * @return pov_user
     */
    public User getPOVuser () {
        return this.pov_user;
    }

    /**
     * getOtheruser returns the other_user attribute of the Balance class.
     * @return other_user
     */
    public User getOtheruser() {
        return this.other_user;
    }

    /**
     * getTransactionlist returns the transaction_list attribute of the Balance class.
     * @return transaction_list
     */
    public ArrayList<Transaction> getTransactionlist() {
        return this.transaction_list;
    }

    /**
     * getCurrency returns the currency attribute of the Balance class.
     * @return currency
     */
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * setPOVuser sets the pov_user attribute component of the Balance class to be the parameter pov.
     * @param pov
     */
    public void setPOVuser (User pov) {
        this.pov_user = pov;
    }

    /**
     * setOtheruser sets the other_user attribute component of the Balance class to be the parameter other.
     *
     * @param other
     */
    public void setOtheruser(User other) {
        this.other_user = other;
    }

    /**
     * setTransactionlist sets the transaction_list attribute of the Balance class to be the parameter transactionlist.
     * @param transactionlist
     */
    public void setTransactionlist(ArrayList<Transaction> transactionlist) {this.transaction_list = transactionlist; }

    /**
     * setCurrency sets the currency attribute of the Balance class to be the parameter currency.
     * @param currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }   
       
}
