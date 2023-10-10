===================================== Sin seguir el principio de Responsabilidad Única:

public class MultiTaskRobot {
    public void cook() {
        System.out.println("Cooking food...");
    }

    public void garden() {
        System.out.println("Gardening...");
    }

    public void paint() {
        System.out.println("Painting a picture...");
    }

    public void drive() {
        System.out.println("Driving a car...");
    }
}

===================================== Siguiendo el principio de Responsabilidad Única:


public class ChefRobot {
    public void cook() {
        System.out.println("Cooking food...");
    }
}

public class GardenerRobot {
    public void garden() {
        System.out.println("Gardening...");
    }
}

public class PainterRobot {
    public void paint() {
        System.out.println("Painting a picture...");
    }
}

public class DriverRobot {
    public void drive() {
        System.out.println("Driving a car...");
    }
}



===================================== Sin seguir el principio Open-Closed:

public class Robot {
    public void cut() {
        System.out.println("I can cut.");
    }

    public void paint() {
        System.out.println("Now, I can paint.");
    }
}

===================================== Siguiendo el principio Open-Closed usando herencia:

public class Robot {
    public void cut() {
        System.out.println("I can cut.");
    }
}

public class PaintingRobot extends Robot {
    public void paint() {
        System.out.println("Now, I can paint.");
    }
}

===================================== Siguiendo el principio Open-Closed usando composición:

interface Action {
    void perform();
}

public class CutAction implements Action {
    @Override
    public void perform() {
        System.out.println("I can cut.");
    }
}

public class PaintAction implements Action {
    @Override
    public void perform() {
        System.out.println("Now, I can paint.");
    }
}

public class Robot {
    private List<Action> actions;

    public Robot(List<Action> actions) {
        this.actions = actions;
    }

    public void performActions() {
        for (Action action : actions) {
            action.perform();
        }
    }
}


===================================== Sin seguir el LSP:

public class Sam {
    public void makeCoffee() {
        System.out.println("Here's your coffee!");
    }
}

public class Eden extends Sam {
    @Override
    public void makeCoffee() {
        System.out.println("I can't make coffee but here's water.");
    }
}

===================================== Siguiendo el LSP:
public class Sam {
    public void makeCoffee() {
        System.out.println("Here's your coffee!");
    }
}

public class Eden extends Sam {
    @Override
    public void makeCoffee() {
        System.out.println("Sure! Here's a cappuccino.");
    }
}


===================================== Sin seguir el LSP:
public class Bird {
    public void fly() {
        System.out.println("I can fly!");
    }
}

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}


===================================== Siguiendo el LSP:

public interface Flyable {
    void fly();
}

public class Bird {
    // propiedades y comportamientos generales de las aves
}

public class Sparrow extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Sparrow can fly!");
    }
}

public class Penguin extends Bird {
    // Penguin no implementa Flyable porque no puede volar
    public void swim() {
        System.out.println("Penguin can swim!");
    }
}

===================================== Sin seguir el ISP:
interface RobotActions {
    void spinAround();
    void rotateArms();
    void wiggleAntennas();
}

public class Robot implements RobotActions {
    @Override
    public void spinAround() {
        System.out.println("Spinning around!");
    }

    @Override
    public void rotateArms() {
        System.out.println("Rotating arms!");
    }

    @Override
    public void wiggleAntennas() {
        // Esto no es relevante para esta clase de robot.
        throw new UnsupportedOperationException("This robot doesn't have antennas!");
    }
}


===================================== Siguiendo el ISP:
interface SpinAction {
    void spinAround();
}

interface RotateArmsAction {
    void rotateArms();
}

interface WiggleAntennasAction {
    void wiggleAntennas();
}

public class Robot implements SpinAction, RotateArmsAction {
    @Override
    public void spinAround() {
        System.out.println("Spinning around!");
    }

    @Override
    public void rotateArms() {
        System.out.println("Rotating arms!");
    }

    // No hay necesidad de implementar wiggleAntennas() porque este robot no tiene antenas.
}


===================================== Sin seguir el DIP:
class PizzaCutterArm {
    void cutPizza() {
        System.out.println("Cutting pizza with my pizza cutter arm!");
    }
}

class Robot {
    PizzaCutterArm arm = new PizzaCutterArm();
    
    void cutPizza() {
        arm.cutPizza();
    }
}


===================================== Siguiendo el DIP:
interface CuttingTool {
    void cut();
}

class PizzaCutterArm implements CuttingTool {
    @Override
    public void cut() {
        System.out.println("Cutting pizza with the pizza cutter arm!");
    }
}

class LaserCutter implements CuttingTool {
    @Override
    public void cut() {
        System.out.println("Cutting pizza with a laser!");
    }
}

class Robot {
    CuttingTool tool;

    Robot(CuttingTool tool) {
        this.tool = tool;
    }
    
    void cutPizza() {
        tool.cut();
    }
}


===================================== Sin seguir el principio DRY:
class Player {
    void jump() {
        System.out.println("Player is jumping");
        // Other logic for jumping
    }

    void doubleJump() {
        System.out.println("Player is jumping");
        // Other logic for jumping
        System.out.println("Player is jumping again");
        // Other logic for jumping
    }
}


===================================== Siguiendo el principio DRY:
class Player {
    void jump() {
        performJump();
    }

    void doubleJump() {
        performJump();
        performJump();
    }

    private void performJump() {
        System.out.println("Player is jumping");
        // Other logic for jumping
    }
}


=====================================
    import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String input = "Preferred method of contact: email ||";
        Pattern pattern = Pattern.compile("(?<=Preferred method of contact: )[^|]+");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            System.out.println(matcher.group().trim());  // Output: email
        }
    }
}

=====================================
=====================================
=====================================
=====================================


