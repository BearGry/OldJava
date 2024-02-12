package sy11;

public class Tester {
    public static void main(String args[]){
        Ticket ticket = new Ticket(200);
        new Producer(ticket).start();
        new Thread(new Seller(ticket)).start();
    }
}
