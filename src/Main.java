import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

class MenuItem {
    String menuName;
    double menuPrice;

    int total;
    MenuItem(String menuName, double menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalPerson;
        String orderBy;
        double  nasiGorengPrice = 9999.99,
                ayamBakarPrice = 12345.67,
                steakPrice = 21108.40,
                kweatiawPrice = 13579.13,
                kambingGulingPrice = 98765.43;

        List<MenuItem> menuList = new ArrayList<MenuItem>();
        NumberFormat formatterDecimal = new DecimalFormat("#0.00");

        menuList.add(new MenuItem("1. Nasi Goreng Spesial", nasiGorengPrice));
        menuList.add(new MenuItem("2. Ayam Bakar Spesial", ayamBakarPrice));
        menuList.add(new MenuItem("3. Steak Sirloin Spesial", steakPrice));
        menuList.add(new MenuItem("4. Kwetiaw Siram Spesial", kweatiawPrice));
        menuList.add(new MenuItem("5. Kambing Guling Spesial", kambingGulingPrice));


        System.out.println("Selamat siang...");
        System.out.print("Pesan untuk berapa orang : ");
        totalPerson = Integer.parseInt(scanner.nextLine());
        System.out.print("Pesan atas nama \t\t : ");
        orderBy = scanner.nextLine();

        System.out.println("Menu Spesial hari ini");
        System.out.println("=====================");

        int idx1 = 0;
        for(MenuItem menuItem : menuList) {
            printMenu(menuItem, idx1);
            idx1++;
        }

        System.out.println("\n");
        System.out.println("Pesanan Anda (Batas pesanan 0-10 porsi)");
        int idx2 = 0;
        for(MenuItem menuItem: menuList) {
            printGetOrderTotal(menuItem, idx2, scanner);
            idx2++;
        }

        System.out.println("\n");
        System.out.println("Selamat menikmati makanan Anda...");
        System.out.println("\n");

        System.out.println("Pembelian...");

        int idx3 = 0;
        for(MenuItem menuItem : menuList) {
            printInvoice(menuItem, idx3, totalPerson);
            idx3++;
        }
        System.out.println("===============================================================================");
        System.out.println("Total Pembelian  \t \t \t \t \t \t \t \t \t \t \t " + " = " + "Rp. " + formatterDecimal.format(calculateTotalBill(menuList)));
        System.out.println("Disc. 10 % (Masa Promosi)  \t \t \t \t \t \t \t \t \t " + " = " + "Rp. " + formatterDecimal.format(calculateTotalDiscount(menuList)) + " -");
        System.out.println("===============================================================================");
        System.out.println("Total Pembelian setelah disc 10% \t \t \t \t \t \t \t " + " = " + "Rp. " + formatterDecimal.format(calculateTotalBillAfterDiscount(menuList)));
        System.out.println("Pembelian Per orang (untuk " + totalPerson + " orang) \t \t \t \t \t \t" + "  = " + "Rp. " + formatterDecimal.format(calculateDivideByPerson(menuList, totalPerson)));

        System.out.println("\n");
        System.out.println("Terima kasih atas kunjugan Anda...");
        System.out.println("...tekan ENTER untuk keluar...");

        try {
            int enter = System.in.read();
        } catch (IOException ex) {
        }
    }

    // METHOD FOR CALCULATING, FORMATTING INVOICE AND FORMATTING MENU
    private static double calculateTotalDiscount(List<MenuItem> menuList) {

        double totalBill = calculateTotalBill(menuList);
        double totalDiscount = totalBill * 10 / 100;

        return totalDiscount;
    }

    private static double calculateTotalBill(List<MenuItem> menuList) {

        double total = 0;
        for(MenuItem menuItem : menuList) {
            total += menuItem.menuPrice * menuItem.getTotal();
        }

        return total;
    }

    private static double calculateTotalBillAfterDiscount(List<MenuItem> menuList) {
        double totalBill = calculateTotalBill(menuList);
        double totalDiscount = calculateTotalDiscount(menuList);

        return totalBill - totalDiscount;
    }

    private static double calculateDivideByPerson(List<MenuItem> menuList, int totalPerson) {
        double totalBillAfterDiscount = calculateTotalBillAfterDiscount(menuList);

        return totalBillAfterDiscount / totalPerson;
    }

    private static void printGetOrderTotal(MenuItem menuItem, int index, Scanner scanner) {
        if(index <= 1) {
            System.out.print(menuItem.menuName + "\t \t \t" + " = ");
            menuItem.setTotal(Integer.parseInt(scanner.nextLine()));
        } else {
            System.out.print(menuItem.menuName + "\t \t" + " = ");
            menuItem.setTotal(Integer.parseInt(scanner.nextLine()));
        }

    }
    private static void printInvoice(MenuItem menu, int index, int totalPerson) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        if(index <= 1) {
            System.out.println(menu.menuName + "\t \t \t \t" + menu.getTotal() + " porsi" + " * " + "Rp. " + menu.menuPrice + "\t  = Rp. " + formatter.format(menu.getTotal() * menu.menuPrice) );
        } else if (index == 4) {
            System.out.println(menu.menuName + "\t \t \t" + menu.getTotal() + " porsi" + " * " + "Rp. " + menu.menuPrice + "\t  = Rp. " + formatter.format(menu.getTotal() * menu.menuPrice) + " +");
        } else {
            System.out.println(menu.menuName + "\t \t \t" + menu.getTotal() + " porsi" + " * " + "Rp. " + menu.menuPrice + "\t  = Rp. " + formatter.format(menu.getTotal() * menu.menuPrice) );
        }
    }
    private static void printMenu (MenuItem item, int index) {
        if(index <= 1) {
            System.out.println("\t" + item.menuName +  "\t\t\t\t\t\t" + "@Rp. " + item.menuPrice);
        } else {
            System.out.println("\t" + item.menuName +  "\t\t\t\t\t" + "@Rp. " + item.menuPrice);
        }
    }
}