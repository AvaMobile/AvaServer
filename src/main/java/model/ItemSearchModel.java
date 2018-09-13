package model;

public class ItemSearchModel {
    public String description;
    public String descriptionContains = "description contains ";

    public ItemSearchModel(String description) {
        //this creates a description contains query
        this.description = descriptionContains + description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription() {
        this.description = descriptionContains + description;
    }


    public static void main(String[] args) {
        ItemSearchModel search = new ItemSearchModel("ceramic");
        String searchTerm = search.getDescription();
        System.out.println(searchTerm);

    }
}
