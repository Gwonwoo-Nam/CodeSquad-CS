import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.LocalDate;
import javax.swing.JFrame;


public class GUI extends JFrame implements Runnable {
    Toolkit imageTool = Toolkit.getDefaultToolkit();
    public static String date="";

    Image GALAXY = imageTool.getImage("galaxy.jpg");

    Image RAW_SUN = imageTool.getImage("SUN.png");
    Image RAW_VENUS = imageTool.getImage("venus.png");
    Image RAW_MERCURY = imageTool.getImage("mercury.png");
    Image RAW_EARTH = imageTool.getImage("EARTH.png");
    Image RAW_MOON = imageTool.getImage("MOON.png");
    Image RAW_MARS = imageTool.getImage("mars.png");


    Image SUN = RAW_SUN.getScaledInstance(40,40, Image.SCALE_SMOOTH);
    Image MERCURY = RAW_MERCURY.getScaledInstance(20,20, Image.SCALE_SMOOTH);
    Image VENUS = RAW_VENUS.getScaledInstance(25,25, Image.SCALE_SMOOTH);


    Image EARTH = RAW_EARTH.getScaledInstance(30,30, Image.SCALE_SMOOTH);
    Image MOON = RAW_MOON.getScaledInstance(15,15, Image.SCALE_SMOOTH);
    Image MARS = RAW_MARS.getScaledInstance(20,20, Image.SCALE_SMOOTH);


    public GUI() {
        //GUI Frame 설정
        setTitle("Solar System");
        setSize(640,640);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {

        g.clearRect(0,0, 640, 640);
        //g.drawImage(GALAXY, 0, 0, null);
        g.drawString(date,480,100);
        g.drawImage(SUN, (int)PlanetsGUI.SUN.getXLocation(), (int)PlanetsGUI.SUN.getYLocation(), this);
        g.drawImage(EARTH, (int)PlanetsGUI.EARTH.getXLocation(), (int)PlanetsGUI.EARTH.getYLocation(), this);
        g.drawImage(MOON, (int)PlanetsGUI.MOON.getXLocation(), (int)PlanetsGUI.MOON.getYLocation(), this);
        g.drawImage(VENUS, (int)PlanetsGUI.VENUS.getXLocation(), (int)PlanetsGUI.VENUS.getYLocation(), this);
        g.drawImage(MERCURY, (int)PlanetsGUI.MERCURY.getXLocation(), (int)PlanetsGUI.MERCURY.getYLocation(), this);
        g.drawImage(MARS, (int)PlanetsGUI.MARS.getXLocation(), (int)PlanetsGUI.MARS.getYLocation(), this);
    }


    @Override
    public void run() {
        try {
            while(true) {
                repaint();
                Thread.sleep(15);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
