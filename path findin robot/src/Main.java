import java.util.Scanner;

class CustomQueue<T> {
    private Object[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CustomQueue(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = (T) array[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }
}

class CustomLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public CustomLinkedList() {
        head = null;
        tail = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class Grid {
    private boolean[][] grid;
    private int rowCount;
    private int columnCount;

    public Grid(int rows, int columns) {
        grid = new boolean[rows][columns];
        rowCount = rows;
        columnCount = columns;
    }

    public void addObstacle(int x, int y) {
        grid[x][y] = true;
    }

    public boolean isObstacle(int x, int y) {
        return grid[x][y];
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < rowCount && y >= 0 && y < columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}

class RobotState {
    private int x;
    private int y;

    public RobotState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class PathPlanner {
    private Grid grid;
    private RobotState start;
    private RobotState goal;

    public PathPlanner(Grid grid, RobotState start, RobotState goal) {
        this.grid = grid;
        this.start = start;
        this.goal = goal;
    }

    public CustomLinkedList<RobotState> planPath() {
        CustomQueue<RobotState> queue = new CustomQueue<>(grid.getRowCount() * grid.getColumnCount());
        boolean[][] visited = new boolean[grid.getRowCount()][grid.getColumnCount()];
        RobotState[][] cameFrom = new RobotState[grid.getRowCount()][grid.getColumnCount()];
        CustomLinkedList<RobotState> path = new CustomLinkedList<>();

        queue.enqueue(start);
        visited[start.getX()][start.getY()] = true;

        while (!queue.isEmpty()) {
            RobotState current = queue.dequeue();

            if (current.getX() == goal.getX() && current.getY() == goal.getY()) {
                return reconstructPath(cameFrom, current);
            }

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int newX = current.getX() + dir[0];
                int newY = current.getY() + dir[1];
                if (grid.isValidPosition(newX, newY) && !visited[newX][newY] && !grid.isObstacle(newX, newY)) {
                    RobotState next = new RobotState(newX, newY);
                    queue.enqueue(next);
                    cameFrom[newX][newY] = current;
                    visited[newX][newY] = true;
                }
            }
        }

        return path;
    }

    private CustomLinkedList<RobotState> reconstructPath(RobotState[][] cameFrom, RobotState current) {
        CustomLinkedList<RobotState> path = new CustomLinkedList<>();
        while (cameFrom[current.getX()][current.getY()] != null) {
            path.add(current);
            current = cameFrom[current.getX()][current.getY()];
        }
        return path;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows for the grid: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns for the grid: ");
        int columns = scanner.nextInt();
        Grid grid = new Grid(rows, columns);

        // Taking obstacle positions
        System.out.print("Enter number of obstacles: ");
        int numObstacles = scanner.nextInt();
        System.out.println("Enter obstacle positions (x, y):");
        for (int i = 0; i < numObstacles; i++) {
            int x, y;
            do {
                System.out.print("Enter position " + (i + 1) + " (x, y): ");
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (!grid.isValidPosition(x, y)) {
                    System.out.println("Invalid position. Please enter valid position.");
                } else if (grid.isObstacle(x, y)) {
                    System.out.println("Obstacle already exists at this position. Please enter a different position.");
                }
            } while (!grid.isValidPosition(x, y) || grid.isObstacle(x, y));
            grid.addObstacle(x, y);
        }

        // Taking robot position
        int startX, startY;
        do {
            System.out.print("Enter robot position (x, y): ");
            startX = scanner.nextInt();
            startY = scanner.nextInt();
            if (!grid.isValidPosition(startX, startY)) {
                System.out.println("Invalid position. Please enter valid position.");
            } else if (grid.isObstacle(startX, startY)) {
                System.out.println("Obstacle exists at this position. Please enter a different position.");
            }
        } while (!grid.isValidPosition(startX, startY) || grid.isObstacle(startX, startY));
        RobotState robot = new RobotState(startX, startY);

        // Taking goal position
        int goalX, goalY;
        do {
            System.out.print("Enter goal position (x, y): ");
            goalX = scanner.nextInt();
            goalY = scanner.nextInt();
            if (!grid.isValidPosition(goalX, goalY)) {
                System.out.println("Invalid position. Please enter valid position.");
            } else if (grid.isObstacle(goalX, goalY)) {
                System.out.println("Obstacle exists at this position. Please enter a different position.");
            }
        } while (!grid.isValidPosition(goalX, goalY) || grid.isObstacle(goalX, goalY) || (goalX == startX && goalY == startY));
        RobotState goal = new RobotState(goalX, goalY);

        PathPlanner pathPlanner = new PathPlanner(grid, robot, goal);
        CustomLinkedList<RobotState> path = pathPlanner.planPath();
        if (path.isEmpty()) {
            System.out.println("No valid path found.");
        } else {
            System.out.println("Path found:");

            // Print grid with path and goal marked
            printVisualGrid(grid, path, robot, goal);

            // Print shortest path cells
            System.out.println("Shortest path cells:");
            while (!path.isEmpty()) {
                RobotState state = path.removeFirst();
                System.out.println("(" + state.getX() + ", " + state.getY() + ")");
            }
        }

        scanner.close();
    }

    public static void printVisualGrid(Grid grid, CustomLinkedList<RobotState> path, RobotState robot, RobotState goal) {
        int rows = grid.getRowCount();
        int columns = grid.getColumnCount();

        // Initialize grid with obstacles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid.isObstacle(i, j)) {
                    System.out.print("X ");
                } else {
                    if (robot.getX() == i && robot.getY() == j) {
                        System.out.print("R ");
                    } else if (goal.getX() == i && goal.getY() == j) {
                        System.out.print("G ");
                    } else {
                        boolean isPathCell = false;
                        CustomLinkedList<RobotState> current = new CustomLinkedList<>();
                        CustomLinkedList<RobotState> tempPath = new CustomLinkedList<>();
                        while (!path.isEmpty()) {
                            RobotState state = path.removeFirst();
                            tempPath.add(state);
                            if (state.getX() == i && state.getY() == j) {
                                isPathCell = true;
                            }
                            current.add(state);
                        }
                        while (!tempPath.isEmpty()) {
                            path.add(tempPath.removeFirst());
                        }
                        if (isPathCell) {
                            System.out.print("P ");
                        } else {
                            System.out.print(". ");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}

