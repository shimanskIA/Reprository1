package Package2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.io.IOException;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;
    private Double mem1;
    private Double mem2;
    private Double mem3;
    private int a = 1;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JLabel graphicField;
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioButtons1 = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxFormulaType1 = Box.createHorizontalBox();
    private int formulaId = 1;
    private void addRadioButton(String buttonName, final int formulaId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                MainFrame.this.formulaId = formulaId;
                if (formulaId == 1)
                {
                    graphicField.setIcon(new ImageIcon("Func1.jpg"));
                }
                else {
                    graphicField.setIcon(new ImageIcon("Func.jpg"));
                }
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    private void addRadioButton1(String buttonName, final int a)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                MainFrame.this.a = a;
            }
        });
        radioButtons1.add(button);
        hboxFormulaType1.add(button);
    }
    public Double calculate1(Double x, Double y, Double z)
    {
        return Math.sin(Math.log(y) + Math.sin(3.14 * Math.pow(y , 2))) * Math.pow((Math.pow(x, 2) + Math.sin(z) + Math.exp(Math.cos(z))), 0.25);
    }
    public Double calculate2(Double x, Double y, Double z)
    {
        return Math.pow((Math.cos(Math.expm1(x) + 1) + Math.log(Math.pow(1 + y, 2)) + Math.sqrt(Math.expm1(Math.cos(x)) + 1 + Math.pow(Math.sin(3.14 * z), 2)) + Math.cos(Math.pow(y, 2))), Math.sin(z));
    }
    public MainFrame() throws IOException {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        graphicField = new JLabel(new ImageIcon("Func1.jpg"));
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize( textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
        {
            try
            {
            Double x = Double.parseDouble(textFieldX.getText());
            Double y = Double.parseDouble(textFieldY.getText());
            Double z = Double.parseDouble(textFieldZ.getText());
            Double result;
            if (formulaId == 1) {
                result = calculate1(x, y, z);
            }
            else
            {
                result = calculate2(x, y, z);
            }
            textFieldResult.setText(result.toString());
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
            }
        }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        hboxFormulaType1.add(Box.createHorizontalGlue());
        addRadioButton1("Переменная 1", 1);
        addRadioButton1("Переменная 2", 2);
        addRadioButton1("Переменная 3", 3);
        radioButtons1.setSelected(radioButtons1.getElements().nextElement().getModel(), true);
        hboxFormulaType1.add(Box.createHorizontalGlue());
        hboxFormulaType1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JButton Mem = new JButton("MC");
        Mem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                mem1 = Double.parseDouble(textFieldX.getText());
                mem2 = Double.parseDouble(textFieldY.getText());
                mem3 = Double.parseDouble(textFieldZ.getText());
            }
        });
        JButton Plus = new JButton("M+");
        Plus.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                Double result;
                if (a == 1)
                {
                    result = Double.parseDouble(textFieldResult.getText()) + mem1;
                    textFieldResult.setText(result.toString());
                }
                if (a == 2)
                {
                    result = Double.parseDouble(textFieldResult.getText()) + mem2;
                    textFieldResult.setText(result.toString());
                }
                if (a == 3)
                {
                    result = Double.parseDouble(textFieldResult.getText()) + mem3;
                    textFieldResult.setText(result.toString());
                }
            }
        });
        Box hboxButtons1 = Box.createHorizontalBox();
        hboxButtons1.add(Box.createHorizontalGlue());
        hboxButtons1.add(Mem);
        hboxButtons1.add(Box.createHorizontalStrut(30));
        hboxButtons1.add(Plus);
        hboxButtons1.add(Box.createHorizontalGlue());
        hboxButtons1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(graphicField);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxFormulaType1);
        contentBox.add(hboxButtons1);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
