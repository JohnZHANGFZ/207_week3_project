package use_case.collection.add_item;

import java.util.List;

public class AddItemOutputData {
    private final List<String> ingredientList;
    private boolean useCaseFailed;


    public AddItemOutputData(List<String> ingredientList, boolean useCaseFailed) {
        this.ingredientList = ingredientList;
        this.useCaseFailed = useCaseFailed;
    }
}
