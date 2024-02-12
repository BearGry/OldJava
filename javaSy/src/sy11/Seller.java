package sy11;

public class Seller implements Runnable{
    Ticket ticket;

    public Seller(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while(ticket.selledMaxNum < ticket.size)
            ticket.sell();
    }
}
