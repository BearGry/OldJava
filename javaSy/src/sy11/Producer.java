package sy11;

public class Producer extends Thread{
    Ticket ticket;

    public Producer(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (ticket.currentNum < ticket.size)
            ticket.produce();
    }
}
