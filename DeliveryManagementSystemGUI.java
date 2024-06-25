 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DeliveryManagementSystemGUI extends JFrame {
    
    public DeliveryManagementSystemGUI() {
        setTitle("Delivery Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window
        
        // Create components
        JLabel titleLabel = new JLabel("Delivery Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton optimizeRouteButton = new JButton("Optimize Route");
        optimizeRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate Dijkstra's algorithm for route optimization
                // Replace with actual algorithm implementation
                JOptionPane.showMessageDialog(null, "Running Dijkstra's algorithm for route optimization.");
            }
        });
        
        JButton processDataButton = new JButton("Process Data");
        processDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate sorting algorithm for data processing
                // Replace with actual algorithm implementation
                JOptionPane.showMessageDialog(null, "Running sorting algorithm for data processing.");
            }
        });
        
        // Layout using GridBagLayout for flexibility
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Padding
        
        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(optimizeRouteButton, constraints);
        
        constraints.gridx = 1;
        panel.add(processDataButton, constraints);
        
        // Add panel to the frame
        add(panel);
        
        // Display the window
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeliveryManagementSystemGUI();
            }
        });
    }
}
