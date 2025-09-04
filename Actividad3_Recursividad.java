import java.util.*;

// hey profe, esta es mi actividad 3 de recursividad


public class Actividad3_Recursividad {

    // 1. Serie de Fibonacci recursiva
    public static int fibonacci(int n) {
        if (n <= 1) return n; // caso base
        return fibonacci(n - 1) + fibonacci(n - 2); // caso recursivo
    }

    // 2. Subset Sum (suma de subconjuntos)
    public static boolean subsetSum(int[] conjunto, int n, int objetivo) {
        if (objetivo == 0) return true; // caso base
        if (n == 0 && objetivo != 0) return false;
        if (conjunto[n - 1] > objetivo) return subsetSum(conjunto, n - 1, objetivo);
        return subsetSum(conjunto, n - 1, objetivo) || subsetSum(conjunto, n - 1, objetivo - conjunto[n - 1]);
    }

    // 3. Sudoku con backtracking (modo turbo pa que saque varias soluciones)
    static int N = 9;
    static int solucionesMax = 3;
    static int contador = 0;

    public static boolean esValido(int[][] tablero, int fila, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (tablero[fila][x] == num || tablero[x][col] == num) return false;
        }
        int startRow = fila - fila % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i + startRow][j + startCol] == num) return false;
            }
        }
        return true;
    }

    public static void resolverSudoku(int[][] tablero, int fila, int col) {
        if (fila == N - 1 && col == N) {
            contador++;
            System.out.println("\nSolución #" + contador + ":");
            imprimirSudoku(tablero);
            if (contador >= solucionesMax) return;
            return;
        }
        if (col == N) {
            fila++;
            col = 0;
        }
        if (tablero[fila][col] != 0) {
            resolverSudoku(tablero, fila, col + 1);
            return;
        }
        for (int num = 1; num <= 9; num++) {
            if (esValido(tablero, fila, col, num)) {
                tablero[fila][col] = num;
                resolverSudoku(tablero, fila, col + 1);
                tablero[fila][col] = 0;
            }
        }
    }

    public static void imprimirSudoku(int[][] tablero) {
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                System.out.print(tablero[r][d] + " ");
            }
            System.out.println();
        }
    }

    // 4. Factorial recursivo (pa no dejar)
    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // 5. Torres de Hanoi (el clásico de clásicos)
    public static void hanoi(int discos, char origen, char auxiliar, char destino) {
        if (discos == 1) {
            System.out.println("Mover disco 1 de " + origen + " a " + destino);
            return;
        }
        hanoi(discos - 1, origen, destino, auxiliar);
        System.out.println("Mover disco " + discos + " de " + origen + " a " + destino);
        hanoi(discos - 1, auxiliar, origen, destino);
    }

    // 6. Problema de las 8 reinas (backtracking a lo grande)
    static final int NQ = 8;

    public static void resolverReinas() {
        int[][] tablero = new int[NQ][NQ];
        if (!colocarReina(tablero, 0)) {
            System.out.println("No hay solucion para las reinas :( ");
        } else {
            imprimirReinas(tablero);
        }
    }

    public static boolean colocarReina(int[][] tablero, int col) {
        if (col >= NQ) return true;
        for (int i = 0; i < NQ; i++) {
            if (esSeguro(tablero, i, col)) {
                tablero[i][col] = 1;
                if (colocarReina(tablero, col + 1)) return true;
                tablero[i][col] = 0;
            }
        }
        return false;
    }

    public static boolean esSeguro(int[][] tablero, int fila, int col) {
        for (int i = 0; i < col; i++) if (tablero[fila][i] == 1) return false;
        for (int i = fila, j = col; i >= 0 && j >= 0; i--, j--) if (tablero[i][j] == 1) return false;
        for (int i = fila, j = col; i < NQ && j >= 0; i++, j--) if (tablero[i][j] == 1) return false;
        return true;
    }

    public static void imprimirReinas(int[][] tablero) {
        for (int i = 0; i < NQ; i++) {
            for (int j = 0; j < NQ; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // main con menu eterno hasta que uno diga ya basta
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n========= MENU ACTIVIDAD 3 =========");
            System.out.println("1. Fibonacci recursivo");
            System.out.println("2. Subset Sum (Suma de subconjuntos)");
            System.out.println("3. Sudoku recursivo (varias soluciones)");
            System.out.println("4. Factorial recursivo");
            System.out.println("5. Torres de Hanoi");
            System.out.println("6. Problema de las 8 reinas");
            System.out.println("7. Salir");
            System.out.print("Elige: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Dame un número: ");
                    int f = sc.nextInt();
                    System.out.println("Fibonacci de " + f + " = " + fibonacci(f));
                    break;
                case 2:
                    int[] conjunto = {3, 34, 4, 12, 5, 2};
                    System.out.print("Objetivo: ");
                    int obj = sc.nextInt();
                    System.out.println("Existe subconjunto? " + subsetSum(conjunto, conjunto.length, obj));
                    break;
                case 3:
                    int[][] sudoku = {
                        {5,3,0,0,7,0,0,0,0},
                        {6,0,0,1,9,5,0,0,0},
                        {0,9,8,0,0,0,0,6,0},
                        {8,0,0,0,6,0,0,0,3},
                        {4,0,0,8,0,3,0,0,1},
                        {7,0,0,0,2,0,0,0,6},
                        {0,6,0,0,0,0,2,8,0},
                        {0,0,0,4,1,9,0,0,5},
                        {0,0,0,0,8,0,0,7,9}
                    };
                    contador = 0;
                    resolverSudoku(sudoku, 0, 0);
                    if (contador == 0) System.out.println("No se encontró solución :( ");
                    break;
                case 4:
                    System.out.print("Dame un número: ");
                    int n = sc.nextInt();
                    System.out.println("Factorial de " + n + " = " + factorial(n));
                    break;
                case 5:
                    System.out.print("Número de discos: ");
                    int d = sc.nextInt();
                    hanoi(d, 'A', 'B', 'C');
                    break;
                case 6:
                    resolverReinas();
                    break;
                case 7:
                    System.out.println("Sale bye 👋 ya estuvo bueno");
                    break;
                default:
                    System.out.println("Opción no válida, intenta otra vez");
            }

        } while (opcion != 7);
    }
}
