package model;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Player</b> is a data encapsulation class that represents the player in the
 * game.
 * 
 * @author swammer
 */
public class Player {

    private String name;
    private int curHealth;
    private int maxHealth;
    private List<String> inventory;

    // TODO: add other fields

    public Player(String name, int curHealth, int maxHealth,
            List<String> inventory) {
        this.name = name;
        this.curHealth = curHealth;
        this.maxHealth = maxHealth;

        // copy in
        this.inventory = new ArrayList<>();
        for (String item : inventory) {
            this.inventory.add(item);
        }

        // TODO: instantiate other fields
    }

    // TODO: add methods for inventory management, hasItem, get inventory,
    // addItem, removeItem

    /**
     * Returns the name of this
     * 
     * @return the name of this
     */
    public String name() {
        return name;
    }

    /**
     * Returns the current health of this
     * 
     * @return the current health of this
     */
    public int curHealth() {
        return curHealth;
    }

    /**
     * Returns the max health of this
     * 
     * @return the max health of this
     */
    public int maxHealth() {
        return maxHealth;
    }

    /**
     * Adds the given health (can be negative) to this.
     * 
     * @param health the amount to change the player's current health by
     */
    public void addHealth(int health) {
        setCurHealth(curHealth + health);
    }

    /**
     * Increase the maximum health of this player by the given amount. Or
     * decrease if the given amount is negative.
     * 
     * @param health
     */
    public void addMaximumHealth(int health) {
        setMaxHealth(maxHealth + health);
    }

    /**
     * Changes the name of this to the given name
     * 
     * @param name the new name of this
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the current health of this to the given int
     * 
     * @param health the new current health to set this at
     */
    public void setCurHealth(int health) {
        curHealth = health;
    }

    /**
     * Changes the max health of this to the given int
     * 
     * @param health the new max health to set this at
     */
    public void setMaxHealth(int health) {
        maxHealth = health;
    }

    /**
     * Adds the given item to this player's inventory.
     * 
     * @param item the item to give the player
     */
    public void giveItem(String item) {
        inventory.add(item);
    }

    /**
     * Removes one of the given item from this player's inventory. Returns true
     * if this item is in the player's inventory and therefore removed.
     * 
     * @param item the item to remove from the player's inventory
     */
    public void removeItem(String item) {
        inventory.remove(item);
    }

    /**
     * Returns true if and only if this player's current health is zero or less.
     * 
     * @return true iff this player's current health is zero or less
     */
    public boolean isDead() {
        return curHealth <= 0;
    }
}
