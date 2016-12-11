public class Main {
    public static void main(String[] args) {
        AccountsGraph graph = new AccountsGraph();
        //new WelcomePanel(graph);

        Account myAccount = new Account("Luke", "Luke", "Harvey", "", "16/11/94", "Bristol", "password", 74279);
        Account otherAccount = new Account("John", "Cena", "test", "", "16/11/94", "Bristol", "password", 74279);

        new ChatPanel(myAccount, otherAccount, graph);
    }
}
