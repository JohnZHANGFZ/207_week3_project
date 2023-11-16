package entity;

import java.util.List;

public class CommonUser implements User{
    private final String username;
    private final String password;
    private Inventory inventory = null;
    private List<String> allergies = null;

    public CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.inventory = null;
        this.allergies = null;
    }
    public CommonUser(String username, String password, Inventory inventory) {
        this.username = username;
        this.password = password;
        this.inventory = inventory;
        this.allergies = null;
    }
    public CommonUser(String username, String password, List<String> allergies) {
        this.username = username;
        this.password = password;
        this.allergies = allergies;
        this.inventory = null;
    }
    public CommonUser(String username, String password, Inventory inventory, List<String> allgergies) {
        this.username = username;
        this.password = password;
        this.inventory = inventory;
        this.allergies = allgergies;
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // TODO: if this.allergies is null, should we raise exception?
    @Override
    public List<String> getAllergies() {
        return this.allergies;
    }

    // TODO: if this.inventory is null, should we raise exception?
    public Inventory getInventory(){
        return this.inventory;
    }
}
