package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {
    private String name = "ComplexCommand";
    private final List<DriverCommand> commands;

    private ComplexCommand(List<DriverCommand> commands) {
        this.commands = commands;
    }

    private ComplexCommand(List<DriverCommand> commands, String name) {
        this.commands = commands;
        this.name = name;
    }

    @Override
    public void execute(Job2dDriver driver) {
        commands.forEach(c -> c.execute(driver));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitICompoundCommand(this);
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public ICompoundCommand clone() {
        List<DriverCommand> cloneCommands = new ArrayList<>();
        this.commands.forEach((e) -> cloneCommands.add(e.clone()));

        return new ComplexCommand(cloneCommands);
    }

    public static final class Builder {
        private final List<DriverCommand> commands = new ArrayList<>();

        public Builder addCommand(DriverCommand command) {
            commands.add(command);
            return this;
        }

        public ComplexCommand build() {
            return new ComplexCommand(new ArrayList<>(commands));
        }
    }
}
