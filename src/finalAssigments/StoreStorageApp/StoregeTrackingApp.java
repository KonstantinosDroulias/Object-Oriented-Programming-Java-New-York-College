package finalAssigments.StoreStorageApp;

import finalAssigments.StoreStorageApp.GUI.StoreTrackingGUI;

public class StoregeTrackingApp {
    public static void main(String[] args) {
        new StoreTrackingGUI();
        new DBConnection().getConnection();
    }
}
