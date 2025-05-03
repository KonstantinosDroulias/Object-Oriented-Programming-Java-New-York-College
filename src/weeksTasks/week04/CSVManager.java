package weeksTasks.week04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVManager {
    static double RevenueTotal = 0;

    public static void exportToCSV(String filePath, List<CustomerOrder> orders) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("CustomerID,CustomerName,CustomerEmail,OrderID,OrderDate,Amount\n");

            for (CustomerOrder co : orders) {
                writer.write(String.format("%d,%s,%s,%d,%s,%.2f\n",
                        co.getCustomer().getCustomerID(),
                        co.getCustomer().getName(),
                        co.getCustomer().getEmail(),
                        co.getOrder().getOrderID(),
                        co.getOrder().getDate(),
                        co.getOrder().getAmount()
                ));
                RevenueTotal += co.getOrder().getAmount();
            }
            writer.write(String.format(",,,,,%.2f\n", RevenueTotal));
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
