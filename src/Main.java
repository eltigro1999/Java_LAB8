/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.*;

public class Main {

    public static void WebpageSourceCode(String webAddress) throws MalformedURLException {


        URL url = new URL(webAddress);

        try (var br = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String line;

            var sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        */
/*String webAddress="https://orioks.miet.ru/";
        WebpageSourceCode(webAddress);*//*

        String webAddress="https://vk.com/";
        WebpageSourceCode(webAddress);
    }
}*/

import javax.mail.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.*;
import javax.swing.*;


public class Main {
    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel(new GridLayout(13, 2));
    public static JTextField fromField = new JTextField(20);
    public static JTextField toField = new JTextField(20);
    public static JTextField subjectField = new JTextField(20);
    public static JTextField messageField = new JTextField(20);
    public static JPasswordField passwordField = new JPasswordField(20);
    public static JLabel fromLabel = new JLabel("From:");
    public static JLabel passwordLabel = new JLabel("Password:");
    public static JLabel toLabel = new JLabel("To:");
    public static JLabel subjectLabel = new JLabel("Subject:");
    public static JLabel textLabel = new JLabel("Text:");
    public static JButton sendButton = new JButton("Send");

    public static void main(String[] args) throws MessagingException {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        panel.setBackground(Color.GRAY);
        panel.add(fromLabel);
        panel.add((fromField));
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(toLabel);
        panel.add(toField);
        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(textLabel);
        panel.add(messageField);
        panel.add(sendButton);
        frame.add(panel);
        frame.setVisible(true);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String from = fromField.getText();

                String password= new String(passwordField.getPassword());
                
                // Sender's email ID needs to be mentioned
                String to = toField.getText();

                // Assuming you are sending email from through gmails smtp
                String host = "smtp.gmail.com";


                // Get system properties
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

                // Get the Session object.// and pass
                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
                Transport transport = null;
                try {
                    transport = session.getTransport();
                } catch (NoSuchProviderException noSuchProviderException) {
                    noSuchProviderException.printStackTrace();
                }
                Message message = new MimeMessage(session);
                try {
                    message.setFrom(new InternetAddress(from));
                } catch (MessagingException messagingException) {
                    messagingException.printStackTrace();
                }
                try {
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                } catch (MessagingException messagingException) {
                    messagingException.printStackTrace();
                }
                try {
                    message.setSubject(subjectField.getText());
                } catch (MessagingException messagingException) {
                    messagingException.printStackTrace();
                }
                try {
                    message.setText(messageField.getText());
                } catch (MessagingException messagingException) {
                    messagingException.printStackTrace();
                }
                try {
                    transport.connect();
                } catch (MessagingException messagingException) {
                    messagingException.printStackTrace();
                }
                try {
                    transport.send(message);
                    System.out.println("Message's been sent successfully");
                } catch (MessagingException messagingException) {
                    System.out.println("Messaging has been failed");
                    messagingException.printStackTrace();
                }

            }
        });


        // Recipient's email ID needs to be mentioned.


    }

}