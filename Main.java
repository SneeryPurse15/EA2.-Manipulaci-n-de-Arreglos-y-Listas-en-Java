public class Main {
    public static void main(String[] args) {
        // Prueba rápida de GestionPedidos
        GestionPedidos gestion = new GestionPedidos();

        String[] ingredientes1 = {"Queso", "Tomate", "Jamón"};
        Pizza pizza1 = new Pizza("Hawaiana", ingredientes1);

        gestion.registrarPizza(pizza1);
        System.out.println("Pedido actual: " + gestion.mostrarPedidoActual());

        gestion.deshacer();
        System.out.println("Después de deshacer: " + gestion.mostrarPedidoActual());

        gestion.rehacer();
        System.out.println("Después de rehacer: " + gestion.mostrarPedidoActual());
    }
}

class Pizza {
    private String nombre;
    private String[] ingredientes;

    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
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

    public void push(Pizza pizza) {
        Nodo nuevo = new Nodo(pizza);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Pizza pop() {
        if (isEmpty()) return null;
        Pizza pizza = tope.dato;
        tope = tope.siguiente;
        return pizza;
    }

    public Pizza peek() {
        if (isEmpty()) return null;
        return tope.dato;
    }

    public boolean isEmpty() {
        return tope == null;
    }
}

class GestionPedidos {
    private Pila pilaPedidos = new Pila();
    private Pila pilaUndo = new Pila();

    public void registrarPizza(Pizza pizza) {
        pilaPedidos.push(pizza);
        pilaUndo = new Pila(); // limpiar pila de undo
    }

    public void deshacer() {
        Pizza ultima = pilaPedidos.pop();
        if (ultima != null) pilaUndo.push(ultima);
    }

    public void rehacer() {
        Pizza ultima = pilaUndo.pop();
        if (ultima != null) pilaPedidos.push(ultima);
    }

    public Pizza mostrarPedidoActual() {
        return pilaPedidos.peek();
    }
}
