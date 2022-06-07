
package models;

/**
 *
 * @author atako
 */
public class FinalMark {
    int idFinalMark ;
    double finalMark ;


    public FinalMark() {
    }

    public FinalMark(double finalMark) {
        this.finalMark = finalMark;
    }

    public FinalMark(int idFinalMark, double finalMark) {
        this.idFinalMark = idFinalMark;
        this.finalMark = finalMark;
    }

    public int getIdFinalMark() {
        return idFinalMark;
    }

    public void setIdFinalMark(int idFinalMark) {
        this.idFinalMark = idFinalMark;
    }

    public double getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(double finalMark) {
        this.finalMark = finalMark;
    }

    
    
}
