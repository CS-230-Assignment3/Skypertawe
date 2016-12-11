/**
 * @file Main.java
 * @author Luke Harvey
 * @date 11th dec 2016
 *
 * The main file for executing the Skypertawe program
 */

public class Main {
    public static void main(String[] args) {
        AccountsGraph graph = new AccountsGraph();
        new WelcomePanel(graph);
    }
}
