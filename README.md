# 🦉 Vatican // The 6ix Side Assistant

Check out the live **[User Guide Here](https://lamemario.github.io/ip/)**!

![Java](https://img.shields.io/badge/Java-17-orange)
![JavaFX](https://img.shields.io/badge/GUI-JavaFX-blue)
![Build](https://img.shields.io/badge/Build-Gradle-green)

**Vatican** is a desktop task management application optimised for use via a Command Line Interface (CLI) but wrapped in a modern, reactive **JavaFX GUI**.

It features a unique **Toronto wasteyute persona** that manages your productivity. It roasts you for errors and blesses you for productivity.

---

## 📸 GUI Features

Vatican uses **Type-Safe Contextual Styling**, changing the interface color dynamically based on the command type:

| Context | Color | Description |
| :--- | :--- | :--- |
| **Add Task** | 🔵 **Blue** | Used for `todo`, `deadline`, and `event` commands. |
| **Success** | 🟢 **Green** | Used when `mark`-ing a task as completed. |
| **Destructive** | 🟠 **Orange** | Used for `delete` and `bye` commands. |
| **Error** | 🔴 **Red** | Appears when commands are invalid or input is empty. |
| **Info** | ⚪ **White** | Standard output for `list` and `find`. |

---

## 🚀 Quick Start

### Prerequisites
* **JDK 17** or higher
* **IntelliJ IDEA** (Recommended)

### Setting up in IntelliJ
1.  Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` first).
2.  **Open Project:**
    * Click `Open`.
    * Select the `Vatican` project directory and click `OK`.
3.  **Configure SDK:**
    * Go to `File` > `Project Structure` > `Project`.
    * Ensure **SDK** is set to **17**.
    * Set **Language Level** to `SDK Default`.
4.  **Run:**
    * Navigate to `src/main/java/vatican/Vatican.java`.
    * Right-click and select `Run 'Vatican.main()'`.

### Building the Standalone App
To create the executable JAR file including all dependencies:

```bash
./gradlew shadowJar
