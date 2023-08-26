//package gitlet;
//
//import java.io.File;
//import static gitlet.Utils.*;
//
//// TODO: any imports you need here
//
///** Represents a gitlet repository.
// *  TODO: It's a good idea to give a description here of what else this Class
// *  does at a high level.
// *
// *  @author TODO
// */
//public class Repository {
//    /**
//     * TODO: add instance variables here.
//     *
//     * List all instance variables of the Repository class here with a useful
//     * comment above them describing what that variable represents and how that
//     * variable is used. We've provided two examples for you.
//     */
//
//    /** The current working directory. */
//    public static final File CWD = new File(System.getProperty("user.dir"));
//    /** The .gitlet directory. */
//    public static final File GITLET_DIR = join(CWD, ".gitlet");
//
//    public static void init(){
//        if (!GITLET_DIR.exists()){
//            GITLET_DIR.mkdir();
//        }
//        else{
//            System.out.println("A Gitlet version-control system already exists in the current directory.");
//            }
//
//        Commit Commit_0 = new Commit();
//
//    }
//}

package gitlet;

//import javassist.Loader;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    public Commit currentCommit;

    public Staging staging;
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /**
     * The current working directory.
     */

    public Repository() throws IOException {
//        currentCommit = null;
//        staging = new Staging();
        //TA said need to initialize all the files/directories before making all the stuff
        //he said maybe do a helper method and change up logic for commits
    }

    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File COMMITS_DIR = join(GITLET_DIR, ".commit");

    public static final File BLOBS_DIR = join(GITLET_DIR, ".blobs");

    public static final File HEAD = join(GITLET_DIR, ".head");

    public static final File STAGING_AREA = join(GITLET_DIR, ".staging");

    private static final File StagedFiles = Utils.join(GITLET_DIR, "StagedFiles");
    private static final File filesstagedTree = Utils.join(GITLET_DIR, "filesstagedTree");

    public static final File BRANCHES_DIR = Utils.join(GITLET_DIR, ".branches");

    public static final File activeBranch = Utils.join(COMMITS_DIR, "activeBranch");

    public static final File filesstagedrmtree = Utils.join(GITLET_DIR, "");


