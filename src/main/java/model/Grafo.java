package model;

import java.util.*;

public class Grafo {
    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void agregarArista(Arista arista) {
        aristas.add(arista);
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public boolean isGraphConnected() {
        if (nodos.isEmpty()) {
            return false;
        }

        Set<Nodo> visitedNodes = new HashSet<>();
        dfs(nodos.get(0), visitedNodes);

        return visitedNodes.size() == nodos.size();
    }

    private void dfs(Nodo nodo, Set<Nodo> visitedNodes) {
        visitedNodes.add(nodo);

        for (Arista arista : aristas) {
            Nodo nodoVecino = null;
            if (arista.getNodoInicio() == nodo) {
                nodoVecino = arista.getNodoFin();
            } else if (arista.getNodoFin() == nodo) {
                nodoVecino = arista.getNodoInicio();
            }

            if (nodoVecino != null && !visitedNodes.contains(nodoVecino)) {
                dfs(nodoVecino, visitedNodes);
            }
        }
    }

    public String getConexityJustification(boolean isConnected) {
        if (isConnected) {
            return "El grafo es conexo porque hay un camino entre cada par de nodos.";
        } else {
            return "El grafo no es conexo porque hay al menos un par de nodos que no están conectados.";
        }
    }

    public String getCircuitoEuleriano() {
        if (!isEuleriano()) {
            return "El grafo no tiene circuito Euleriano.";
        } else {
            List<Nodo> circuito = new ArrayList<>();
            Deque<Nodo> stack = new ArrayDeque<>();
            Map<Nodo, List<Nodo>> adjacencyMap = buildAdjacencyMap();

            Nodo inicio = nodos.get(0);
            stack.push(inicio);

            while (!stack.isEmpty()) {
                Nodo actual = stack.peek();
                if (adjacencyMap.containsKey(actual) && !adjacencyMap.get(actual).isEmpty()) {
                    stack.push(adjacencyMap.get(actual).remove(0));
                } else {
                    circuito.add(stack.pop());
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = circuito.size() - 1; i >= 0; i--) {
                sb.append(circuito.get(i).getNombre());
                if (i > 0) {
                    sb.append(" -> ");
                }
            }

            return "Circuito Euleriano: " + sb;
        }
    }

    private Map<Nodo, List<Nodo>> buildAdjacencyMap() {
        Map<Nodo, List<Nodo>> adjacencyMap = new HashMap<>();
        for (Arista arista : aristas) {
            adjacencyMap.computeIfAbsent(arista.getNodoInicio(), k -> new ArrayList<>()).add(arista.getNodoFin());
            adjacencyMap.computeIfAbsent(arista.getNodoFin(), k -> new ArrayList<>()).add(arista.getNodoInicio());
        }
        return adjacencyMap;
    }

    private boolean isEuleriano() {
        for (Nodo nodo : nodos) {
            int grado = 0;
            for (Arista arista : aristas) {
                if (arista.getNodoInicio() == nodo || arista.getNodoFin() == nodo) {
                    grado++;
                }
            }
            if (grado % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public String getCircuitoHamiltoniano() {
        if (!isHamiltoniano()) {
            return "El grafo no tiene circuito Hamiltoniano.";
        } else {
            List<Nodo> circuito = new ArrayList<>();
            Set<Nodo> visitedNodes = new HashSet<>();
            findHamiltonianCircuit(nodos.get(0), nodos.get(0), visitedNodes, circuito);

            StringBuilder sb = new StringBuilder();
            for (Nodo nodo : circuito) {
                sb.append(nodo.getNombre()).append(" -> ");
            }
            sb.append(circuito.get(0).getNombre());

            return "Circuito Hamiltoniano: " + sb.toString();
        }
    }

    private boolean findHamiltonianCircuit(Nodo start, Nodo current, Set<Nodo> visited, List<Nodo> circuit) {
        circuit.add(current);
        visited.add(current);

        if (circuit.size() == nodos.size()) {
            // Verificar si el último nodo está conectado al nodo inicial
            for (Arista arista : aristas) {
                if ((arista.getNodoInicio() == current && arista.getNodoFin() == start) ||
                        (arista.getNodoInicio() == start && arista.getNodoFin() == current)) {
                    circuit.add(start);
                    return true;
                }
            }
            circuit.remove(current);
            visited.remove(current);
            return false;
        }

        for (Nodo vecino : getVecinos(current)) {
            if (!visited.contains(vecino)) {
                if (findHamiltonianCircuit(start, vecino, visited, circuit)) {
                    return true;
                }
            }
        }

        circuit.remove(current);
        visited.remove(current);
        return false;
    }

    public String justificarGrafoHamiltoniano() {
        if (!isHamiltoniano()) {
            StringBuilder justificacion = new StringBuilder("El grafo no es Hamiltoniano debido a: ");

            // Verificar si el grafo tiene menos de tres nodos
            if (getNodos().size() < 3) {
                justificacion.append("tiene menos de tres nodos");
                return justificacion.toString();
            }

            // Comprobar el teorema de Dirac
            boolean cumpleDirac = true;
            for (Nodo nodo : getNodos()) {
                if (nodo.getGrado(this) < getNodos().size() / 2) {
                    cumpleDirac = false;
                    break;
                }
            }
            if (!cumpleDirac) {
                justificacion.append("no cumple con el teorema de Dirac");
                return justificacion.toString();
            }

            // Comprobar el teorema de Ore
            boolean cumpleOre = true;
            for (Nodo nodo1 : getNodos()) {
                for (Nodo nodo2 : getNodos()) {
                    // Solo considerar pares de nodos no adyacentes
                    if (!nodo1.esAdyacente(nodo2,this)) {
                        // Calcular la suma de los grados de los nodos no adyacentes
                        int gradoTotal = nodo1.getGrado(this) + nodo2.getGrado(this);
                        // Si la suma de los grados es menor que el número total de nodos, el grafo no es Hamiltoniano
                        if (!nodo1.esAdyacente(nodo2,this) && gradoTotal < getNodos().size()) {
                            cumpleOre = false;
                            break;
                        }
                    }
                }
                if (!cumpleOre) {
                    break;
                }
            }
            if (!cumpleOre) {
                justificacion.append("no cumple con el teorema de Ore");
                return justificacion.toString();
            }

            // Si no se encontraron razones específicas, devolver un mensaje general
            return "El grafo no es Hamiltoniano debido a razones desconocidas.";
        } else {
            return "El grafo es Hamiltoniano porque cumple con las condiciones necesarias.";
        }
    }

    public boolean isBipartito() {
        Set<Nodo> conjuntoA = new HashSet<>();
        Set<Nodo> conjuntoB = new HashSet<>();

        // Elegir un nodo aleatorio como nodo inicial
        Nodo nodoInicial = nodos.get(0);
        conjuntoA.add(nodoInicial);

        // Realizar un recorrido en profundidad (DFS) para asignar nodos a conjuntos
        Stack<Nodo> stack = new Stack<>();
        stack.push(nodoInicial);

        while (!stack.isEmpty()) {
            Nodo nodoActual = stack.pop();
            Set<Nodo> conjuntoActual = (conjuntoA.contains(nodoActual)) ? conjuntoA : conjuntoB;

            for (Nodo vecino : getVecinos(nodoActual)) {
                if (!conjuntoA.contains(vecino) && !conjuntoB.contains(vecino)) {
                    // Si el vecino no ha sido asignado a ningún conjunto, asignarlo al contrario del conjunto actual
                    if (conjuntoActual == conjuntoA) {
                        conjuntoB.add(vecino);
                    } else {
                        conjuntoA.add(vecino);
                    }
                    stack.push(vecino);
                } else if (conjuntoActual.contains(vecino)) {
                    // Si el vecino pertenece al mismo conjunto que el nodo actual, el grafo no es bipartito
                    return false;
                }
            }
        }

        // Si no hay conflictos de asignación, el grafo es bipartito
        return true;
    }
    private boolean isHamiltoniano() {
        // Verificar si el grafo cumple con las condiciones de un grafo Hamiltoniano

        // Si el grafo tiene menos de tres nodos, no puede ser Hamiltoniano
        if (nodos.size() < 3) {
            return false;
        }

        // Comprobar el teorema de Dirac: Si cada nodo tiene un grado mayor o igual a la mitad del número total de nodos
        for (Nodo nodo : nodos) {
            if (nodo.getGrado(this) < nodos.size() / 2) {
                return false;
            }
        }

        // Comprobar el teorema de Ore: La suma de los grados de cada par de nodos no adyacentes es al menos igual al número total de nodos
        for (Nodo nodo1 : nodos) {
            for (Nodo nodo2 : nodos) {
                // Solo considerar pares de nodos no adyacentes
                if (!nodo1.esAdyacente(nodo2,this)) {
                    // Calcular la suma de los grados de los nodos no adyacentes
                    int gradoTotal = nodo1.getGrado(this) + nodo2.getGrado(this);
                    // Si la suma de los grados es menor que el número total de nodos, el grafo no es Hamiltoniano
                    if (!nodo1.esAdyacente(nodo2,this) && gradoTotal < nodos.size()) {
                        return false;
                    }
                }
            }
        }

        // Si pasa todas las condiciones, el grafo es Hamiltoniano
        return true;
    }
    private Set<Nodo> getVecinos(Nodo nodo) {
        Set<Nodo> vecinos = new HashSet<>();
        for (Arista arista : aristas) {
            if (arista.getNodoInicio() == nodo) {
                vecinos.add(arista.getNodoFin());
            } else if (arista.getNodoFin() == nodo) {
                vecinos.add(arista.getNodoInicio());
            }
        }
        return vecinos;
    }

    public String identificarTipo() {
        StringBuilder tipo = new StringBuilder();

        boolean conexo = isGraphConnected();
        boolean euleriano = isEuleriano();
        boolean hamiltoniano = isHamiltoniano();
        boolean bipartito = isBipartito();

        if (conexo) {
            tipo.append("Conexo");
        } else {
            tipo.append("No conexo");
        }

        tipo.append(", ");

        if (euleriano) {
            tipo.append("Euleriano");
        } else {
            tipo.append("No euleriano");
        }

        tipo.append(", ");

        if (hamiltoniano) {
            tipo.append("Hamiltoniano");
        } else {
            tipo.append("No hamiltoniano");
        }

        tipo.append(", ");

        if (bipartito) {
            tipo.append("Bipartito");
        } else {
            tipo.append("No bipartito");
        }

        return tipo.toString();
    }

    public String getMatrizAdyacencia() {
        int size = nodos.size();
        int[][] matriz = new int[size][size];

        // Llenar la matriz de adyacencia
        for (Arista arista : aristas) {
            int indiceInicio = nodos.indexOf(arista.getNodoInicio());
            int indiceFin = nodos.indexOf(arista.getNodoFin());
            matriz[indiceInicio][indiceFin] = 1;
            matriz[indiceFin][indiceInicio] = 1; // Si el grafo es no dirigido, agregar esta línea
        }

        // Construir la cadena de texto con la matriz de adyacencia
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(matriz[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
