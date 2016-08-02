package shoshin.alex.utils;

/**
 * Created by Administrator on 24.07.2016.
 */
public class Args {
    public static void checkInput(String[] args, String... argsDescription) throws IllegalArgumentException {
        if (args.length != argsDescription.length) {
            StringBuilder message = new StringBuilder("Expected ")
                    .append(argsDescription.length)
                    .append(" input parameters: ");
            for (String descr: argsDescription) {
                message.append(descr).append(", ");
            }
            throw new IllegalArgumentException(message.toString());
        }
    }
}