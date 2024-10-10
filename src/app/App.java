package app;

import domain.Controller;
import domain.DataLists;
import domain.IDataStore;


public class App {
    public static void main(String[] args) {

        IDataStore dataLists = new DataLists();  //to store all the data
        Controller controller = new Controller(dataLists); //  handle all operations
        dataLists.preloadTestData(); // Preload some test data  

        ConsoleUI UI = new ConsoleUI(controller);
        UI.start();
    }
}