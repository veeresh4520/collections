
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

class Account implements Serializable {

    public String name;
    public String an;
    public double balnce;

    Account(String name, String an, double balance) {
        this.name = name;
        this.an = an;
        this.balnce = balance;
    }

    public String toString() {
        return "Name :" + name + " Acount Number: " + an + " Balance :" + balnce;
    }

}

public class MenuDrivenCollections {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Account acc = null;
        HashMap<String, Account> a = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("Accounts.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                acc = (Account) ois.readObject();
                System.out.println(acc);
                a.put(acc.an, acc);
            }
            fis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream fos = new FileOutputStream("Accounts.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        int choice;
        do {
            System.out.println("1.Create Account");
            System.out.println("2.Delete Acount");
            System.out.println("3.View Account");
            System.out.println("4.View All Acoounts");
            System.out.println("5.Save Accounts");
            System.out.println("6.Exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            switch (choice) {
                case 1:
                    System.out.println("Enter detials Name ,Account Number , Balance");
                    String name = sc.nextLine();
                    String an = sc.nextLine();
                    Double bal = sc.nextDouble();
                    acc = new Account(name, an, bal);
                    a.put(an, acc);
                    System.out.println("Account is Created for :" + name);
                    break;
                case 2:
                    System.out.println("Enter Account Number to Delete");
                    sc.nextLine();
                    an = sc.nextLine();
                    a.remove(an);
                    break;
                case 3:
                    System.out.println("Enter Account Number to View:");
                    an = sc.nextLine();
                    acc = a.get(an);
                    System.out.println(acc);
                    break;
                case 4:
                    a.forEach((k, v) -> System.out.println(k + " " + v));
                    break;
                case 5:
                case 6:
                    oos.write(a.size());
                    for (Account b : a.values()) {
                        oos.writeObject(b);
                    }
                    break;
                default:
                    System.out.println("Invalid option Bhai");
            }
        } while (choice != 6);
        oos.flush();
        oos.close();
        fos.close();

    }
}
