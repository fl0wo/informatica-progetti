package grafica;

public class Calculator {

    static double t = 0;
    static Vector W1, W2, ViewVector, RotationVector, DirectionVector, PlaneVector1, PlaneVector2;
    static Piano P;
    static double[] CalcFocusPos = new double[2];

    static double[] CalculatePositionP(double[] ViewFrom, double[] ViewTo, double x, double y, double z) {
        double[] projP = getProj(ViewFrom, ViewTo, x, y, z, P);
        double[] drawP = getDrawP(projP[0], projP[1], projP[2]);
        return drawP;
    }

    static double[] getProj(double[] ViewFrom, double[] ViewTo, double x, double y, double z, Piano P) {
        Vector ViewToPoint = new Vector(x - ViewFrom[0], y - ViewFrom[1], z - ViewFrom[2]);

        t = (P.NV.x * P.P[0] + P.NV.y * P.P[1] + P.NV.z * P.P[2]
                - (P.NV.x * ViewFrom[0] + P.NV.y * ViewFrom[1] + P.NV.z * ViewFrom[2]))
                / (P.NV.x * ViewToPoint.x + P.NV.y * ViewToPoint.y + P.NV.z * ViewToPoint.z);

        x = ViewFrom[0] + ViewToPoint.x * t;
        y = ViewFrom[1] + ViewToPoint.y * t;
        z = ViewFrom[2] + ViewToPoint.z * t;

        return new double[]{x, y, z};
    }

    static double[] getDrawP(double x, double y, double z) {
        double DrawX = W2.x * x + W2.y * y + W2.z * z;
        double DrawY = W1.x * x + W1.y * y + W1.z * z;
        return new double[]{DrawX, DrawY};
    }

    static Vector getRotationVector(double[] ViewFrom, double[] ViewTo) {
        double dx = Math.abs(ViewFrom[0] - ViewTo[0]);
        double dy = Math.abs(ViewFrom[1] - ViewTo[1]);
        double xRot, yRot;
        xRot = dy / (dx + dy);
        yRot = dx / (dx + dy);

        if (ViewFrom[1] > ViewTo[1]) {
            xRot = -xRot;
        }
        if (ViewFrom[0] < ViewTo[0]) {
            yRot = -yRot;
        }

        Vector V = new Vector(xRot, yRot, 0);
        return V;
    }

    static void SetPrederterminedInfo() {
        ViewVector = new Vector(Schermo.ViewTo[0] - Schermo.ViewFrom[0], Schermo.ViewTo[1] - Schermo.ViewFrom[1], Schermo.ViewTo[2] - Schermo.ViewFrom[2]);
        DirectionVector = new Vector(1, 1, 1);
        PlaneVector1 = ViewVector.CrossProduct(DirectionVector);
        PlaneVector2 = ViewVector.CrossProduct(PlaneVector1);
        P = new Piano(PlaneVector1, PlaneVector2, Schermo.ViewTo);

        RotationVector = Calculator.getRotationVector(Schermo.ViewFrom, Schermo.ViewTo);
        W1 = ViewVector.CrossProduct(RotationVector);
        W2 = ViewVector.CrossProduct(W1);

        CalcFocusPos = Calculator.CalculatePositionP(Schermo.ViewFrom, Schermo.ViewTo, Schermo.ViewTo[0], Schermo.ViewTo[1], Schermo.ViewTo[2]);
        CalcFocusPos[0] = Schermo.zoom * CalcFocusPos[0];
        CalcFocusPos[1] = Schermo.zoom * CalcFocusPos[1];
    }
}
