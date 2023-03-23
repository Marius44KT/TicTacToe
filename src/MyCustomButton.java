import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MyCustomButton extends JButton {
    private Color pressedColor=new Color(0,0,0);
    private Color rolloverColor=new Color(25,25,25);
    private Color normalColor=new Color(25,25,25);

    public MyCustomButton (String text) {
        super(text);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);

        setBackground(normalColor);
        setForeground(new Color(25,255,0));
        setFont(new Font("Ink Free",Font.BOLD,50));
        setText(text);

        addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent evt)
            {
                if (getModel().isPressed())
                    setBackground(pressedColor);
                else if (getModel().isRollover())
                    setBackground(rolloverColor);
                else
                    setBackground(normalColor);
            }
        });
    }
}