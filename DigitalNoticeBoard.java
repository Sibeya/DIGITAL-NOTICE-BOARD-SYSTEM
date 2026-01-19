import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class DigitalNoticeBoard {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Digital Notice Board System");
        frame.setSize(800, 600); // Increased size for better view
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Create a text area to display notices
        JTextArea noticeBoard = new JTextArea();
        noticeBoard.setEditable(false);
        noticeBoard.setLineWrap(true);
        noticeBoard.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(noticeBoard);
        scrollPane.setBounds(220, 20, frame.getWidth() - 240, frame.getHeight() - 60); // Adjusted dynamically
        frame.add(scrollPane);

        // Set button positions dynamically based on window size
        int buttonWidth = frame.getWidth() / 4; // Adjusting button width
        int buttonHeight = 40;
        int margin = 20;

        // Create buttons
        JButton viewButton = new JButton("View Notices");
        JButton editButton = new JButton("Edit Notice");
        JButton deleteButton = new JButton("Delete Notice");
        JButton settingsButton = new JButton("Settings");
        JButton searchButton = new JButton("Search Notices");
        JButton logoutButton = new JButton("Logout/Exit");

        // Set button colors
        Color buttonColor = new Color(0, 123, 255); // Blue color for buttons
        viewButton.setBackground(buttonColor);
        editButton.setBackground(buttonColor);
        deleteButton.setBackground(buttonColor);
        settingsButton.setBackground(buttonColor);
        searchButton.setBackground(buttonColor);
        logoutButton.setBackground(buttonColor);

        // Set button text color
        viewButton.setForeground(Color.WHITE);
        editButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        settingsButton.setForeground(Color.WHITE);
        searchButton.setForeground(Color.WHITE);
        logoutButton.setForeground(Color.WHITE);

        // Set button positions
        viewButton.setBounds(margin, margin, buttonWidth, buttonHeight);
        editButton.setBounds(margin, margin + buttonHeight + margin, buttonWidth, buttonHeight);
        deleteButton.setBounds(margin, margin + 2 * (buttonHeight + margin), buttonWidth, buttonHeight);
        settingsButton.setBounds(margin, margin + 3 * (buttonHeight + margin), buttonWidth, buttonHeight);
        searchButton.setBounds(margin, margin + 4 * (buttonHeight + margin), buttonWidth, buttonHeight);
        logoutButton.setBounds(margin, margin + 5 * (buttonHeight + margin), buttonWidth, buttonHeight);

        // Add buttons to the frame
        frame.add(viewButton);
        frame.add(editButton);
        frame.add(deleteButton);
        frame.add(settingsButton);
        frame.add(searchButton);
        frame.add(logoutButton);

        // Add some default notices
        String[] defaultNotices = {
            "EXAM DETAILS: ",
            "Batch: 2023-2027",
            "Academic Year: 2024-2025",
            "Department: Computer Science and Engineering",
            "Semester: 3rd Sem",
            "Starting Date: 10.12.2024",
            "Ending Date: 24.12.2024  \n",
            "COURSES: ",
            "Java Programming",
            "Data Science",
            "Computer Architecture and Organization",
            "Design and Analysis of Algorithms",
            "Engineering Mathematics",
            "Digital Principles and System Designs",
        };
        for (String notice : defaultNotices) {
            String[] words = notice.split(" ", 2); // Split the string into two parts: first word and the rest
            System.out.println(words[0]); // Print the first word
            if (words.length > 1) {
                System.out.println(words[1]); // Print the remaining part
            }
        }
        // Add button action listeners
        viewButton.addActionListener(e -> {
            // Display some default notices if there are no notices to show
            if (noticeBoard.getText().isEmpty()) {
                noticeBoard.setText(""); // Clear existing content
                for (String notice : defaultNotices) {
                    noticeBoard.append(notice + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Notices:\n" + noticeBoard.getText());
            }
        });

        editButton.addActionListener(e -> {
            String[] notices = noticeBoard.getText().split("\n");
            if (notices.length == 0 || noticeBoard.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No notices to edit.");
                return;
            }
            String noticeToEdit = (String) JOptionPane.showInputDialog(frame,
                    "Select a notice to edit:",
                    "Edit Notice",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    notices,
                    notices[0]);
            if (noticeToEdit != null) {
                String updatedNotice = JOptionPane.showInputDialog(frame, "Edit the notice:", noticeToEdit);
                if (updatedNotice != null && !updatedNotice.isEmpty()) {
                    noticeBoard.setText(noticeBoard.getText().replace(noticeToEdit, updatedNotice));
                }
            }
        });

        deleteButton.addActionListener(e -> {
            String[] notices = noticeBoard.getText().split("\n");
            if (notices.length == 0 || noticeBoard.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No notices to delete.");
                return;
            }
            String noticeToDelete = (String) JOptionPane.showInputDialog(frame,
                    "Select a notice to delete:",
                    "Delete Notice",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    notices,
                    notices[0]);
            if (noticeToDelete != null) {
                noticeBoard.setText(noticeBoard.getText().replace(noticeToDelete + "\n", ""));
            }
        });

        settingsButton.addActionListener(e -> {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setSize(300, 250);
            settingsFrame.setLayout(null);
            settingsFrame.getContentPane().setBackground(new Color(255, 255, 255));

            JLabel label = new JLabel("Choose Background Color:");
            label.setBounds(20, 20, 200, 30);
            settingsFrame.add(label);

            JButton changeColorButton = new JButton("Change Color");
            changeColorButton.setBounds(20, 60, 150, 30);
            settingsFrame.add(changeColorButton);

            // Add Notice Button
            JButton addNoticeButton = new JButton("Add Notice");
            addNoticeButton.setBounds(20, 100, 150, 30);
            settingsFrame.add(addNoticeButton);

            // Action for adding a new notice
            addNoticeButton.addActionListener(addNoticeEvent -> {
                String newNotice = JOptionPane.showInputDialog(settingsFrame, "Enter the new notice:");
                if (newNotice != null && !newNotice.isEmpty()) {
                    noticeBoard.append(newNotice + "\n");
                }
            });

            changeColorButton.addActionListener(colorEvent -> {
                Color color = JColorChooser.showDialog(settingsFrame, "Select a Color", Color.WHITE);
                if (color != null) {
                    noticeBoard.setBackground(color);
                }
            });

            settingsFrame.setVisible(true);
        });

        searchButton.addActionListener(e -> {
            String searchQuery = JOptionPane.showInputDialog(frame, "Enter search keyword:");
            if (searchQuery != null && !searchQuery.isEmpty()) {
                String[] notices = noticeBoard.getText().split("\n");
                StringBuilder results = new StringBuilder();
                for (String notice : notices) {
                    if (notice.toLowerCase().contains(searchQuery.toLowerCase())) {
                        results.append(notice).append("\n");
                    }
                }
                if (results.length() > 0) {
                    JOptionPane.showMessageDialog(frame, "Search Results:\n" + results);
                } else {
                    JOptionPane.showMessageDialog(frame, "No matches found.");
                }
            }
        });

        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
