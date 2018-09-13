package model;

public class ItemSearchModel {
    public String description;
    public String descriptionContains = "description contains ";
    //https://rest.avatax.com/api/v2/items?%24filter=description%20contains%20mug
//    {
//        "@recordsetCount": 0,
//            "value": [
//        {
//            "id": 56789,
//                "companyId": 12345,
//                "itemCode": "CERMUG",
//                "taxCode": "P0000000",
//                "description": "Ceramic Mug"
//        }
//  ],
//        "@nextLink": "string"
//    }
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
