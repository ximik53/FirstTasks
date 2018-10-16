package CalcGUI;

import java.awt.*;
import java.lang.String;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;

public class CalculGUI extends JFrame {
    private JLabel Headline = new JLabel(" Calculator 9000 ");
    private JButton Sum = new JButton("+");
    private JButton Ded = new JButton("-");
    private JButton Div = new JButton("/");
    private JButton Mul = new JButton("*");
    private JLabel Arg1 = new JLabel(" Первый аргумент: ");
    private JFormattedTextField Arg1Input = new JFormattedTextField();
    {
        NumberFormat.getIntegerInstance();
    }
    private JLabel Arg2 = new JLabel(" Второй аргумент: ");
    private JFormattedTextField Arg2Input = new JFormattedTextField();
    {
        NumberFormat.getIntegerInstance();
    }
    int Res;
    private JLabel Result =new JLabel("Ответ: "+Res);

    public CalculGUI() {
        super(" Calculator 9000 ");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 280);
        setLayout(null);
        Headline.setSize(150,35);
        Headline.setLocation(140,5);
        Headline.setFont(new Font("Arial", 3,17));
        Headline.setForeground(Color.DARK_GRAY);
        add(Headline);
        Arg1.setSize(200, 25);
        Arg1.setLocation(50,60);
        add(Arg1);
        Arg1Input.setSize(155,25);
        Arg1Input.setLocation(175, 60);
        add(Arg1Input);
        Arg2.setSize(200, 25);
        Arg2.setLocation(50,100);
        add(Arg2);
        Arg2Input.setSize(155,25);
        Arg2Input.setLocation(175, 100);
        add(Arg2Input);
        Sum.setSize(75,25);
        Sum.setLocation(30,150);
        Sum.addActionListener(e -> Result.setText("Ответ: "+Arg1Input.getText() + "+" + Arg2Input.getText() + "="+(Integer.parseInt(Arg1Input.getText())+Integer.parseInt(Arg2Input.getText()))));
        add(Sum);
        Ded.setSize(75,25);
        Ded.setLocation(110,150);
        Ded.addActionListener(e -> Result.setText("Ответ: "+Arg1Input.getText() + "-" + Arg2Input.getText() + "="+(Integer.parseInt(Arg1Input.getText())-Integer.parseInt(Arg2Input.getText()))));
        add(Ded);
        Div.setSize(75,25);
        Div.setLocation(190,150);
        Div.addActionListener(e -> Result.setText("Ответ: "+Arg1Input.getText() + "/" + Arg2Input.getText() + "="+(Integer.parseInt(Arg1Input.getText())/Integer.parseInt(Arg2Input.getText()))));
        add(Div);
        Mul.setSize(75,25);
        Mul.setLocation(270,150);
        Mul.addActionListener(e -> Result.setText("Ответ: "+Arg1Input.getText() + "*" + Arg2Input.getText() + "="+(Integer.parseInt(Arg1Input.getText())*Integer.parseInt(Arg2Input.getText()))));
        add(Mul);
        Result.setSize(150,35);
        Result.setLocation(140,190);
        Result.setFont(new Font("Arial", 3,17));
        Result.setForeground(Color.BLUE);
        add(Result);
    }

    public static void main(String[] args) {
        CalculGUI app = new CalculGUI();
        app.setVisible(true);
    }
}