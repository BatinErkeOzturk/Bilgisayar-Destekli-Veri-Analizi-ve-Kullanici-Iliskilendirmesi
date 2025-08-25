
package panels;

import com.mycompany.analysisapp.*;

import javax.swing.*;
import java.awt.*;

public class InterestGraphPanel extends JPanel{
    
    private CustomLinkedList<String> interestList;
    private String username;
    
    public InterestGraphPanel(String username , CustomLinkedList<String> interestList){
        this.username = username;
        this.interestList = interestList;
        
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 350;
        
        drawInterestList(g,interestList,300,300);

       
    }
    
    private void drawInterestList(Graphics g, CustomLinkedList<String> interestList, int centerX, int centerY) {
    int radius = 100;
    double gapCoefficient = 1.5;

    double angleIncrement = 2 * Math.PI / interestList.getSize() * gapCoefficient;

    CustomNode<String> interestTemp = interestList.getHead();
    for (int i = 0; i < interestList.getSize(); i++) {
        double angle = i * angleIncrement;

        int x = centerX + (int) (radius * Math.cos(angle));
        int y = centerY + (int) (radius * Math.sin(angle));

        g.setColor(Color.GREEN);
        g.fillOval(x - 5, y - 5, 5, 5);

        g.setColor(Color.BLACK);
        g.drawLine(centerX, centerY, x, y);

        String interestName = interestTemp.getData();
        int nameX = x - 30;
        int nameY = y - 7;
        g.drawString(interestName, nameX, nameY);

        interestTemp = interestTemp.getNext();
    }
}

    
}
