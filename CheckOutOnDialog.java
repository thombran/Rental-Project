package Project2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CheckOutOnDialog extends JDialog implements ActionListener {

	private JTextField txtDate;

	private JButton okButton;
	private JButton cancelButton;
	private int closeStatus;
	private CampSite campSite;
	private CampSite temp;

	static final int OK = 0;
	static final int CANCEL = 1;

	/*********************************************************
	 Instantiate a Custom Dialog as 'modal' and wait for the
	 user to provide data and click on a button.

	 @param parent reference to the JFrame application
	 @param campSite an instantiated object to be filled with data
	 *********************************************************/

	public CheckOutOnDialog(JFrame parent, CampSite campSite) {
		// call parent and create a 'modal' dialog
		super(parent, true);

		this.campSite = campSite;
		setTitle("Check Out dialog box");
		closeStatus = CANCEL;
		setSize(300,100);

		//setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
        txtDate = new JTextField(dateFormat.format(campSite.
				getEstimatedCheckOut().getTime()),30);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1,2));
		textPanel.add(new JLabel("Check out Date: "));
		textPanel.add(txtDate);

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
			GregorianCalendar gTemp = new GregorianCalendar();

			Date d = null;
			int count = 0;
			try {
				String dateIn = txtDate.getText();
				dateIn = dateIn.substring(dateIn.lastIndexOf('/') + 1);
				int yearIn = Integer.parseInt(dateIn);
				if (dateIn.length() != 4 ) {
					JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
					closeStatus = CANCEL;
					count += 1;
				}
				else {
					for (int i = 0; i < dateIn.length(); i++) {
						if (!Character.isDigit(dateIn.charAt(i)) && count < 1) {
							JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
							closeStatus = CANCEL;
							count += 1;
						}
					}
				}

				String dateOut = txtDate.getText();
				dateOut = dateOut.substring(dateOut.lastIndexOf('/') + 1);
				int yearOut = Integer.parseInt(dateOut);
				if (dateOut.length() != 4 && count < 1) {
					JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
					closeStatus = CANCEL;
					count += 1;
				}
				else {
					for (int i = 0; i < dateOut.length(); i++) {
						if (!Character.isDigit(dateOut.charAt(i)) && count < 1) {
							JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
							closeStatus = CANCEL;
							count += 1;
						}
					}
				}
				d = df.parse(txtDate.getText());
				gTemp.setTime(d);
				temp = campSite;
				temp.setActualCheckOut(gTemp);
				if (temp.actualCheckOut.before(campSite.checkIn) && count < 1)
					JOptionPane.showMessageDialog(getParent(), "Estimated Checkout cant be before Check in.");
					closeStatus = CANCEL;

			}catch (ParseException | NullPointerException | NumberFormatException e1) {
				if(count < 1) {
					JOptionPane.showMessageDialog(getParent(), "Incorrect Date!");
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

