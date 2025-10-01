import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frontend extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public frontend() {
        // Frame settings
        setTitle("🎬 Movie Ticket Booking System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Modern Look
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // CardLayout for switching screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add all screens
        mainPanel.add(movieListUI(), "MovieList");
        mainPanel.add(theaterSelectionUI(), "TheaterSelection");
        mainPanel.add(seatSelectionUI(), "SeatSelection");
        mainPanel.add(bookingFormUI(), "BookingForm");
        mainPanel.add(confirmationUI(), "Confirmation");

        add(mainPanel);
        setVisible(true);

        // Show Movie List first
        cardLayout.show(mainPanel, "MovieList");
    }

    // ------------------ UI Screens ------------------

    private JPanel movieListUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);

        JLabel label = new JLabel("Available Movies", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setForeground(Color.WHITE);

        // Dummy movie list
        String[] columns = {"Movie Name", "Genre", "Duration"};
        String[][] data = {
                {"Inception", "Sci-Fi", "148 min"},
                {"The Dark Knight", "Action", "152 min"},
                {"Interstellar", "Sci-Fi", "169 min"}
        };
        JTable table = new JTable(data, columns);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton nextBtn = new JButton("Select Theater ➡");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 16));
        nextBtn.addActionListener(e -> cardLayout.show(mainPanel, "TheaterSelection"));

        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(nextBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel theaterSelectionUI() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBackground(Color.GRAY);

        JLabel label = new JLabel("Select a Theater", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JButton theater1 = new JButton("PVR Cinemas");
        JButton theater2 = new JButton("INOX");
        JButton nextBtn = new JButton("Continue to Seat Selection ➡");

        nextBtn.addActionListener(e -> cardLayout.show(mainPanel, "SeatSelection"));

        panel.add(label);
        panel.add(theater1);
        panel.add(theater2);
        panel.add(nextBtn);

        return panel;
    }

    private JPanel seatSelectionUI() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Select Your Seats", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel seatGrid = new JPanel(new GridLayout(5, 10, 5, 5)); // 5 rows, 10 columns

        for (int i = 1; i <= 50; i++) {
            JButton seat = new JButton("S" + i);
            seat.setBackground(Color.GREEN);
            seat.setOpaque(true);

            seat.addActionListener(e -> {
                if (seat.getBackground() == Color.GREEN) {
                    seat.setBackground(Color.BLUE); // Selected
                } else if (seat.getBackground() == Color.BLUE) {
                    seat.setBackground(Color.GREEN); // Unselect
                }
            });

            seatGrid.add(seat);
        }

        JButton nextBtn = new JButton("Proceed to Booking Form ➡");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 16));
        nextBtn.addActionListener(e -> cardLayout.show(mainPanel, "BookingForm"));

        panel.add(label, BorderLayout.NORTH);
        panel.add(seatGrid, BorderLayout.CENTER);
        panel.add(nextBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel bookingFormUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        JLabel nameLabel = new JLabel("Your Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();

        JButton confirmBtn = new JButton("Confirm Booking ✅");
        confirmBtn.addActionListener(e -> cardLayout.show(mainPanel, "Confirmation"));

        panel.add(nameLabel); panel.add(nameField);
        panel.add(emailLabel); panel.add(emailField);
        panel.add(phoneLabel); panel.add(phoneField);
        panel.add(new JLabel("")); panel.add(confirmBtn);

        return panel;
    }

    private JPanel confirmationUI() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("🎉 Booking Confirmed!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.BLUE);

        JButton backBtn = new JButton("Back to Movies ⬅");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "MovieList"));

        panel.add(label, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ------------------ MAIN ------------------
    public static void main(String[] args) {
        new frontend();
    }
}
