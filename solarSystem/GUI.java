import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class GUI extends JFrame {
    Toolkit imageTool = Toolkit.getDefaultToolkit();
    public static String date="";

    Image RAW_SUN = imageTool.getImage("SUN.png");
    Image RAW_VENUS = imageTool.getImage("venus.png");
    Image RAW_MERCURY = imageTool.getImage("mercury.png");
    Image RAW_EARTH = imageTool.getImage("EARTH.png");
    Image RAW_MOON = imageTool.getImage("MOON.png");
    Image RAW_MARS = imageTool.getImage("mars.png");
    Image RAW_JUPITER = imageTool.getImage("jupiter.png");
    Image RAW_SATURN = imageTool.getImage("saturn.png");


    Image SUN = RAW_SUN.getScaledInstance(40,40, Image.SCALE_SMOOTH);
    Image MERCURY = RAW_MERCURY.getScaledInstance(20,20, Image.SCALE_SMOOTH);
    Image VENUS = RAW_VENUS.getScaledInstance(25,25, Image.SCALE_SMOOTH);
    Image EARTH = RAW_EARTH.getScaledInstance(30,30, Image.SCALE_SMOOTH);
    Image MOON = RAW_MOON.getScaledInstance(20,20, Image.SCALE_SMOOTH);
    Image MARS = RAW_MARS.getScaledInstance(20,20, Image.SCALE_SMOOTH);
    Image JUPITER = RAW_JUPITER.getScaledInstance(25,25, Image.SCALE_SMOOTH);
    Image SATURN = RAW_SATURN.getScaledInstance(25,25, Image.SCALE_SMOOTH);

    Image imgBuffer = null;
    Graphics buffer = null;

    public GUI() {
        setTitle("Solar System");
        setSize(640,640);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        imgBuffer = createImage(640,640);
        buffer = imgBuffer.getGraphics();
        update(g);
    }

    @Override
    public void update(Graphics g) {
        buffer.clearRect(0,0, 640, 640);
        buffer.drawString(date,480,100);
        buffer.drawImage(SUN, (int)PlanetsGUI.SUN.getXLocation(), (int)PlanetsGUI.SUN.getYLocation(), this);
        buffer.drawImage(EARTH, (int)PlanetsGUI.EARTH.getXLocation(), (int)PlanetsGUI.EARTH.getYLocation(), this);
        buffer.drawImage(MOON, (int)PlanetsGUI.MOON.getXLocation(), (int)PlanetsGUI.MOON.getYLocation(), this);
        buffer.drawImage(VENUS, (int)PlanetsGUI.VENUS.getXLocation(), (int)PlanetsGUI.VENUS.getYLocation(), this);
        buffer.drawImage(MERCURY, (int)PlanetsGUI.MERCURY.getXLocation(), (int)PlanetsGUI.MERCURY.getYLocation(), this);
        buffer.drawImage(MARS, (int)PlanetsGUI.MARS.getXLocation(), (int)PlanetsGUI.MARS.getYLocation(), this);
        buffer.drawImage(JUPITER, (int)PlanetsGUI.JUPITER.getXLocation(), (int)PlanetsGUI.JUPITER.getYLocation(), this);
        buffer.drawImage(SATURN, (int)PlanetsGUI.SATURN.getXLocation(), (int)PlanetsGUI.SATURN.getYLocation(), this);

        g.drawImage(imgBuffer,0,0,this);
        repaint();
    }
}
