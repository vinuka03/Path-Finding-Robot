### **Project: Grid-Based Path Planning for a Robot**  

#### **Overview**  
This project implements a **grid-based path planning system** for a robot using **Java**. The system allows users to define a grid, add obstacles, set a robot's starting position, and define a goal position. The program then calculates the **shortest path** from the start to the goal using a **breadth-first search (BFS) algorithm** implemented with a custom queue and linked list data structures.  

#### **Key Features**  
- **Custom Grid System:** Users can specify grid dimensions and place obstacles at desired positions.  
- **Obstacle Handling:** Ensures that the robot and goal positions do not overlap with obstacles.  
- **Path Planning Algorithm:** Uses a **BFS-based approach** to find the shortest path from the robot’s start position to the goal.  
- **Custom Data Structures:** Implements a custom **queue** for BFS traversal and a **linked list** to store the path.  
- **Visual Representation:** Displays the grid with obstacles (`X`), the robot (`R`), the goal (`G`), and the computed path (`P`).  
- **Error Handling & Input Validation:** Ensures that users input valid grid coordinates and prevents incorrect placements.  

#### **Technologies Used**  
- **Java** (for implementation and logic)  
- **Object-Oriented Programming (OOP)** (to structure the grid, robot state, and path planning logic)  
- **Custom Queue and Linked List** (to optimize pathfinding operations)  
- **BFS Algorithm** (for shortest path calculation)  

#### **How It Works**  
1. The user **defines the grid size** (rows and columns).  
2. The user **adds obstacles** by specifying their coordinates.  
3. The user **sets the robot's start position** and a **goal position**.  
4. The program **searches for the shortest path** from the robot to the goal.  
5. If a path exists, it is **displayed visually** in the console.  
6. If no path is found, the system notifies the user.  

#### **Potential Applications**  
- **Autonomous robotics navigation**  
- **AI-driven grid-based path planning**  
- **Simulation of real-world movement in structured environments**  
- **Fundamental algorithmic learning in computer science**  

This project demonstrates expertise in **algorithm design, data structures, and AI pathfinding techniques**, making it useful for robotics and AI-based applications. 🚀
