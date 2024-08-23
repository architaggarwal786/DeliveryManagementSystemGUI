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
                String input = JOptionPane.showInputDialog("Enter graph data for Dijkstra's algorithm (e.g., A-B:1,B-C:2):");
                if (input != null) {
                    String result = simulateDijkstraAlgorithm(input, "A"); // Assuming "A" as the source node
                    JOptionPane.showMessageDialog(null, "Dijkstra's algorithm result: " + result);
                }
            }
        });

        JButton processDataButton = new JButton("Process Data");
        processDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate sorting algorithm for data processing
                String input = JOptionPane.showInputDialog("Enter numbers to sort (e.g., 3,1,2,5,4):");
                if (input != null) {
                    String result = simulateSortingAlgorithm(input);
                    JOptionPane.showMessageDialog(null, "Sorting algorithm result: " + result);
                }
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

    // Simulate Dijkstra's algorithm
    private String simulateDijkstraAlgorithm(String input, String source) {
        // Parse the input to build the graph
        Map<String,ArrayList<Edge>> graph = new HashMap<>();
        String[] edges = input.split(",");
        for (String edge : edges) {
            String[] parts = edge.split(":");
            String[] nodes = parts[0].split("-");
            String from = nodes[0];
            String to = nodes[1];
            int weight = Integer.parseInt(parts[1]);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight)); // Assuming undirected graph
        }

        // Perform Dijkstra's algorithm
        Map<String, Integer> distances = dijkstra(graph, source);

        // Format the result
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            result.append(source).append(" to ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return result.toString();
    }

    // Dijkstra's algorithm implementation
    private Map<String, Integer> dijkstra(Map<String, ArrayList<Edge>> graph, String source) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        for (String node : graph.keySet()) {
            if (node.equals(source)) {
                distances.put(node, 0);
                priorityQueue.add(new Node(node, 0));
            } else {
                distances.put(node, Integer.MAX_VALUE);
                priorityQueue.add(new Node(node, Integer.MAX_VALUE));
            }
        }

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            for (Edge edge : graph.get(current.name)) {
                int newDist = distances.get(current.name) + edge.weight;
                if (newDist < distances.get(edge.to)) {
                    distances.put(edge.to, newDist);
                    priorityQueue.add(new Node(edge.to, newDist));
                }
            }
        }

        return distances;
    }

    // Edge class to represent a connection between nodes
    private static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Node class to represent nodes in the priority queue
    private static class Node {
        String name;
        int distance;

        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    // Simulate a sorting algorithm
    private String simulateSortingAlgorithm(String input) {
        // Parse the input and run a simple sorting algorithm simulation
        String[] parts = input.split(",");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i].trim());
        }
        Arrays.sort(numbers);
        return Arrays.toString(numbers);
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
