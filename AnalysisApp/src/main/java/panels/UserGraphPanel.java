
package panels;

import com.mycompany.analysisapp.*;

import javax.swing.*;
import java.awt.*;

public class UserGraphPanel extends JPanel {

    private CustomLinkedList<String> followersList;
    private CustomLinkedList<String> followingList;
    private String username;

    public UserGraphPanel(String username, CustomLinkedList<String> followersList, CustomLinkedList<String> followingList) {
        this.username = username;
        this.followersList = followersList;
        this.followingList = followingList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 350;

        drawConnections(g, followersList, Color.RED, centerX - radius, centerY, -1);

        drawConnections(g, followingList, Color.BLUE, centerX + radius, centerY, 1);
    }

    private void drawConnections(Graphics g, CustomLinkedList<String> connections, Color color, int centerX, int centerY, int direction) {
        int radius = 300;

        double angleIncrement = 2 * Math.PI / connections.getSize();

        CustomNode<String> temp = connections.getHead();
        for (int i = 0; i < connections.getSize(); i++) {
            double angle = i * angleIncrement;

            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));

            g.setColor(color);
            g.fillOval(x - 5, y - 5, 5, 5);

            g.setColor(Color.BLACK);
            g.drawLine(centerX, centerY, x, y);

            String name = temp.getData();
            int nameX = x - 50 + direction * 10;
            int nameY = y - 5;
            g.drawString(name, nameX, nameY);

            temp = temp.getNext();
        }
    }
}
