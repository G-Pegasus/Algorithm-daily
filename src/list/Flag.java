package list;

import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;

public class Flag extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("我爱你中国");
        frame.getContentPane().add(new Flag(600));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static Shape createPentacle(double sx, double sy, double radius, double theta) {
        final double arc = Math.PI / 5;
        final double rad = Math.sin(Math.PI / 10) / Math.sin(3 * Math.PI / 10);
        GeneralPath path = new GeneralPath();
        path.moveTo(1, 0);
        for (int idx = 0; idx < 5; idx++) {
            path.lineTo(rad * Math.cos((1 + 2 * idx) * arc), rad * Math.sin((1 + 2 * idx) * arc));
            path.lineTo(Math.cos(2 * (idx + 1) * arc), Math.sin(2 * (idx + 1) * arc));
        }
        path.closePath();
        AffineTransform atf = AffineTransform.getScaleInstance(radius, radius);
        atf.translate(sx / radius, sy / radius);
        atf.rotate(theta);
        return atf.createTransformedShape(path);
    }

    private final int width;
    private final int height;
    private final double[] minX = {0.50, 0.60, 0.60, 0.50};
    private final double[] minY = {0.10, 0.20, 0.35, 0.45};

    public Flag(int width) {
        this.width = width / 3 * 3;
        this.height = width / 3 * 2;
        setPreferredSize(new Dimension(this.width, this.height));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.RED);
        g2d.fillRect(0, 0, width, height);

        double maxX = 0.25;
        double maxY = 0.25;
        double ox = height * maxX, oy = height * maxY;
        g2d.setPaint(Color.YELLOW);
        double maxR = 0.15;
        g2d.fill(createPentacle(ox, oy, height * maxR, -Math.PI / 2));

        for (int idx = 0; idx < 4; idx++) {
            double sx = minX[idx] * height, sy = minY[idx] * height;
            double theta = Math.atan2(oy - sy, ox - sx);
            double minR = 0.05;
            g2d.fill(createPentacle(sx, sy, height * minR, theta));
        }
    }
}