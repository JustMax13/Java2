import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyForm extends JFrame{
    private JPanel mainPanel;
    private JLabel firstCity;
    private JTextField firstLatitude;
    private JTextField firstLongitude;
    private JLabel secondCity;
    private JTextField secondLatitude;
    private JTextField secondLongitude;
    private JLabel resultDictanceText;
    private JButton buttonClear;
    private JButton buttonSolve;
    private JPanel xPoint;
    private JPanel yPoint;
    private JLabel resultValue;

    private double earthRadius = 6371e3;

    public static void main(String[] args) {
        MyForm myForm = new MyForm();
    }
    public MyForm(){
        Frame frame = new Frame();
        frame.setTitle("dictanceCounter");
        frame.setSize(600,200);

        firstLatitude.setText("");
        secondLatitude.setText("");
        firstLongitude.setText("");
        secondLongitude.setText("");

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        }
        );
        buttonSolve.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              solve();
                                          }
                                      }
        );

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void clear(){
        firstLatitude.setText("");
        secondLatitude.setText("");
        firstLongitude.setText("");
        secondLongitude.setText("");
    }
    public void solve(){
        double lat1 = 0;
        double lat2 = 0;
        double lon1 = 0;
        double lon2 = 0;
        try {
            lat1 = Double.parseDouble(firstLatitude.getText());
            lat2 = Double.parseDouble(secondLatitude.getText());
            lon1 = Double.parseDouble(firstLongitude.getText());
            lon2 = Double.parseDouble(secondLongitude.getText());
        }
        catch (Exception e){
            resultValue.setText("Помилка, не вірно введені дані");
            return;
        }


        double fi_1 = lat1 * ( Math.PI / 180 );
        double fi_2 = lat2 * ( Math.PI / 180 );

        double deltaFi = (lat2 - lat1) * ( Math.PI / 180 );
        double deltaLambda = (lon2 - lon1) * ( Math.PI / 180 );

        double a = Math.pow(Math.sin(deltaFi / 2), 2) + Math.cos(fi_1) * Math.cos(fi_2)
                * Math.pow(Math.sin(deltaLambda / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double D = earthRadius * c;
        double distanceInKm = (D / 1000);
        distanceInKm = Math.round(distanceInKm * 10000) / 10000.0;

        resultValue.setText(String.valueOf(distanceInKm) + " км");
    }

}