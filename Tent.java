package Project2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReservationTentDialog extends JDialog implements ActionListener {
    private JTextField txtGuestName;
    private JTextField txtDateCheckin;
    private JTextField txtDateCheckout;
    private JTextField txtNumberOfTenters;
    private JButton okButton;
    private JButton cancelButton;
    private int closeStatus;
    private Tent tent;
    public static final int OK = 0;
    public static final int CANCEL = 1;


    /*********************************************************
     Instantiate a Custom Dialog as 'modal' and wait for the
     user to provide data and click on a button.

     @param parent reference to the JFrame application
     @param tent an instantiated object to be filled with data
     *********************************************************/

    public ReservationTentDialog(JFrame parent, Tent tent) {
        // call parent and create a 'modal' dialog
        super(parent, true);
        this.tent = tent;

        setTitle("TentOnly dialog box");
        closeStatus = CANCEL;
        setSize(400,200);

        // prevent user from closing window
        //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // instantiate and display two text fields
        txtGuestName = new JTextField("Judy",30);
        txtDateCheckin = new JTextField(15);
        txtDateCheckout = new JTextField(15);
        txtNumberOfTenters = new JTextField("4", 15);

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); //format it as per your requirement
        formatter.setLenient(false);
        String dateNow = formatter.format(currentDate.getTime());
        currentDate.add(Calendar.DATE, 1);
        String datetomorrow = formatter.format(currentDate.getTime());

        txtDateCheckin.setText(dateNow);
        txtDateCheckout.setText(datetomorrow);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(5,2));

        textPanel.add(new JLabel(""));
        textPanel.add(new JLabel(""));

        textPanel.add(new JLabel("Name of Tenter: "));
        textPanel.add(txtGuestName);
        textPanel.add(new JLabel("Date on Check in: "));
        textPanel.add(txtDateCheckin);
        textPanel.add(new JLabel("Date on Check out (est.): "));
        textPanel.add(txtDateCheckout);
        textPanel.add(new JLabel("Number of Tenters"));
        textPanel.add(txtNumberOfTenters);

        getContentPane().add(textPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setVisible (true);
    }

    /**************************************************************
     Respond to either button clicks
     @param e the action event that was just fired
     **************************************************************/
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        // if OK clicked the fill the object
        if (button == okButton) {
            // save the information in the object
            closeStatus = OK;
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            df.setLenient(false);
            Date d1 = null;
            Date d2 = null;

            int count = 0;
            try {
                String dateIn = txtDateCheckin.getText();
                dateIn = dateIn.substring(dateIn.lastIndexOf('/') + 1);
                String dateOut = txtDateCheckout.getText();
                dateOut = dateOut.substring(dateOut.lastIndexOf('/') + 1);

                if (dateIn.length() != 4 || dateOut.length() != 4) {
                    JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
                    closeStatus = CANCEL;
                    count += 1;
                }
                else {
                    for (int i = 0; i < dateIn.length(); i++) {
                        if ((!Character.isDigit(dateIn.charAt(i)) || !Character.isDigit(dateOut.charAt(i))) && count < 1) {
                            JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
                            closeStatus = CANCEL;
                            count += 1;
                        }
                    }
                }

                GregorianCalendar gregTemp = new GregorianCalendar();
                d1 = df.parse(txtDateCheckin.getText());
                gregTemp.setTime(d1);
                tent.setCheckIn(gregTemp);

                gregTemp = new GregorianCalendar();
                d2 = df.parse(txtDateCheckout.getText());
                gregTemp.setTime(d2);
                tent.setEstimatedCheckOut(gregTemp);
                if (tent.getCheckIn().after(tent.getEstimatedCheckOut())) {
                    JOptionPane.showMessageDialog(getParent(), "Estimated Checkout cant be before Check in.");
                    closeStatus = CANCEL;
                }


            } catch (ParseException e1) {
                if (count < 1) {
                    JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
                    closeStatus = CANCEL;
                }
            }
            try {
                if (txtGuestName.getText().length() == 0){
                    JOptionPane.showMessageDialog(getParent(), "Invalid name!");
                    closeStatus = CANCEL;
                }
                String name = txtGuestName.getText();
                for (char c : name.toCharArray()) {
                    if ((!Character.isLetter(c) && c != ' ' && c != '.') && count < 1) {
                        JOptionPane.showMessageDialog(getParent(), "Invalid name!");
                        closeStatus = CANCEL;
                    }
                }
                if (closeStatus != CANCEL)
                    tent.setGuestName(txtGuestName.getText());

                tent.setNumberOfTenters(Integer.parseInt(txtNumberOfTenters.getText()));
                if (tent.getNumberOfTenters() <= 0 && count < 1) {
                    JOptionPane.showMessageDialog(getParent(), "Must have at least 1 Tenter");
                    closeStatus = CANCEL;
                }
            }
            catch (Exception e1) {
                if (count < 1) {
                    JOptionPane.showMessageDialog(getParent(), "Invalid number of tenters!");
                    closeStatus = CANCEL;
                }
            }
        }

        // make the dialog disappear
        dispose();
    }

    /**************************************************************
     Return a String to let the caller know which button
     was clicked

     @return an int representing the option OK or CANCEL
     **************************************************************/
    public int getCloseStatus(){
        return closeStatus;
    }
}

