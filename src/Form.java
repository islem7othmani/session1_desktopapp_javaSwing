import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

class Form extends JFrame implements ActionListener {

  private JTextField username;
  private JTextField email;
  private JTextField phone;
  private JComboBox<Integer> dayComboBox;
  private JComboBox<String> monthComboBox;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> educationComboBox;
  private JRadioButton maleRadioButton;
  private JRadioButton femaleRadioButton;
  private JComboBox<String> javaLevelComboBox;
  private JComboBox<String> pythonLevelComboBox;
  private JComboBox<String> machineLearningLevelComboBox;
  private JTextArea commentTextArea;

  JButton valider = new JButton("Envoyer");
  JButton quitter = new JButton("Annuler");
  JCheckBox checkBox = new JCheckBox("Enregistrer une version PDF de la candidature avant de l'envoyer");

  // Constructor
  public Form() {
    setTitle("Registration Form");
    setBounds(300, 90, 1200, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(true);

    // Create a JPanel to contain all components
    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbcMain = new GridBagConstraints();
    gbcMain.insets = new Insets(5, 5, 5, 5); // Padding
    gbcMain.anchor = GridBagConstraints.NORTH;

    // Create a JPanel to contain the form components
    JPanel formPanel = new JPanel(new BorderLayout());
    Border border = BorderFactory.createLineBorder(Color.BLUE);
    formPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

    // Create a JPanel to contain the image label
    JPanel imagePanel = new JPanel(new BorderLayout());
    imagePanel.setOpaque(false); // Make the panel transparent

    ImageIcon icon = new ImageIcon("src/images/addpic.png"); // Replace "src/images/addpic.png" with your image file path
    Image image = icon.getImage();
    Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);

    JLabel imageLabel = new JLabel(scaledIcon);
    imagePanel.add(imageLabel, BorderLayout.NORTH);

    // Add the image panel to the top right corner of the form panel
    formPanel.add(imagePanel, BorderLayout.NORTH);

    // Create a JPanel to contain the form fields
    JPanel fieldsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbcFields = new GridBagConstraints();
    gbcFields.insets = new Insets(5, 5, 5, 5); // Padding
    gbcFields.anchor = GridBagConstraints.WEST;

    JLabel lblusername = new JLabel("Nom & Prénom:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 0;
    fieldsPanel.add(lblusername, gbcFields);

    username = new JTextField(20);
    gbcFields.gridx = 1;
    gbcFields.gridy = 0;
    fieldsPanel.add(username, gbcFields);

    JLabel lblemail = new JLabel("Email:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 1;
    fieldsPanel.add(lblemail, gbcFields);

    email = new JTextField(20);
    gbcFields.gridx = 1;
    gbcFields.gridy = 1;
    fieldsPanel.add(email, gbcFields);

    JLabel lblphone = new JLabel("Phone:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 2;
    fieldsPanel.add(lblphone, gbcFields);

    phone = new JTextField(20);
    gbcFields.gridx = 1;
    gbcFields.gridy = 2;
    fieldsPanel.add(phone, gbcFields);

    // Birth Date
    JLabel lblBirthDate = new JLabel("Date de naissance:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 3;
    fieldsPanel.add(lblBirthDate, gbcFields);

    // Determine the total width of the text input fields
    int textFieldWidth = username.getPreferredSize().width;

    // Determine the width of each JComboBox
    int comboBoxWidth = textFieldWidth / 3;

    // Day ComboBox
    Integer[] days = new Integer[31];
    for (int i = 0; i < 31; i++) {
      days[i] = i + 1;
    }
    dayComboBox = new JComboBox<>(days);
    dayComboBox.setPreferredSize(new Dimension(comboBoxWidth, dayComboBox.getPreferredSize().height)); // Set width
    gbcFields.gridx = 1;
    gbcFields.gridy = 3;
    gbcFields.anchor = GridBagConstraints.WEST;
    fieldsPanel.add(dayComboBox, gbcFields);

    // Month ComboBox
    String[] months = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
    monthComboBox = new JComboBox<>(months);
    monthComboBox.setPreferredSize(new Dimension(comboBoxWidth, monthComboBox.getPreferredSize().height)); // Set width
    gbcFields.gridx = 2;
    gbcFields.gridy = 3;
    gbcFields.anchor = GridBagConstraints.WEST;
    fieldsPanel.add(monthComboBox, gbcFields);

    // Year ComboBox
    Integer[] years = new Integer[100];
    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    for (int i = 0; i < 100; i++) {
      years[i] = currentYear - i;
    }
    yearComboBox = new JComboBox<>(years);
    yearComboBox.setPreferredSize(new Dimension(comboBoxWidth, yearComboBox.getPreferredSize().height)); // Set width
    gbcFields.gridx = 3;
    gbcFields.gridy = 3;
    gbcFields.anchor = GridBagConstraints.WEST;
    fieldsPanel.add(yearComboBox, gbcFields);

    // Gender Radio Buttons
    JLabel lblGender = new JLabel("Sexe:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 4;
    fieldsPanel.add(lblGender, gbcFields);

    maleRadioButton = new JRadioButton("Male");
    femaleRadioButton = new JRadioButton("Female");

    ButtonGroup genderButtonGroup = new ButtonGroup();
    genderButtonGroup.add(maleRadioButton);
    genderButtonGroup.add(femaleRadioButton);

    JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    genderPanel.add(maleRadioButton);
    genderPanel.add(new JLabel("Male"));
    genderPanel.add(femaleRadioButton);
    genderPanel.add(new JLabel("Female"));

    gbcFields.gridx = 1;
    gbcFields.gridy = 4;
    fieldsPanel.add(genderPanel, gbcFields);

    // Education
    JLabel lblEducation = new JLabel("Formation:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 5;
    fieldsPanel.add(lblEducation, gbcFields);

    String[] educationOptions = {"Bac", "Licence", "Master", "Doctorat"};
    educationComboBox = new JComboBox<>(educationOptions);
    educationComboBox.setPreferredSize(new Dimension(textFieldWidth, educationComboBox.getPreferredSize().height)); // Set width
    gbcFields.gridx = 1;
    gbcFields.gridy = 5;
    gbcFields.gridwidth = 3; // Span multiple columns for the ComboBox
    fieldsPanel.add(educationComboBox, gbcFields);

    // Java Level
    JLabel lblJavaLevel = new JLabel("Niveau en Java:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 6;
    gbcFields.gridwidth = 1; // Reset grid width
    fieldsPanel.add(lblJavaLevel, gbcFields);

    String[] javaLevelOptions = {"Débutant", "Intermédiaire", "Avancé"};
    javaLevelComboBox = new JComboBox<>(javaLevelOptions);
    javaLevelComboBox.setPreferredSize(new Dimension(comboBoxWidth, javaLevelComboBox.getPreferredSize().height)); // Set width
    gbcFields.gridx = 1;
    gbcFields.gridy = 6;
    fieldsPanel.add(javaLevelComboBox, gbcFields);

    // Python Level
    JLabel lblPythonLevel = new JLabel("Niveau en Python:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 7;
    fieldsPanel.add(lblPythonLevel, gbcFields);

    String[] pythonLevelOptions = {"Débutant", "Intermédiaire", "Avancé"};
    pythonLevelComboBox = new JComboBox<>(pythonLevelOptions);
    pythonLevelComboBox.setPreferredSize(new Dimension(comboBoxWidth, pythonLevelComboBox.getPreferredSize().height)); // Set width
    gbcFields.gridx = 1;
    gbcFields.gridy = 7;
    fieldsPanel.add(pythonLevelComboBox, gbcFields);

    // Machine Learning Level
    JLabel lblMLLevel = new JLabel("Niveau en Machine Learning:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 8;
    fieldsPanel.add(lblMLLevel, gbcFields);

    String[] mlLevelOptions = {"Débutant", "Intermédiaire", "Avancé"};
    machineLearningLevelComboBox = new JComboBox<>(mlLevelOptions);
    machineLearningLevelComboBox.setPreferredSize(new Dimension(comboBoxWidth, machineLearningLevelComboBox.getPreferredSize().height)); // Set width
    gbcFields.gridx = 1;
    gbcFields.gridy = 8;
    fieldsPanel.add(machineLearningLevelComboBox, gbcFields);

    // Comment Field
    JLabel lblComment = new JLabel("Commentaire:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 9;
    fieldsPanel.add(lblComment, gbcFields);

    commentTextArea = new JTextArea(5, 20);
    JScrollPane scrollPane = new JScrollPane(commentTextArea);
    gbcFields.gridx = 1;
    gbcFields.gridy = 9;
    gbcFields.gridwidth = 3; // Span multiple columns for the comment field
    fieldsPanel.add(scrollPane, gbcFields);

    // Add the fields panel to the center of the form panel
    formPanel.add(fieldsPanel, BorderLayout.CENTER);

    // Add the form panel to the main panel
    gbcMain.gridx = 0;
    gbcMain.gridy = 0;
    mainPanel.add(formPanel, gbcMain);

    checkBox.setHorizontalAlignment(JCheckBox.CENTER);
    checkBox.setVerticalAlignment(JCheckBox.CENTER);

    // Add action listeners to the buttons
    valider.addActionListener(this);
    quitter.addActionListener(this);

    // Add the main panel to the content pane
    getContentPane().add(mainPanel, BorderLayout.NORTH);
    getContentPane().add(checkBox, BorderLayout.CENTER);

    JPanel psouth = new JPanel();
    psouth.setLayout(new FlowLayout());
    psouth.add(valider);
    psouth.add(quitter);
    this.add(psouth, BorderLayout.SOUTH);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == valider) {
      // Handle "Envoyer" button click
      // Build HTML page with the form data
      StringBuilder htmlContent = new StringBuilder();
      htmlContent.append("<html><head><title>Form Data</title></head><body>");
      htmlContent.append("<h1>Form Data</h1>");
      htmlContent.append("<p><strong>Nom & Prénom:</strong> ").append(username.getText()).append("</p>");
      htmlContent.append("<p><strong>Email:</strong> ").append(email.getText()).append("</p>");
      htmlContent.append("<p><strong>Phone:</strong> ").append(phone.getText()).append("</p>");
      htmlContent.append("<p><strong>Date de naissance:</strong> ")
              .append(dayComboBox.getSelectedItem()).append(" ")
              .append(monthComboBox.getSelectedItem()).append(" ")
              .append(yearComboBox.getSelectedItem()).append("</p>");
      htmlContent.append("<p><strong>Sexe:</strong> ");
      htmlContent.append(maleRadioButton.isSelected() ? "Male" : "Female");
      htmlContent.append("</p>");
      htmlContent.append("<p><strong>Formation:</strong> ").append(educationComboBox.getSelectedItem()).append("</p>");
      htmlContent.append("<p><strong>Niveau en Java:</strong> ").append(javaLevelComboBox.getSelectedItem()).append("</p>");
      htmlContent.append("<p><strong>Niveau en Python:</strong> ").append(pythonLevelComboBox.getSelectedItem()).append("</p>");
      htmlContent.append("<p><strong>Niveau en Machine Learning:</strong> ").append(machineLearningLevelComboBox.getSelectedItem()).append("</p>");
      htmlContent.append("<p><strong>Commentaire:</strong> ").append(commentTextArea.getText()).append("</p>");
      htmlContent.append("</body></html>");

      // Write HTML content to a file
      try {
        FileWriter writer = new FileWriter("formData.html");
        writer.write(htmlContent.toString());
        writer.close();
        Desktop.getDesktop().browse(new File("formData.html").toURI());
        JOptionPane.showMessageDialog(this, "Formulaire envoyé avec succès. Veuillez consulter formData.html.");
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else if (e.getSource() == quitter) {
      // Handle "Annuler" button click
      System.exit(0);
    }
  }

}
