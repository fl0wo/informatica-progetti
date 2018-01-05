
/**
 *
 * @author Florian
 */
import static java.util.Arrays.sort;

/**
 *
 * @author Florian
 * @param <T> il tipo t dev essere confrontabile con un altro oggetto dello
 * stesso tipo
 */
public class BST<T extends Comparable<T>> {

    private Nodo<T> radice;

    public BST() {
        this.radice = null;
    }

    public BST(T o) {
        this.radice = new Nodo<>(o);
    }

    public BST(T[] array) {
        sort(array);
        radice = new Nodo(array, 0, array.length);
    }

    public void add(T o) {
        if (radice == null) {
            radice = new Nodo(o);
        } else {
            radice.inserisci(o);
        }
    }

    public boolean remove(Comparable value) {
            if (radice == null)
                  return false;
            else {
                  if (radice.informazione == value) {
                        Nodo auxRoot = new Nodo(0);
                        auxRoot.sinistra = radice;
                        boolean result = radice.remove(value, auxRoot);
                        radice = auxRoot.sinistra;
                        return result;
                  } else {
                        return radice.remove(value, null);
                  }
            }
      }

    public boolean esiste(T valore) {
        return esiste(radice, valore);
    }

    private boolean esiste(Nodo<T> node, T o) {
        if (node == null) {
            return false;
        }
        if (node.informazione.compareTo(o) == 0) {
            return true;
        } else if (node.informazione.compareTo(o) < 0) {
            esiste(node.right, o);
        } else {
            esiste(node.sinistra, o);
        }
        return false;
    }

    private void preVisita(Nodo<T> node) {
        if (node != null) {
            node.elabora();
            BST.this.preVisita(node.sinistra);
            BST.this.preVisita(node.right);
        }
    }

    public void crescente(Nodo<T> node) {
        if (node != null) {
            crescente(node.sinistra);
            node.elabora();
            crescente(node.right);
        }
    }

    public void decrescente(Nodo<T> node) {
        if (node != null) {
            decrescente(node.sinistra);
            decrescente(node.right);
            node.elabora();
        }
    }

    public void preVisita() {
        if (radice != null) {
            BST.this.preVisita(radice);
        }
    }

    public void simmetricaCrescente() {
        if (radice != null) {
            crescente(radice);
        }
    }

    public void simmetricaDecrescente() {
        if (radice != null) {
            decrescente(radice);
        }
    }

    class Nodo<T extends Comparable<T>> {

        T informazione;
        Nodo<T> sinistra;
        Nodo<T> right;

        public Nodo(T o) {
            this.informazione = o;
            this.sinistra = null;
            this.right = null;
        }

        public Nodo() {
            this.informazione = null;
            this.sinistra = null;
            this.right = null;
        }

        private Nodo(T[] array, int inizio, int fine) {
            int pivot = (inizio + fine) / 2;
            this.informazione = array[pivot];

            if (inizio < pivot) {
                sinistra = new Nodo(array, inizio, pivot);
            }
            if (pivot + 1 < fine) {
                right = new Nodo(array, pivot + 1, fine);
            }
        }

        public void inserisci(T o) {
            if (informazione.compareTo(o) < 0) {
                if (right == null) {
                    right = new Nodo(o);
                } else {
                    right.inserisci(o);
                }
            } else if (informazione.compareTo(o) > 0) {
                if (sinistra == null) {
                    sinistra = new Nodo<>(o);
                } else {
                    sinistra.inserisci(o);
                }
            }
            // se ce gia non lo inseriamo.
        }

        public boolean remove(Comparable value, Nodo parent) {
            if (value.compareTo(this.informazione) < 0) {
                if (sinistra != null) {
                    return sinistra.remove(value, this);
                } else {
                    return false;
                }
            } else if (value.compareTo(this.informazione)>0) {
                if (right != null) {
                    return right.remove(value, this);
                } else {
                    return false;
                }
            } else {
                if (sinistra != null && right != null) {
                    this.informazione = right.minValue();
                    right.remove(this.informazione, this);
                } else if (parent.sinistra == this) {
                    parent.sinistra = (sinistra != null) ? sinistra : right;
                } else if (parent.right == this) {
                    parent.right = (sinistra != null) ? sinistra : right;
                }
                return true;
            }
        }

        public T minValue() {
            if (sinistra == null) {
                return informazione;
            } else {
                return sinistra.minValue();
            }
        }

        public void elabora() {
            System.out.print(this.informazione + " ");
        }
    }

}
