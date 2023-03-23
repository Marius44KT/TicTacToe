import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    private final Random random=new Random();
    private final JFrame frame=new JFrame();
    private final JPanel title_panel=new JPanel();
    private final JPanel button_panel=new JPanel();
    private final JLabel textfield=new JLabel();
    private final JButton[] buttons=new JButton[9];
    private final JPanel bottom_panel=new JPanel();
    //private final JButton restart_button=new JButton();
    private final MyCustomButton restart_button=new MyCustomButton("Restart game");
    private boolean player1_turn;
    private String player;



    public TicTacToe()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free",Font.BOLD,50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("TicTacToe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,600,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(96,96,96));
        for(int i=0; i<9; i++)
        {
            buttons[i]=new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);

            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
        }


        bottom_panel.setLayout(new BorderLayout());
        bottom_panel.setBounds(0,0,600,100);


        title_panel.add(textfield);
        bottom_panel.add(restart_button);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        frame.add(bottom_panel,BorderLayout.SOUTH);
        firstTurn();
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==restart_button)
        {
            for(int i=0; i<9; i++)
            {
                buttons[i].setText("");
                buttons[i].setEnabled(true);
                buttons[i].setBackground(new JButton().getBackground());
            }
            set_first_Player();
            restart_button.removeActionListener(this);
        }
        for(int i=0; i<9; i++)
        {
            if(e.getSource()==buttons[i] && player1_turn)
            {
                if(Objects.equals(buttons[i].getText(), ""))
                {
                    buttons[i].setForeground(new Color(255,0,0));
                    buttons[i].setText("X");
                    player1_turn=false;
                    textfield.setText("O turn");
                    check();
                }
            }
            if(e.getSource()==buttons[i] && !player1_turn)
            {
                if(Objects.equals(buttons[i].getText(), ""))
                {
                    buttons[i].setForeground(new Color(0,0,255));
                    buttons[i].setText("O");
                    player1_turn=true;
                    textfield.setText("X turn");
                    check();
                }
            }
        }
    }



    public void firstTurn()
    {
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        for(int i=0; i<9; i++)
            buttons[i].setEnabled(true);
        set_first_Player();
    }


    public void set_first_Player()
    {
        if(random.nextInt(2)==0)
        {
            player1_turn=true;
            textfield.setText("X Turn");
        }
        else
        {
            player1_turn=false;
            textfield.setText("O turn");
        }
    }


    public void check()
    {
        if(isVictory(0, 1, 2))
            Winner(0,1,2);
        else if(isVictory( 3, 4,5))
            Winner(3,4,5);
        else if(isVictory(6, 7, 8))
            Winner(6,7,8);
        else if(isVictory( 0, 3,6))
            Winner(0,3,6);
        else if(isVictory(1, 4, 7))
            Winner(1,4,7);
        else if(isVictory( 2, 5,8))
            Winner(2,5,8);
        else if(isVictory(0, 4, 8))
            Winner(0,4,8);
        else if(isVictory( 2, 4,6))
            Winner(2,4,6);
        else if(is_not_Victory())
            NoWinner();
    }



    public boolean isVictory(int a,int b,int c)
    {
        if(Objects.equals(buttons[a].getText(),buttons[b].getText()) && Objects.equals(buttons[b].getText(),buttons[c].getText())
                && (Objects.equals(buttons[c].getText(),"X") || Objects.equals(buttons[c].getText(),"O")))
        {
            player=buttons[a].getText();
            return true;
        }
        return false;
    }



    public boolean is_not_Victory()
    {
        boolean draw=true;
        for(int i=0; i<9; i++)
            if(Objects.equals(buttons[i].getText(), ""))
            {
                draw=false;
                break;
            }
        return draw;
    }



    public void Winner(int a,int b,int c)
    {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);
        for(int i=0; i<9; i++)
            buttons[i].setEnabled(false);
        textfield.setText(player+" Wins");
        restart_button.addActionListener(this);
    }



    public void NoWinner()
    {
        for(int i=0; i<9; i++)
            buttons[i].setEnabled(false);
        textfield.setText("Draw");
        restart_button.addActionListener(this);
    }
}
