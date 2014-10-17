package matriks;

import larik.Larik;

/**
 * @author Yusuf Aji Wibowo
 *
 */
public class Matrik {
    //data

    private int nBaris;
    private int nKolom;
    private double[][] itemDt;

    //metode
    public Matrik(int m, int n) {
        this.nBaris = m;
        this.nKolom = n;
        itemDt = new double[this.nBaris][this.nKolom];
    }

    /**
     * konstruktor untuk membuat matrik
     *
     * @return nBaris
     */
    public Matrik(double[][] dt) {
        this(dt.length, dt[1].length);
        this.nBaris = dt.length;
        this.nKolom = dt[0].length;

        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                this.itemDt[i][j] = dt[i][j];
            }
        }
    }

    /**
     * fungsi untuk mendapatkan banyaknya baris
     *
     * @return nBaris
     */
    public int getNBaris() {
        return nBaris;
    }

    /**
     * fungsi untuk mendapatkan banyaknya kolom
     *
     * @return nKolom
     */
    public int getNKolom() {
        return nKolom;
    }

    public void setItem(int n, int m, double dt) {
        this.itemDt[n][m] = dt;
    }

    /**
     * fungsi untuk mendapatkan item pada baris n dan kolom m
     *
     * @param n : baris
     * @param m : kolom
     * @return the itemDt
     */
    public double getItem(int n, int m) {
        return itemDt[n][m];
    }

    public Matrik tambah(Matrik A, Matrik B) {
        Matrik C = null;
        if (A.getNBaris() == B.getNBaris() && A.getNKolom() == B.getNKolom()) {
            C = new Matrik(A.getNBaris(), A.getNKolom());
            for (int i = 0; i < A.getNBaris(); i++) {
                for (int j = 0; j < A.getNKolom(); j++) {
                    C.setItem(i, j, A.getItem(i, j) + B.getItem(i, j));
                }
            }
        }
        return C;
    }

    public void cetak(String komentar) {
        System.out.println(komentar);
        for (int i = 0; i < this.nBaris; i++) {
            for (int j = 0; j < this.nKolom; j++) {
                System.out.printf("%.2f  ", this.itemDt[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * fungsi untuk mendapatkan matrik hasil kali antara dua matrik
     *
     * @param A : Matrik
     * @param B : Matrik
     * @return C : Matrik
     */
    public Matrik kali(Matrik A, Matrik B) {
        Matrik C = null;
        if (A.getNKolom() == B.getNBaris()) {
            C = new Matrik(A.getNBaris(), B.getNKolom());
            double x;
            for (int i = 0; i < A.getNBaris(); i++) {
                for (int j = 0; j < B.getNKolom(); j++) {
                    x = 0;
                    for (int k = 0; k < B.getNKolom(); k++) {

                        x += A.itemDt[i][k] * B.itemDt[k][j];
                    }
                    C.setItem(i, j, x);
                }
            }
        }
        return C;
    }

    /**
     * fungsi untuk mendapatkan larik dari pada baris ke n dari matrik
     *
     * @param n : baris
     * @return l : larik
     */
    public Larik getBaris(int n) {
        Larik l = new Larik(this.nKolom);
        for (int i = 0; i < this.nKolom; i++) {
            double itemKolom = this.getItem(n, i);
            l.setItem(i, (int) itemKolom);
        }
        return l;
    }

    /**
     * fungsi untuk mendapatkan larik dari pada kolom ke m dari matrik
     *
     * @param m : kolom
     * @return l : larik
     */
    public Larik getKolom(int m) {
        Larik l = new Larik(this.nBaris);
        for (int i = 0; i < this.nBaris; i++) {
            double itemKolom = this.getItem(i, m);
            l.setItem(i, (int) itemKolom);
        }
        return l;
    }

    /**
     * fungsi untuk mendapatkan matrik transpos
     *
     * @param M : Matrik
     * @return N : Matrik
     */
    public Matrik Transpos(Matrik M) {
        Matrik N = null;
        N = new Matrik(M.getNKolom(), M.getNBaris());
        for (int i = 0; i < M.getNBaris(); i++) {
            for (int j = 0; j < M.getNKolom(); j++) {
                N.setItem(j, i, M.getItem(i, j));
            }
        }
        return N;
    }

    /**
     * fungsi untuk mendapatkan Matrik invers menghitung invers dengan rumus
     * (matrik adjoin/Determinan) Determinan = ab -bc matrik adjoin pada matrik
     * ordo 2 d	-c -b	a
     *
     * @return M : Matrik
     */
    public Matrik invers() {
        Matrik M = null;
        if (this.getNBaris() == this.getNKolom() && this.getNBaris() == 2) {
            double D = (this.itemDt[0][0] * this.itemDt[1][1]) - (this.itemDt[1][0] * this.itemDt[0][1]);
            M = new Matrik(this.nBaris, this.nKolom);
            M.setItem(0, 0, this.getItem(1, 1) / D);
            M.setItem(0, 1, -(this.getItem(0, 1) / D));
            M.setItem(1, 0, -(this.getItem(1, 0) / D));
            M.setItem(1, 1, this.getItem(0, 0) / D);
        }
        return M;
    }

    /**
     * fungsi untuk mendapatkan nilai covariance
     *
     * @return d : double
     */
    public double covariance() {
        double d = 0;
        Larik l[] = new Larik[nBaris];
        double jumlah[] = new double[nKolom];
        double rata[] = new double[nKolom];
        double tot = 1;
        for (int i = 0; i < this.nKolom; i++) {
            l[i] = this.getKolom(i);
        }

        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length; j++) {
                jumlah[i] += l[i].getItem(j);
            }
            rata[i] = jumlah[i] / l.length;
        }
        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                tot *= l[j].getItem(i) - rata[j];
            }
            d += tot;
        }
        return d;
    }

    public Boolean kesamaan(Matrik M) {
        Boolean hasil = true;
        return hasil;
    }

    @Override
    public String toString() {
        if (getNBaris() == 2) {
            return String.format("%s\t%s\n%s\t%s", this.getItem(0, 0), this.getItem(0, 1), this.getItem(1, 0), this.getItem(1, 1));
        } else if (getNBaris() == 3) {
            return String.format("%s\t%s\t%s\n%s\t%s\t%s\n%s\t%s\t%s", this.getItem(0, 0), this.getItem(0, 1), this.getItem(0, 2), this.getItem(1, 0),
                    this.getItem(1, 1), this.getItem(1, 2), this.getItem(2, 0), this.getItem(2, 1), this.getItem(2, 2));
        } else {
            return String.format("%s\t%s\t%s\t%s\n%s\t%s\t%s\t%s\n%s\t%s\t%s\t%s\n%s\t%s\t%s\t%s", this.getItem(0, 0), this.getItem(0, 1), this.getItem(0, 2), this.getItem(0, 3),
                    this.getItem(1, 0), this.getItem(1, 1), this.getItem(1, 2), this.getItem(1, 3),
                    this.getItem(2, 0), this.getItem(2, 1), this.getItem(2, 2), this.getItem(2, 3),
                    this.getItem(3, 0), this.getItem(3, 1), this.getItem(3, 2), this.getItem(3, 3));
        }
    }

    public static void main(String[] args) {
        Matrik A, B, C, D, E, F, G;
        double[][] X = {{1, 2, 3}, {3, 5, 7}, {2, 6, 8}};
        double[][] Y = {{2, 5, 1}, {6, 7, 4}, {1, 1, 1}};
        double[][] Z = {{2, 6}, {4, 5}};

        A = new Matrik(X);
        A.cetak("Matrik A : ");

        B = new Matrik(Y);
        B.cetak("Matrik B : ");

        C = new Matrik(A.getNBaris(), A.getNKolom());
        C = C.tambah(A, B);
        C.cetak("Matrik C (A + B) : ");

        D = new Matrik(A.getNBaris(), B.getNKolom());
        D = D.kali(A, B);
        D.cetak("Matrik D (A * B) : ");

        E = new Matrik(A.getNKolom(), A.getNBaris());
        E = E.Transpos(A);
        E.cetak("Matrik E (Transpose dari Matrik A) : ");


        F = new Matrik(Z);
        F.cetak("Matrik F :");
        F = F.invers();
        F.cetak("Matrik F (invers) : ");

        G = new Matrik(Z);
        G.cetak("Matrik G : ");
        System.out.println("Covariance dari matrik G : " + G.covariance());
    }
}