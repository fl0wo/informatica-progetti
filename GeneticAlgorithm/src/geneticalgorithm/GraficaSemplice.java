//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package geneticalgorithm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D.Double;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.function.IntSupplier;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public final class GraficaSemplice implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

    final String VERSIONE = "3.3";
    public final Color BIANCO;
    public final Color NERO;
    public final Color GRIGIO_CHIARO;
    public final Color GRIGIO;
    public final Color GRIGIO_SCURO;
    public final Color BLU;
    public final Color CIANO;
    public final Color VERDE;
    public final Color GIALLO;
    public final Color ROSA;
    public final Color ROSSO;
    public final Color MAGENTA;
    private final Color[] colori;
    public final int FRECCIA_SU = 38;
    public final int FRECCIA_GIU = 40;
    public final int FRECCIA_SX = 37;
    public final int FRECCIA_DX = 39;
    boolean modoXor;
    final Color DEFAULT_PEN_COLOR;
    final Color DEFAULT_CLEAR_COLOR;
    Color penColor;
    final int DEFAULT_SIZE = 512;
    int larghezza;
    int altezza;
    final double DEFAULT_PEN_RADIUS = 0.002D;
    double penRadius;
    boolean defer;
    final double BORDER = 0.0D;
    String TITOLO = "Applicazione Grafica Bella Zuccante";
    final double DEFAULT_XMIN = 0.0D;
    final double DEFAULT_XMAX = 1.0D;
    final double DEFAULT_YMIN = 0.0D;
    final double DEFAULT_YMAX = 1.0D;
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    private Object mouseLock;
    private Object keyLock;
    Font font;
    BufferedImage offscreenImage;
    BufferedImage onscreenImage;
    Graphics2D offscreen;
    Graphics2D onscreen;
    GraficaSemplice std;
    JFrame frame;
    boolean mousePressed;
    double mouseX;
    double mouseY;
    int mouseIntX;
    int mouseIntY;
    LinkedList<Character> keysTyped;
    TreeSet<Integer> keysDown;
    //Queue<Metodi> codaAzioni;

    public boolean isXorMode() {
        return modoXor;
    }

    public void setXor() {
        modoXor = true;
        offscreen.setXORMode(BIANCO);
    }

    public void setNonXor() {
        modoXor = false;
        offscreen.setPaintMode();
    }

    public Color coloreACaso() {
        return colori[(int) (Math.random() * (double) colori.length)];
    }

    public Color coloreRGB(int r, int g, int b) {
        return new Color(r % 255, g % 255, b % 255);
    }

    GraficaSemplice() {
    }

    GraficaSemplice(String titolo) {
        this.TITOLO = titolo;
    }
    
    public int larghezza() {
        return larghezza;
    }

    public int altezza() {
        return altezza;
    }

    public void setFinestra() {
        setFinestra(512, 512, TITOLO);
    }

    public void setFinestra(int larghezza, int altezza, String titolo) {
        if (larghezza >= 1 && altezza >= 1) {
            this.larghezza = larghezza;
            this.altezza = altezza;
            //init(titolo);
        } else {
            throw new IllegalArgumentException("larghezza e/o altezza non positive");
        }
    }


    JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem1 = new JMenuItem(" Salva Immagine  ");
        menuItem1.addActionListener(std);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(83, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem1);
        return menuBar;
    }

    void setXscale() {
        setXscale(0.0D, 1.0D);
    }

    void setYscale() {
        setYscale(0.0D, 1.0D);
    }

    void setXscale(double min, double max) {
        double size = max - min;
        synchronized (mouseLock) {
            xmin = min - 0.0D * size;
            xmax = max + 0.0D * size;
        }
    }

    void setYscale(double min, double max) {
        double size = max - min;;
        synchronized (mouseLock) {
            ymin = min - 0.0D * size;
            ymax = max + 0.0D * size;
        }
    }

    void setScale(double min, double max) {
        setXscale(min, max);
        setYscale(min, max);
    }

    double scaleX(double x) {
        return (double) larghezza * (x - xmin) / (xmax - xmin);
    }

    double scaleY(double y) {
        return (double) altezza * (ymax - y) / (ymax - ymin);
    }

    double factorX(double w) {
        return w * (double) larghezza / Math.abs(xmax - xmin);
    }

    double factorY(double h) {
        return h * (double) altezza / Math.abs(ymax - ymin);
    }

    double userX(double x) {
        return xmin + x * (xmax - xmin) / (double) larghezza;
    }

    double userY(double y) {
        return ymax - y * (ymax - ymin) / (double) altezza;
    }

    public void pulisci() {
        pulisci(DEFAULT_CLEAR_COLOR);
    }

    public void pulisci(Color c) {
        offscreen.setColor(c);
        offscreen.fillRect(0, 0, larghezza, altezza);
        offscreen.setColor(penColor);
        draw();
    }
    
    

    public double getGrossezza() {
        return penRadius;
    }

    public void setGrossezza() {
        setGrossezza(0.002D);
    }

    public void setGrossezza(double r) {
        if (r < 0.0D) {
            throw new IllegalArgumentException("grossezza < 0");
        } else {
            penRadius = r;
            float scaledPenRadius = (float) (r * 512.0D);
            BasicStroke stroke = new BasicStroke(scaledPenRadius, 1, 1);
            offscreen.setStroke(stroke);
        }
    }

    public Color getColore() {
        return penColor;
    }

    public void setColore() {
        setColore(DEFAULT_PEN_COLOR);
    }

    public void setColore(Color c) {
        penColor = c;
        offscreen.setColor(penColor);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font f) {
        font = f;
    }

    public void linea(double x0, double y0, double x1, double y1) {
        offscreen.draw(new Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
        draw();
    }

    public void linea(double x0, double y0, double x1, double y1, Color col, double gros) {
        Color savedColor = penColor;
        double savedPenRadius = penRadius;
        setColore(col);
        setGrossezza(gros);
        offscreen.draw(new Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
        draw();
        setColore(savedColor);
        penRadius = savedPenRadius;
    }

    private void pixel(int x, int y) {
        offscreen.fillRect((int) Math.round(scaleX((double) x)), (int) Math.round(scaleY((double) y)), 1, 1);
    }

    public void setPixel(int x, int y) {
        offscreen.fillRect(x, y, 1, 1);
        draw();
    }

    public int getRGBcode(Color c) {
        return 65536 * c.getRed() + 256 * c.getGreen() + c.getBlue();
    }

    public int getPixelRGBcode(int x, int y) {
        return offscreenImage.getRGB(x, y) & 16777215;
    }

    public int getMouseRGBcode() {
        return getPixelRGBcode(mouseIntX, mouseIntY);
    }

    private void pixel(double x, double y) {
        offscreen.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
    }

    public void punto(double x, double y) {
        double xs = scaleX(x);
        double ys = scaleY(y);
        double r = penRadius;
        float scaledPenRadius = (float) (r * 512.0D);
        if (scaledPenRadius <= 1.0F) {
            pixel(x, y);
        } else {
            offscreen.fill(new java.awt.geom.Ellipse2D.Double(xs - (double) (scaledPenRadius / 2.0F), ys - (double) (scaledPenRadius / 2.0F), (double) scaledPenRadius, (double) scaledPenRadius));
        }

        draw();
    }

    public void cerchio(double x, double y, double r) {
        if (r < 0.0D) {
            throw new IllegalArgumentException("il raggio di un cerchio non può essere negativo");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0D * r);
            double hs = factorY(2.0D * r);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.draw(new java.awt.geom.Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
        }
    }

    public void cerchioPieno(double x, double y, double r, Color c) {
        if (r < 0.0D) {
            throw new RuntimeException("raggio < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0D * r);
            double hs = factorY(2.0D * r);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.fill(new java.awt.geom.Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public void ellissePiena(double x, double y, double semiAsseMaggiore, double semiAsseMinore, Color c) {
        if (semiAsseMaggiore < 0.0D) {
            throw new IllegalArgumentException("semiasse maggiore < 0");
        } else if (semiAsseMinore < 0.0D) {
            throw new IllegalArgumentException("semiasse minore < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0D * semiAsseMaggiore);
            double hs = factorY(2.0D * semiAsseMinore);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.fill(new java.awt.geom.Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public void ellisse(double x, double y, double semiAsseMaggiore, double semiAsseMinore) {
        if (semiAsseMaggiore < 0.0D) {
            throw new IllegalArgumentException("semiasse maggiore < 0");
        } else if (semiAsseMinore < 0.0D) {
            throw new IllegalArgumentException("semiasse minore < 0");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0D * semiAsseMaggiore);
            double hs = factorY(2.0D * semiAsseMinore);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.fill(new java.awt.geom.Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
        }
    }

    public void arco(double x, double y, double r, double angolo1, double angolo2) {
        if (r < 0.0D) {
            throw new IllegalArgumentException("raggio dell'arco < 0 ");
        } else {
            while (angolo2 < angolo1) {
                angolo2 += 360.0D;
            }

            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0D * r);
            double hs = factorY(2.0D * r);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.draw(new java.awt.geom.Arc2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs, angolo1, angolo2 - angolo1, 0));
            }

            draw();
        }
    }

    public void quadrato(double x, double y, double lato) {
        if (lato < 0.0D) {
            throw new IllegalArgumentException("La misura del lato è  < 0");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(lato);
            double hs = factorY(lato);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.draw(new java.awt.geom.Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
        }
    }

    public void quadratoPieno(double x, double y, double lato, Color c) {
        if (lato < 0.0D) {
            throw new IllegalArgumentException("misura lato < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(lato);
            double hs = factorY(lato);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.fill(new java.awt.geom.Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public void rettangolo(double x, double y, double larghezza, double altezza) {
        if (larghezza < 0.0D) {
            throw new IllegalArgumentException("la larghezza non può essere < 0");
        } else if (altezza < 0.0D) {
            throw new IllegalArgumentException("l'altezza non può essere < 0");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(larghezza);
            double hs = factorY(altezza);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.draw(new java.awt.geom.Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
        }
    }

    public void rettangoloPieno(double x, double y, double larghezza, double altezza, Color c) {
        if (larghezza < 0.0D) {
            throw new IllegalArgumentException("la larghezza non può essere < 0");
        } else if (altezza < 0.0D) {
            throw new IllegalArgumentException("l'altezza non può essere < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(larghezza);
            double hs = factorY(altezza);
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            } else {
                offscreen.fill(new java.awt.geom.Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public void poligono(double[] x, double[] y) {
        int N = x.length;
        GeneralPath path = new GeneralPath();
        path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));

        for (int i = 0; i < N; ++i) {
            path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
        }

        path.closePath();
        offscreen.draw(path);
        draw();
    }

    public void poligonoPieno(double[] x, double[] y, Color c) {
        Color savedColor = penColor;
        setColore(c);
        int N = x.length;
        GeneralPath path = new GeneralPath();
        path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));

        for (int i = 0; i < N; ++i) {
            path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
        }

        path.closePath();
        offscreen.fill(path);
        draw();
        setColore(savedColor);
    }

    Image getImage(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        URL url;
        if (icon == null || icon.getImageLoadStatus() != 8) {
            try {
                url = new URL(filename);
                icon = new ImageIcon(url);
            } catch (Exception var3) {
                ;
            }
        }

        if (icon == null || icon.getImageLoadStatus() != 8) {
            url = GraficaSemplice.class.getResource("/" + filename);
            if (url == null) {
                throw new RuntimeException("immagine " + filename + " non trovata");
            }

            icon = new ImageIcon(url);
        }

        return icon.getImage();
    }

    public void disegnaImmagine(double x, double y, String s, double gradi) {
        Image image = getImage(s);
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = image.getWidth((ImageObserver) null);
        int hs = image.getHeight((ImageObserver) null);
        if (ws >= 0 && hs >= 0) {
            offscreen.rotate(Math.toRadians(-gradi), xs, ys);
            offscreen.drawImage(image, (int) Math.round(xs - (double) ws / 2.0D), (int) Math.round(ys - (double) hs / 2.0D), (ImageObserver) null);
            offscreen.rotate(Math.toRadians(gradi), xs, ys);
            draw();
        } else {
            throw new RuntimeException("l'immagine" + s + " è corrotta");
        }
    }

    public void disegnaImmagineRidimensionata(double x, double y, String percorso, double gradi, double largh, double alt) {
        Image image = getImage(percorso);
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(largh);
        double hs = factorY(alt);
        if (ws >= 0.0D && hs >= 0.0D) {
            if (ws <= 1.0D && hs <= 1.0D) {
                pixel(x, y);
            }

            offscreen.rotate(Math.toRadians(-gradi), xs, ys);
            offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0D), (int) Math.round(ys - hs / 2.0D), (int) Math.round(ws), (int) Math.round(hs), (ImageObserver) null);
            offscreen.rotate(Math.toRadians(gradi), xs, ys);
            draw();
        } else {
            throw new RuntimeException("l'immagine " + percorso + "è corrotta");
        }
    }

    public void testo(double x, double y, String s) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            offscreen.setFont(font);
            FontMetrics metrics = offscreen.getFontMetrics();
            double xs = scaleX(x);
            double ys = scaleY(y);
            int ws = metrics.stringWidth(s);
            int hs = metrics.getDescent();
            offscreen.drawString(s, (float) (xs - (double) ws / 2.0D), (float) (ys + (double) hs));
            draw();
        }
    }

    public void testo(double x, double y, String s, double gradi, Color c) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            Color savedColor = penColor;
            setColore(c);
            offscreen.rotate(Math.toRadians(-gradi), xs, ys);
            testo(x, y, s);
            offscreen.rotate(Math.toRadians(gradi), xs, ys);
            setColore(savedColor);
        }
    }

    public void testoSinistra(double x, double y, String s) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            offscreen.setFont(font);
            FontMetrics metrics = offscreen.getFontMetrics();
            double xs = scaleX(x);
            double ys = scaleY(y);
            int hs = metrics.getDescent();
            offscreen.drawString(s, (float) xs, (float) (ys + (double) hs));
            draw();
        }
    }

    public void testoDestra(double x, double y, String s) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            offscreen.setFont(font);
            FontMetrics metrics = offscreen.getFontMetrics();
            double xs = scaleX(x);
            double ys = scaleY(y);
            int ws = metrics.stringWidth(s);
            int hs = metrics.getDescent();
            offscreen.drawString(s, (float) (xs - (double) ws), (float) (ys + (double) hs));
            draw();
        }
    }

    void draw() {
        onscreen.drawImage(offscreenImage, 0, 0, (ImageObserver) null);
        frame.repaint();
    }

    public void salvaDisegno(String nomefile) {
        File file = new File(nomefile);
        String suffix = nomefile.substring(nomefile.lastIndexOf(46) + 1);
        if (suffix.toLowerCase().equals("png")) {
            try {
                ImageIO.write(onscreenImage, suffix, file);
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        } else if (suffix.toLowerCase().equals("jpg")) {
            WritableRaster raster = onscreenImage.getRaster();
            WritableRaster newRaster = raster.createWritableChild(0, 0, larghezza, altezza, 0, 0, new int[]{0, 1, 2});
            DirectColorModel cm = (DirectColorModel) onscreenImage.getColorModel();
            DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(), cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
            BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster, false, (Hashtable) null);

            try {
                ImageIO.write(rgbBuffer, suffix, file);
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        } else {
            System.out.println("Invalid image file type: " + suffix);
        }

    }

    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame, "Scelgliere estensione .png o .jpg ", 1);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            salvaDisegno(chooser.getDirectory() + File.separator + chooser.getFile());
        }

    }

    public boolean mousePremuto() {
        Object var0 = mouseLock;
        synchronized (mouseLock) {
            return mousePressed;
        }
    }

    public double mouseX() {
        Object var0 = mouseLock;
        synchronized (mouseLock) {
            return mouseX;
        }
    }

    public double mouseY() {
        Object var0 = mouseLock;
        synchronized (mouseLock) {
            return mouseY;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        Object var2 = mouseLock;
        synchronized (mouseLock) {
            mouseIntX = e.getX();
            mouseIntY = e.getY();
            mousePressed = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        Object var2 = mouseLock;
        synchronized (mouseLock) {
            mousePressed = false;
        }
    }

    public void mouseDragged(MouseEvent e) {
        Object var2 = mouseLock;
        synchronized (mouseLock) {
            mouseX = userX((double) e.getX());
            mouseY = userY((double) e.getY());
        }
    }

    public void mouseMoved(MouseEvent e) {
        synchronized (mouseLock) {
            mouseX = userX((double) e.getX());
            mouseY = userY((double) e.getY());
        }
    }

    public boolean tastoPremuto() {
        synchronized (keyLock) {
            return !keysTyped.isEmpty();
        }
    }

    public char tasto() {
        synchronized (keyLock) {
            if (keysTyped.isEmpty()) {
                throw new RuntimeException("il programma ha gia` elaborato tutti le pressioni dei tasti");
            } else {
                return ((Character) keysTyped.removeLast()).charValue();
            }
        }
    }

    public boolean premutoTasto(int keycode) {
        synchronized (keyLock) {
            return keysDown.contains(keycode);
        }
    }

    public void keyTyped(KeyEvent e) {
        Object var2 = keyLock;
        synchronized (keyLock) {
            keysTyped.addFirst(e.getKeyChar());
        }
    }

    public void keyPressed(KeyEvent e) {
        Object var2 = keyLock;
        synchronized (keyLock) {
            keysDown.add(e.getKeyCode());
        }
    }

    public void keyReleased(KeyEvent e) {
        Object var2 = keyLock;
        synchronized (keyLock) {
            keysDown.remove(e.getKeyCode());
        }
    }

    {
        BIANCO = Color.WHITE;
        NERO = Color.BLACK;
        GRIGIO_CHIARO = Color.LIGHT_GRAY;
        GRIGIO = Color.GRAY;
        GRIGIO_SCURO = Color.DARK_GRAY;
        BLU = Color.BLUE;
        CIANO = Color.CYAN;
        VERDE = Color.GREEN;
        GIALLO = Color.YELLOW;
        ROSA = Color.PINK;
        ROSSO = Color.RED;
        MAGENTA = Color.MAGENTA;
        colori = new Color[]{BIANCO, NERO, GRIGIO_CHIARO, GRIGIO, GRIGIO_SCURO, BLU, CIANO,  VERDE,  GIALLO, ROSA, ROSSO, MAGENTA};
        modoXor = false;
        DEFAULT_PEN_COLOR = NERO;
        DEFAULT_CLEAR_COLOR = BIANCO;
        larghezza = 512;
        altezza = 512;
        defer = false;
        mouseLock = new Object();
        keyLock = new Object();
        mousePressed = false;
        mouseX = 0.0D;
        mouseY = 0.0D;
        mouseIntX = 0;
        mouseIntY = 0;
        keysTyped = new LinkedList();
        keysDown = new TreeSet();
        Scheduler.add(this);
    }

}
