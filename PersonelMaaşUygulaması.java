// JTable ve butonlar için gerekli import'lar
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonelMaaşUygulaması extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField salaryField;
    private JButton addButton, totalButton, personelTotalButton, changeButton, maxButton;
    private JPanel panel;

    public PersonelMaaşUygulaması() {
        // JFrame başlatma
        setTitle("Personel Maaş Uygulaması");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // JTable modelini ayarla
        String[] columnNames = {"Personel Adı", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};
        tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Butonlar ve TextField
        panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        
        addButton = new JButton("Ekle");
        totalButton = new JButton("Aylık Toplam");
        personelTotalButton = new JButton("Personel Toplam");
        changeButton = new JButton("Değiştir");
        maxButton = new JButton("En Büyük");

        salaryField = new JTextField(10);

        panel.add(addButton);
        panel.add(totalButton);
        panel.add(personelTotalButton);
        panel.add(changeButton);
        panel.add(maxButton);
        panel.add(salaryField);

        // Ekle butonunun işlevi
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] newRow = new String[13];
                newRow[0] = JOptionPane.showInputDialog("Personel Adını Giriniz:");
                for (int i = 1; i < 13; i++) {
                    newRow[i] = JOptionPane.showInputDialog("Ay " + i + " maaşını giriniz:");
                }
                tableModel.addRow(newRow);
            }
        });

        // Aylık toplam butonunun işlevi
        totalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double[] monthlyTotal = new double[12];
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 1; col < 13; col++) {
                        monthlyTotal[col - 1] += Double.parseDouble(tableModel.getValueAt(row, col).toString());
                    }
                }
                Object[] totalRow = new Object[13];
                totalRow[0] = "Aylık Toplam";
                for (int i = 0; i < 12; i++) {
                    totalRow[i + 1] = monthlyTotal[i];
                }
                tableModel.addRow(totalRow);
            }
        });

        // Personel toplam butonunun işlevi
        personelTotalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    double totalSalary = 0;
                    for (int col = 1; col < 13; col++) {
                        totalSalary += Double.parseDouble(tableModel.getValueAt(row, col).toString());
                    }
                    tableModel.setValueAt(totalSalary, row, 13); // Yeni sütun ekleyin.
                }
            }
        });

        // Değiştir butonunun işlevi
        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double newSalary = Double.parseDouble(salaryField.getText());
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 1; col < 13; col++) {
                        double currentSalary = Double.parseDouble(tableModel.getValueAt(row, col).toString());
                        if (currentSalary > 2000) {
                            tableModel.setValueAt(newSalary, row, col);
                        }
                    }
                }
            }
        });

        // En büyük maaş butonunun işlevi
        maxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double maxSalary = 0;
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 1; col < 13; col++) {
                        double currentSalary = Double.parseDouble(tableModel.getValueAt(row, col).toString());
                        if (currentSalary > maxSalary) {
                            maxSalary = currentSalary;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "En büyük maaş: " + maxSalary);
            }
        });

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new PersonelMaaşUygulaması();
    }
}
