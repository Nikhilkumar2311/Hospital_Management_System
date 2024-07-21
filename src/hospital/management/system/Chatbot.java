package hospital.management.system;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Chatbot extends JFrame implements ActionListener {

    JTextField symptomsField;
    JTextArea responseArea;
    JButton submitButton, backButton;

    public Chatbot() {
        setUndecorated(true);
        setTitle("Chatbot Interface");
        setSize(850, 550);
        setLocation(250, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Input panel for entering symptoms
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter Symptoms:");
        inputPanel.add(label);

        symptomsField = new JTextField(30);
        inputPanel.add(symptomsField);

        submitButton = new JButton("Get Medicine Suggestion");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        inputPanel.add(submitButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        inputPanel.add(backButton);

        // Area to display the response
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(responseArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String symptoms = symptomsField.getText();
            if (symptoms != null && !symptoms.isEmpty()) {
                getMedicineSuggestion(symptoms);
            } else {
                responseArea.setText("Please enter symptoms.");
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    private void getMedicineSuggestion(String symptoms) {
        String apiUrl = "https://healthgraphic-healthgraphic-v1.p.rapidapi.com/api.healthgraphic.com/v1/conditions/" + symptoms;
        String apiKey = "734d4aae89msh16ccbbf902bc45bp111f9bjsnbbb840907feb";
        String apiHost = "healthgraphic-healthgraphic-v1.p.rapidapi.com";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", apiHost)
                    .GET()
                    .build();

            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            response.thenApply(HttpResponse::body)
                    .thenApply(JSONObject::new)
                    .thenApply(json -> json.getString("medicine")) // Adjust according to actual API response
                    .thenAccept(medicine -> {
                        SwingUtilities.invokeLater(() -> responseArea.setText("Suggested Medicine: " + medicine));
                    })
                    .exceptionally(ex -> {
                        ex.printStackTrace();
                        SwingUtilities.invokeLater(() -> responseArea.setText("Error: Unable to get suggestion."));
                        return null;
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
            responseArea.setText("Error: Unable to get suggestion.");
        }
    }

    public static void main(String[] args) {
        new Chatbot();
    }
}
