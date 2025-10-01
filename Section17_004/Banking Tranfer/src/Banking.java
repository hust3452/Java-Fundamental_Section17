public class Banking {
    private int id;
    private double balance;

    public Banking() {
    }

    public Banking(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Banking{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
