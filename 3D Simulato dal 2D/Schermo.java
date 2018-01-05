package grafica;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Schermo extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    //ArrayList con tutti gli oggetti(poligoni) 3d - ogniuno di loro ha un 2D 'Polygono' dentro chiamato Disegnabile Poligono
    static ArrayList<Poligono> poligoni3d = new ArrayList<Poligono>();

    static ArrayList<Cubo> cubi = new ArrayList<Cubo>();
    static ArrayList<Prisma> prismi = new ArrayList<Prisma>();
    static ArrayList<Piramide> piramidi = new ArrayList<Piramide>();

    //Il poligono che il mouse sta toccando
    static PolygonObject poligonoPuntato = null;

    //Robot usato per tenere il mouse al centro dello schermo.
    Robot r;

    static double[] ViewFrom = new double[]{15, 5, 10},
            ViewTo = new double[]{0, 0, 0},
            direzioneLuce = new double[]{1, 1, 1};

    // Massimo e minimo zoom 
    static double zoom = 500, zoomMinimo = 200, zoomMassimo = 2500, mouseX = 0, mouseY = 0, velocita = 0.35;

    //FPS max = quanto vuoi..
    double drawFPS = 0, fpsMAX = 1000, ritardo = 1000.0 / fpsMAX, ultimoRefresh = 0, tempoIniziale = System.currentTimeMillis(), ultimoControlloFPS = 0, controlli = 0;

    double vistaVerticale = -0.9, vistaOrrizontale = 0, aimSight = 4, velRot_orrizontale = 900, velRot_verticale = 2200, posizioneSole = 0;

    //Terra conto dell'ordine per la quale gli oggetti nella scena vengono disegnati (ogni fotogramma per secondo)
    // andando a disegnare prima quelli piu vicini
    int[] nuovoOrdine;
    int altezza = 1;

    // Indica se vogliamo il contorno attorno ad ogni oggetto
    static boolean contorno = true;

    boolean[] tasti = new boolean[4];

    long tempoRipaint = 0;
    
    private final Color cielo = new Color(140, 150, 210);
    private final double velocitaSole = 0.005;

    private GeneraTerreno t;
    
            
    public Schermo() {
        this.addKeyListener(this);  // Ascolta la tastiera
        // Vuol dire che nel caso questo JPanel entri in uno stato diverso dal corrente (Quando ridisegnamo la scena) 
        // aquisisce il focus , quindi il frame la prima cosa che elaborera sarà questo panel qui, perche puo focussarsi
        setFocusable(true);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);

        mouseInvisibile();  // rendo il mouse trasparente cosi ci metto sopra una X

        t = new GeneraTerreno();
        
        //Tutti gli arraylist devono avere almeno 1 oggetto
        cubi.add(new Cubo(0, -5, 0, 2, 2, 2, Color.red));
        prismi.add(new Prisma(6, -5, 0, 2, 2, 2, Color.green));
        piramidi.add(new Piramide(12, -5, 0, 2, 2, 2, Color.blue));
        
        //cubi.add(new Cubo(18, -5, 0, 2, 2, 2, Color.red));
        /*
        cubi.add(new Cubo(20, -5, 0, 2, 2, 2, Color.red));
        cubi.add(new Cubo(22, -5, 0, 2, 2, 2, Color.red));
        cubi.add(new Cubo(20, -5, 2, 2, 2, 2, Color.red));
        prismi.add(new Prisma(18, -5, 2, 2, 2, 2, Color.green));
        prismi.add(new Prisma(22, -5, 2, 2, 2, 2, Color.green));
        piramidi.add(new Piramide(20, -5, 4, 2, 2, 2, Color.blue));
        */
        
    }

    @Override
    public void paintComponent(Graphics g) {
        // Disegna il background
        disegnaCielo(g);

        // Gestisco il movimento WASD
        movimentoCamera();

        //Data la posizione della videocamera imposto info
        Calculator.SetPrederterminedInfo();

        movimentoLuci();

        //Updates each polygon for this camera position
        for (int i = 0; i < poligoni3d.size(); i++) {
            poligoni3d.get(i).updatePolygon();
        }

        //rotate and update shape examples
        cubi.get(0).rotation += .01;
        cubi.get(0).aggiornaPoligono();

        prismi.get(0).rotation += .04;
        prismi.get(0).updatePoly();

        piramidi.get(0).rotation += .1;
        piramidi.get(0).updatePoly();

        //Set drawing order so closest polygons gets drawn last
        impostaOrdine();

        //Set the polygon that the mouse is currently over
        trovaPoligonoPuntato();

        //draw polygons in the Order that is set by the 'setOrder' function
        for (int i = 0; i < nuovoOrdine.length; i++) {
            poligoni3d.get(nuovoOrdine[i]).poligonoDisegnabile.drawPolygon(g);
        }

        //draw the cross in the center of the screen
        disegnaMirino(g);

        //Stampo gli fps
        g.drawString("FPS: " + (int) drawFPS, 40, 40);
        //SideViewVector
        g.setFont(new Font("Arial",10,30));
        g.drawString("Parkube C.Zuccante 4IC Sabani Florian", Main.getRisoluzione().width/3 +10, 40);

//		tempoRipaint = System.currentTimeMillis() - tempoRipaint; 
//		System.out.println(repaintTime);
        dormiEAggiorna();
    }

    /**
     * Ogni frame in base alla posizione del personaggio ,
     * vado a vedermi la posizione di tutti gli oggetti e uno ad uno
     * in base a quello piu vicino alla mia posizione 
     * metto la sua posizione nell arrayList in un array di interi
     * chiamato nuovo ordine
     * in modo che cosi poi per fare delle operazioni con gli oggetti 
     * risultera infinitamente piu veloce accederci 
     * in quanto solitamente si interagisce con ogetti vicini e non lontanissimi
     */
    private void impostaOrdine() {
        
        double[] k = new double[poligoni3d.size()];
        nuovoOrdine = new int[poligoni3d.size()];

        for (int i = 0; i < poligoni3d.size(); i++) {
            k[i] = poligoni3d.get(i).AvgDist;
            nuovoOrdine[i] = i;
        }

        double temp;
        int tempr;
        // 0(n^2)
        for (int a = 0; a < k.length - 1; a++) {
            for (int b = 0; b < k.length - 1-a; b++) {
                if (k[b] < k[b + 1]) {
                    temp = k[b];
                    tempr = nuovoOrdine[b];
                    nuovoOrdine[b] = nuovoOrdine[b + 1];
                    k[b] = k[b + 1];

                    nuovoOrdine[b + 1] = tempr;
                    k[b + 1] = temp;
                }
            }
        }
        
    }
    
    /**
     * Rendo il cursore normale trasparente
     */
    private void mouseInvisibile() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "InvisibleCursor");
        setCursor(invisibleCursor);
    }

    /**
     * Disegno una X come mirino
     * @param g Graphics che mi permette di disegnare sui pixel
     */
    private void disegnaMirino(Graphics g) {
        g.setColor(Color.black);
        g.drawLine((int) (Main.getRisoluzione().getWidth() / 2 - aimSight), (int) (Main.getRisoluzione().getHeight() / 2), (int) (Main.getRisoluzione().getWidth() / 2 + aimSight), (int) (Main.getRisoluzione().getHeight() / 2));
        g.drawLine((int) (Main.getRisoluzione().getWidth() / 2), (int) (Main.getRisoluzione().getHeight() / 2 - aimSight), (int) (Main.getRisoluzione().getWidth() / 2), (int) (Main.getRisoluzione().getHeight() / 2 + aimSight));
    }

    private void dormiEAggiorna() {
        long tempoCurrent = (long) (System.currentTimeMillis() - ultimoRefresh);

        controlli++;
        if (controlli >= 15) {
            drawFPS = controlli / ((System.currentTimeMillis() - ultimoControlloFPS) / 1000.0);
            ultimoControlloFPS = System.currentTimeMillis();
            controlli = 0;
        }

        if (tempoCurrent < 1000.0 / fpsMAX) {
            try {Thread.sleep((long) (1000.0 / fpsMAX - tempoCurrent));} catch (InterruptedException e) {}  //dormi
        }

        ultimoRefresh = System.currentTimeMillis();

        repaint();  //ridisegna tutto
    }

    private void movimentoLuci() {
        posizioneSole += velocitaSole;
        double mapSize = GeneraTerreno.grandezzaMappa * GeneraTerreno.grandezza;
        direzioneLuce[0] = mapSize / 2 - (mapSize / 2 + Math.cos(posizioneSole) * mapSize * 10);    //x
        direzioneLuce[1] = mapSize / 2 - (mapSize / 2 + Math.sin(posizioneSole) * mapSize * 10);    //y
        direzioneLuce[2] = -200;    //z
    }

    private void movimentoCamera() {
        
        Vector vista = new Vector(ViewTo[0] - ViewFrom[0], ViewTo[1] - ViewFrom[1], ViewTo[2] - ViewFrom[2]);
        double xMosso = 0, yMosso = 0, zMosso = 0;
        Vector VerticalVector = new Vector(0, 0, 1);
        Vector movimentoLaterale = vista.CrossProduct(VerticalVector);
        
        
        if (tasti[0]) {
            xMosso += vista.x;
            yMosso += vista.y;
            zMosso += vista.z;
        }

        if (tasti[2]) {
            xMosso -= vista.x;
            yMosso -= vista.y;
            zMosso -= vista.z;
        }

        if (tasti[1]) {
            xMosso += movimentoLaterale.x;
            yMosso += movimentoLaterale.y;
            zMosso += movimentoLaterale.z;
        }

        if (tasti[3]) {
            xMosso -= movimentoLaterale.x;
            yMosso -= movimentoLaterale.y;
            zMosso -= movimentoLaterale.z;
        }

        Vector movimento = new Vector(xMosso, yMosso, zMosso);
        
        if(true /*Se sono sopra qualcosa*/)
            if(ViewFrom[2] + movimento.z * velocita < t.getTerreno()+this.altezza){
                movimento.z = 0;
            }
        
        // sposta verso il basso
        
        sposta(ViewFrom[0] + movimento.x * velocita, ViewFrom[1] + movimento.y * velocita, ViewFrom[2] + movimento.z * velocita);
    }

    private void sposta(final double x, final double y, final double z) {
        ViewFrom[0] = x;
        ViewFrom[1] = y;
        ViewFrom[2] = z;
        aggiornaVisuale();
    }

    private void trovaPoligonoPuntato() {
        poligonoPuntato = null;
        //Scorro l'arrayList andandomi a pigliare gli oggetti piu vicini a me.
        for (int i = nuovoOrdine.length - 1; i >= 0; i--) {
            if (poligoni3d.get(nuovoOrdine[i]).poligonoDisegnabile.haMouseSopra() && poligoni3d.get(nuovoOrdine[i]).daDisegnare
                    && poligoni3d.get(nuovoOrdine[i]).poligonoDisegnabile.visible) {
                poligonoPuntato = poligoni3d.get(nuovoOrdine[i]).poligonoDisegnabile;
                break;
            }
        }
    }

    private void movimentiMouse(double nuovaPosX, double nuovaPosY) {
        double difX = (nuovaPosX - Main.getRisoluzione().getWidth() / 2);
        double difY = (nuovaPosY - Main.getRisoluzione().getHeight() / 2);
        difY *= 6 - Math.abs(vistaVerticale) * 5;
        vistaVerticale -= difY / velRot_verticale;
        vistaOrrizontale += difX / velRot_orrizontale;

        if (vistaVerticale > 0.999) {
            vistaVerticale = 0.999;
        }

        if (vistaVerticale < -0.999) {
            vistaVerticale = -0.999;
        }

        aggiornaVisuale();
    }

    private void aggiornaVisuale() {
        double r = Math.sqrt(1 - (vistaVerticale * vistaVerticale));
        ViewTo[0] = ViewFrom[0] + r * Math.cos(vistaOrrizontale);
        ViewTo[1] = ViewFrom[1] + r * Math.sin(vistaOrrizontale);
        ViewTo[2] = ViewFrom[2] + vistaVerticale;
    }

    private void centraMouse() {
        try {
            r = new Robot();
            r.mouseMove((int) Main.getRisoluzione().getWidth() / 2, (int) Main.getRisoluzione().getHeight() / 2);
        } catch (AWTException e) {
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            tasti[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            tasti[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            tasti[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            tasti[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            contorno = !contorno;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            chiudi();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            tasti[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            tasti[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            tasti[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            tasti[3] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        movimentiMouse(arg0.getX(), arg0.getY());
        mouseX = arg0.getX();
        mouseY = arg0.getY();
        centraMouse();
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        movimentiMouse(arg0.getX(), arg0.getY());
        mouseX = arg0.getX();
        mouseY = arg0.getY();
        centraMouse();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        if (arg0.getButton() == MouseEvent.BUTTON1) {
            if (poligonoPuntato != null) {
                poligonoPuntato.seeThrough = false;
            }
        }

        if (arg0.getButton() == MouseEvent.BUTTON3) {
            if (poligonoPuntato != null) {
                poligonoPuntato.seeThrough = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0) {
        if (arg0.getUnitsToScroll() > 0) {
            if (zoom > zoomMinimo) {
                zoom -= 25 * arg0.getUnitsToScroll();
            }
        } else {
            if (zoom < zoomMassimo) {
                zoom -= 25 * arg0.getUnitsToScroll();
            }
        }
    }

    private void disegnaCielo(Graphics g) {
        g.setColor(cielo);
        g.fillRect(0, 0, (int) Main.getRisoluzione().getWidth(), (int) Main.getRisoluzione().getHeight());
    }

    private void chiudi() {
        System.exit(0);
    }
}
