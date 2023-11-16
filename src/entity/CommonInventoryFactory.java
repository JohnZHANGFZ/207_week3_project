package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonInventoryFactory implements InventoryFactory {
    IngredientFactory factory = new CommonIngredientFactory();

    @Override
    public Inventory create(List<String> items) {
        Inventory inventory = new CommonInventory();
        List listIngredient = new ArrayList<CommonIngredient>();
        for (int i = 0; i < items.size(); i++) {
            Ingredient item = factory.create(items.get(i));
            listIngredient.add(item);
        }
        inventory.addItems(listIngredient);
        return inventory;
    }
}
