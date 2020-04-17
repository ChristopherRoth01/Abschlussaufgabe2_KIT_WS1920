package tasktwo.userinterface.commands;

import java.util.regex.Pattern;

import tasktwo.userinterface.Session;

/**
 * Quit-Command. Does what the name says.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Quit extends Command {

    /**
     * Pattern
     */
    private Pattern quitPattern = Pattern.compile("^quit$");

    /**
     * This command gets a session in contrast to the other commands, because this
     * is the only Command which is executed on the session.
     */
    private Session session;

    @Override
    public void execute() {

        session.quit();
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    Pattern getPattern() {
        return this.quitPattern;
    }

}
