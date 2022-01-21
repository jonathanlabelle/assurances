
package Contrat;

import java.util.Objects;

public class PoliceSoin {

    private Contrat categorieSoin;
    private int numeroSoin;

    public PoliceSoin(Contrat contrat, int numeroSoin) {
        setCategorieSoin(contrat);
        setNumeroSoin(numeroSoin);
    }

    public Contrat getCategorieSoin() {
        return categorieSoin;
    }

    public int getNumeroSoin() {
        return numeroSoin;
    }

    private void setCategorieSoin(Contrat contrat) {
        this.categorieSoin = contrat;
    }

    private void setNumeroSoin(int numeroSoin) {
        this.numeroSoin = numeroSoin;
    }

    @Override
    public String toString(){
        return String.format("%s%s",
                categorieSoin,
                numeroSoin);
    }


    // equals Java class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoliceSoin)) return false;
        PoliceSoin policeSoin = (PoliceSoin) o;
        return this.categorieSoin == policeSoin.categorieSoin
                && numeroSoin == policeSoin.numeroSoin;
    }

    // hashcode Java class
    @Override
    public int hashCode() {
        return Objects.hash(categorieSoin, numeroSoin);
    }
}
