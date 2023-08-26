package gitlet;

import java.io.File;
import java.io.IOException;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) throws IOException {
        // TODO: what if args is empty?
        String firstArg = args[0];

        Repository repo = new Repository();
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                if (args.length == 1) {
                    repo.init();
                    break;
                } else {
                    System.out.println("Incorrect operands.");
                }
            case "add": {
                if (args.length == 2) {
                    repo.add(args[1]);
                    break;
                } else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "commit": {
                if (args.length == 2) {
                    repo.commit(args[1]);
                    break;
                } else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "log": {
                if (args.length == 1) {
                    repo.log();
                    break;
                } else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "restore": {
                if (args.length == 3) {
                    repo.restoreFile(args[2]);
                    break;
                } else if (args.length == 4) {
                    repo.restoreFile(args[3], args[1]);
                    break;
                } else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "global-log": {
                if (args.length == 1) {
                    repo.global_log();
                    break;
                }
                else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "status": {
                if (args.length == 1) {
                    repo.status();
                    break;
                }
                else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "switch": {
                if (args.length == 1) {
                    repo.switchBranch(args[2]);
                    break;
                }
                else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "branch": {
                if (args.length == 2) {
                    repo.branch(args[2]);
                    break;
                }
                else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "rm-branch": {
                if (args.length == 2) {
                    repo.remove_branch(args[2]);
                    break;
                }
                else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "reset": {
                if (args.length == 2) {
                    repo.reset(args[2]);
                    break;
                }
                else {
                    System.out.println("Incorrect operands.");
                }
            }
            case "merge": {
                if (args.length == 2) {
                    repo.merge(args[2]);
                    break;
                }
                else {
                    System.out.println("Incorrect operands.");
                }
            }
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);
        }
    }
}