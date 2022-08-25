import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame{
    private IViewToModel vtm;
    private JLabel percentage;
    private int partyNum;

    public View(IViewToModel vtm){
        this.vtm = vtm;
        //add code
        initGui();
    }

    public void start(){
        this.setVisible(true);
    }

    public void changeLabel(String str){
        this.percentage.setText(str);
    }

    public void initGui() {
        JTextField parties = new JTextField("parties: ");
        this.add(parties);
        parties.setPreferredSize(new Dimension(500, 70));
        parties.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                parties.setText("");
            }
        });


        JTextField iterations = new JTextField("iterations: ");
        this.add(iterations);
        iterations.setPreferredSize(new Dimension(500, 70));
        iterations.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                iterations.setText("");
            }
        });

        JTextField towns = new JTextField("towns: ");
        this.add(towns);
        towns.setPreferredSize(new Dimension(500, 70));
        towns.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                towns.setText("");
            }
        });

        JTextField districts = new JTextField("districts: ");
        this.add(districts);
        districts.setPreferredSize(new Dimension(500, 70));
        districts.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                districts.setText("");
            }
        });

        JTextField craziness = new JTextField("craziness: ");
        this.add(craziness);
        craziness.setPreferredSize(new Dimension(500, 70));
        craziness.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                craziness.setText("");
            }
        });

        JTextField maxSupporters = new JTextField("max supporters: ");
        this.add(maxSupporters);
        maxSupporters.setPreferredSize(new Dimension(500, 70));
        maxSupporters.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                maxSupporters.setText("");
            }
        });

        JTextField wantedMargin = new JTextField("wanted margin: ");
        this.add(wantedMargin);
        wantedMargin.setPreferredSize(new Dimension(500, 70));
        wantedMargin.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                wantedMargin.setText("");
            }
        });

        JButton button = new JButton("Start");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vtm.startGerryMander(Integer.parseInt(parties.getText()), Integer.parseInt(iterations.getText()), Integer.parseInt(towns.getText()), Integer.parseInt(districts.getText()), Integer.parseInt(craziness.getText()), Integer.parseInt(maxSupporters.getText()), Double.parseDouble(wantedMargin.getText()));
            }
        });
        this.add(button);

        this.percentage = new JLabel("Not Running");
        percentage.setPreferredSize(new Dimension(500, 150));
        percentage.setBounds(0, 350, 500, 150);
        percentage.setVerticalAlignment(JLabel.TOP);
        percentage.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(percentage);

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(500, 500);
    }
}