//The directory for the staged files


    /* TODO: fill in the rest of this class. */
    public void init() throws IOException {

        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        GITLET_DIR.mkdir();
        COMMITS_DIR.mkdir();
        BLOBS_DIR.mkdir();
        BRANCHES_DIR.mkdir();
        staging = new Staging();
        File stagingObject = Utils.join(GITLET_DIR, "stagingObject");
        Utils.writeObject(stagingObject, staging);

        Commit newCommit = new Commit();


    }


    public static boolean restoreFile(String filename) {

        File HeadCommite = Utils.join(Repository.COMMITS_DIR, "HeadCommit");
        Commit HEADCOMMI = Utils.readObject(HeadCommite, Commit.class);


        //Checking if the file version exists in the HEADCommit
        File CommitFiles = Utils.join(Repository.COMMITS_DIR, ".treemap" + HEADCOMMI.hash);
        TreeMap<String, String> commitFiles = Utils.readObject(CommitFiles, TreeMap.class);
        if (commitFiles.containsKey(filename) == false) {
            System.out.println("File does not exist in that commit.");
        }

        //Replaces the currentFile with the blob contents
        File currentFile = Utils.join(CWD, filename);
        File restored_file = Utils.join(BLOBS_DIR, filename + HEADCOMMI.hash);
        Blobs blob = Utils.readObject(restored_file, Blobs.class);
        String blobcontents = blob.getFilecontents();

        Utils.writeContents(currentFile, blobcontents);
        ;
        return true;

    }

    public void add(String fileName) {

        File filesstagedTree = Utils.join(Repository.GITLET_DIR, "filesstagedTree");
        TreeMap staging = Utils.readObject(filesstagedTree, TreeMap.class);
        File file = Utils.join(CWD, fileName);
        if (!file.exists()) {
            System.out.println("File does not exist.");
        } else {
            //check if file is already staged for addition
            //add copy of file to staging area
            if (staging.containsKey(file.getName())) {
                //if staging area contains the file
                File stagedFile = (File) staging.get(file.getName());
                if (isSameContent(file, stagedFile)) {
                    //if the content is the same as staged content, remove from staging area
                    staging.remove(file.getName());
                }
            }
            staging.put(file.getName(), file);
            Utils.writeObject(filesstagedTree, staging);

        }
    }

    private boolean checkifFileexistsinstaged(String filename) {
        File filesstagedTree = Utils.join(Repository.GITLET_DIR, "filesstagedTree");
        TreeMap treeacquired = Utils.readObject(filesstagedTree, TreeMap.class);
        for (Object file : treeacquired.keySet()) {
            if (filename.equals(file) == true) {
                return true;
            }
        }
        return false;
    }

    public void rm(String filename) {

        Commit Headcommit = getHead();

        //If the file is loaded onto staging area for addition
        File filesstagedTree = Utils.join(Repository.GITLET_DIR, "filesstagedTree");
        TreeMap staging = Utils.readObject(filesstagedTree, TreeMap.class);
        File file = Utils.join(CWD, filename);

        if (checkifFileexistsinstaged(filename) == true) {
            staging.remove(filename, staging.get(filename));
        }
        File newstaging = Utils.join(Repository.GITLET_DIR, "filesstagedTree");
        Utils.writeObject(newstaging, TreeMap.class);

        //If the files is tracked in the current commit
        //Check if file is being "tracked"
        if (fileinHead(filename) == true) {
            TreeMap removaltrees = getremovalstagingtree();
            removaltrees.put(filename, file);
            file.delete();
        }

    }

    public TreeMap getremovalstagingtree() {
        File removaltreef = Utils.join(COMMITS_DIR, "filesstagedrmtree ");
        TreeMap removaltree = Utils.readObject(removaltreef, TreeMap.class);
        return removaltree;
    }

    public boolean fileinHead(String filename) {
        Commit Headcommit = getHead();
        for (String filetrack : Headcommit.files_tracked) {
            if (filetrack == filename) {
                return true;
            }
        }
        return false;
    }

    public void global_log() {

        for (String fileName : Utils.plainFilenamesIn(COMMITS_DIR)) {
            File currentFile = Utils.join(COMMITS_DIR, fileName);
            Commit current = Utils.readObject(currentFile, Commit.class);
            System.out.println("===");
            System.out.println("commit " + current.hash);
            System.out.println("Date: " + current.timeStamp);
            System.out.println(current.message);
            System.out.println();
        }


    }

    public void status() {

    }

    public void branch(String branchname) {
        File HEADCoMM = Utils.join(Repository.COMMITS_DIR, "HeadCommit");
        Commit HEADCOMMIT = Utils.readObject(HEADCoMM, Commit.class);

        Commit newCommit = HEADCOMMIT;
        File newBranch = Utils.join(Repository.BRANCHES_DIR, branchname);
        Utils.writeObject(newBranch, newCommit);

    }

    public void makeitactivebranch(String branchname) {

        //updating the active branch
        File branchfile = Utils.join(COMMITS_DIR, branchname);
        Commit branch = Utils.readObject(branchfile, Commit.class);
        Commit activebranch = branch; //updated the active branch to the provided branch
        File activebranchfile = Utils.join(COMMITS_DIR, "activeBranch");
        Utils.writeObject(activebranchfile, activebranch);

        //updating the HEAD pointer
        File HEADCoMM = Utils.join(Repository.COMMITS_DIR, "HeadCommit");
        Commit HEADCOMMIT = Utils.readObject(HEADCoMM, Commit.class);
        HEADCOMMIT = activebranch;
        Utils.writeObject(HEADCoMM, HEADCOMMIT);

    }


    public void remove_branch(String branchname) { //Check if this is correct
        File branchfile = Utils.join(COMMITS_DIR, branchname);
        Commit branch = Utils.readObject(branchfile, Commit.class);
        branch = null;
        Utils.writeObject(branchfile, null);
    }

    public void reset(String commitID) {

        //Approach: We are going to go through every file in the filesstagedTree and then you can call restore on those files
        File currentCommitFile = Utils.join(COMMITS_DIR, commitID);
        Commit currentCommit = Utils.readObject(currentCommitFile, Commit.class); //reading the ccommit and setting it to currentCommit
        for (String filename : currentCommit.getCommit_tracker().keySet()) { //getting all the filenames from the currentCommit
            this.restoreFile(filename, commitID); //restoring all the files in the commit
        }

        File HEADCommit = Utils.join(COMMITS_DIR, "HeadCommit");
        Utils.writeContents(HEADCommit, currentCommit);

    }

    public Commit getHead() {
        File Heacommi = Utils.join(COMMITS_DIR, "HeadCommit");
        Commit heac = Utils.readObject(Heacommi, Commit.class);
        return heac;
    }

    public Commit getactivepointer() {
        File apf = Utils.join(COMMITS_DIR, "activeBranch");
        Commit ap = Utils.readObject(apf, Commit.class);
        return ap;
    }


    private boolean isSameContent(File file1, File file2) {
        if (readContents(file1) == readContents(file2)) {
            return true;
        } else {
            return false;
        }
    }

    public void commit(String message) {
        File filesstagedtree = Utils.join(GITLET_DIR, "filesstagedTree");
        TreeMap staging = Utils.readObject(filesstagedtree, TreeMap.class);
        if (staging.isEmpty()) {
            System.out.println("No changes added to the commit.");
            return;
        }
        if (message.isEmpty() || message.isBlank()) {
            System.out.println("Please enter a commit message.");
            return;

        } else {
            Commit newCommit = new Commit(message);
            //set the parent later
            staging.clear();
            currentCommit = newCommit;
        }
    }

    public void log() {
        File activebfile = Utils.join(Repository.COMMITS_DIR, "activeBranch");
        Commit activepointer = Utils.readObject(activebfile, Commit.class);

        Commit current = activepointer;
        while (current != null) {
            System.out.println("===");
            System.out.println("commit " + current.hash);
            System.out.println("Date: " + current.timeStamp);
            System.out.println(current.message);
            System.out.println();
            current = current.getParentCommitnotS();

        }
    }


    public boolean restore() {
        return true;
    }

    public boolean commitidinlist(String iD) {

        for (int i = 0; i < Repository.GITLET_DIR.list().length; i++) {
            if (iD == Repository.GITLET_DIR.list()[i]) {
                return true;
            }
        }
        return false;
    }


    public boolean restoreFile(String filename, String CommitID) {


        File Commite = Utils.join(Repository.COMMITS_DIR, CommitID);
        Commit COMMI = Utils.readObject(Commite, Commit.class);


        //Checking if the file version exists in the HEADCommit
        File CommitFiles = Utils.join(Repository.COMMITS_DIR, ".treemap" + COMMI.hash);
        TreeMap<String, String> commitFiles = Utils.readObject(CommitFiles, TreeMap.class);
        if (commitFiles.containsKey(filename) == false) {
            System.out.println("File does not exist in that commit.");
        }

        //Replaces the currentFile with the blob contents
        File currentFile = Utils.join(CWD, filename);
        File restored_file = Utils.join(BLOBS_DIR, filename + COMMI.hash);
        Blobs blob = Utils.readObject(restored_file, Blobs.class);
        String blobcontents = blob.getFilecontents();

        Utils.writeContents(currentFile, blobcontents);
        ;
        return true;

    }

    public void merge(String branchName) {
        boolean mergeConflict = false;
        ArrayList<File> fileList;
        if (mergeConlict1(branchName)) {
            return;
        }
    }

    private boolean mergeConlict1(String branchName) {
        if (!staging.stagedFiles.isEmpty() || !staging.removedFiles.isEmpty()) {
            System.out.println("You have uncommitted changes.");
            return true;
        }
        else if (!(new File(".gitlet/.branches/" + branchName + ".txt")).exists()){
            System.out.println("A branch with that name does not exist.");
            return true;
        }
        else if (branchName.equals(HEAD)) {
            System.out.println("Cannot merge a branch with iteslef.");
            return true;
        }
        return false;
    }

    public void switchBranch(String branchName) {
    }
}