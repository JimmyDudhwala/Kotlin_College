data class Task(val id: Int, var description: String, var isCompleted: Boolean = false)

class ToDoList {
    private val tasks = mutableListOf<Task>()
    private var taskId = 1

    fun addTask(description: String) {
        tasks.add(Task(taskId++, description))
        println("Task added.")
    }

    fun viewTasks() {
        if (tasks.isEmpty()) println("No tasks.") 
        else tasks.forEach { println("${it.id}. ${it.description} - ${if (it.isCompleted) "Completed" else "Pending"}") }
    }

    fun deleteTask(id: Int) {
        tasks.removeIf { it.id == id }.also { println(if (it) "Task removed." else "Task not found.") }
    }

    fun markTaskAsComplete(id: Int) {
        tasks.find { it.id == id }?.let { it.isCompleted = true; println("Task completed.") } ?: println("Task not found.")
    }
}

fun main() {
    val toDoList = ToDoList()
    var continueLoop = true

    while (continueLoop) {
        println("\n1. Add Task\n2. View Tasks\n3. Delete Task\n4. Complete Task\n5. Exit\nChoose an option:")
        when (readLine()?.toIntOrNull()) {
            1 -> { print("Enter task description: "); toDoList.addTask(readLine().orEmpty()) }
            2 -> toDoList.viewTasks()
            3 -> { print("Enter task ID to delete: "); toDoList.deleteTask(readLine()?.toIntOrNull() ?: -1) }
            4 -> { print("Enter task ID to complete: "); toDoList.markTaskAsComplete(readLine()?.toIntOrNull() ?: -1) }
            5 -> { println("Exiting..."); continueLoop = false }
            else -> println("Invalid option.")
        }
    }
}
