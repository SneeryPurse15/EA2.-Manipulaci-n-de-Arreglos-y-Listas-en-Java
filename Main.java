import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionPedidos gestion = new GestionPedidos();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Pizza-Track ===");
            System.out.println("1. Registrar Pizza");
            System.out.println("2. Deshacer (Undo)");
            System.out.println("3. Rehacer (Redo)");
            System.out.println("4. Mostrar Pedido Actual");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la pizza: ");
                    String nombre = sc.nextLine();
                    String[] ingredientes = new String[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Ingrediente " + (i + 1) + ": ");
                        ingredientes[i] = sc.nextLine();
                    }
                    Pizza nueva = new Pizza(nombre, ingredientes);
                    gestion.registrarPizza(nueva);
                    System.out.println("Pizza registrada!");
                    break;
                case 2:
                    gestion.deshacer();
                    System.out.println("Acción deshecha.");
                    break;
                case 3:
                    gestion.rehacer();
                    System.out.println("Acción rehecha.");
                    break;
                case 4:
                    Pizza actual = gestion.mostrarPedidoActual();
                    if (actual != null) {
                        System.out.println("Pedido actual: " + actual);
                    } else {
                        System.out.println("No hay pedido actual.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
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
        pilaUndo = new Pila();
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
