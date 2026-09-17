public class Main {
    public static void main(String[] args) {
        // Prueba rápida de la pila
        String[] ingredientes = {"Queso", "Tomate", "Jamón"};
        Pizza pizza = new Pizza("Hawaiana", ingredientes);

        Pila pila = new Pila();
        pila.push(pizza);

        System.out.println("Pizza en el tope: " + pila.peek());
    }
}

class Pizza {
    private String nombre;
    private String[] ingredientes;

    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    @Override
    public String toString() {
        return "Pizza: " + nombre + " | Ingredientes: " 
               + String.join(", ", ingredientes);
    }
}

class Nodo {
    Pizza dato;
    Nodo siguiente;

    public Nodo(Pizza dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class Pila {
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public void push(Pizza pizza) {
        Nodo nuevo = new Nodo(pizza);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Pizza pop() {
        if (isEmpty()) {
            return null;
        }
        Pizza pizza = tope.dato;
        tope = tope.siguiente;
        return pizza;
    }

    public Pizza peek() {
        if (isEmpty()) {
            return null;
        }
        return tope.dato;
    }

    public boolean isEmpty() {
        return tope == null;
    }
}
