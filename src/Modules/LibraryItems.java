package Modules;

public class LibraryItems {
   static int ItemId;
   static String ItemName;
   int ItemYear;
   int ItemQuantity;
   boolean ItemAvailable;

   public LibraryItems(int ItemId, String ItemName, int ItemYear, int ItemQuantity) {
       this.ItemId = ItemId;
       this.ItemName = ItemName;
       this.ItemYear = ItemYear;
       this.ItemQuantity = ItemQuantity;
   }

   public int getItemId () {return this.ItemId;}

   public String getItemName () {return this.ItemName;}

   public int getItemYear () {return this.ItemYear;}

   public void getItemQuantity () {
       System.out.println("We have " +this.ItemQuantity +" copies in the stock");}

   public boolean getItemAvailable () {
       if (this.ItemQuantity > 0) {
           this.ItemAvailable = true;
       }else {
           this.ItemAvailable = false;
       }
       return this.ItemAvailable;}
}
