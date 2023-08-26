//package gitlet;
//
//// TODO: any imports you need here
//
//import java.util.Date; // TODO: You'll likely use this in this class
//import java.util.HashMap;
//
///** Represents a gitlet commit object.
// *  TODO: It's a good idea to give a description here of what else this Class
// *  does at a high level.
// *
// *  @author TODO
// */
//public class Commit {
//    /**
//     * TODO: add instance variables here.
//     *
//     * List all instance variables of the Commit class here with a useful
//     * comment above them describing what that variable represents and how that
//     * variable is used. We've provided one example for `message`.
//     */
//
//    private Date timeStamp;
//
//    /** The message of this Commit. */
//    private String message = "initial commit";
//    private static Commit recent_commit;
//    public Commit next_commit;
//    private static HashMap <String,Commit> commit_tracker = new HashMap<String,Commit>();
//
//    public Commit(){
//        this.timeStamp = firstTimeStamp();
//        this.message = "initial commit";
//        commit_tracker.put("00:00:00 UTC, Thursday, 1 January 1970", this);
//        recent_commit = this;
//    }
//    public Commit(String message){
//        this.timeStamp = timeStamp();
//        this.message = message;
//        commit_tracker.put("Replace this",this);
//        recent_commit.next_commit = this;
//        recent_commit = this;
//
//    }
//
//
//
//    public Date firstTimeStamp() {
//        Date timestamp = new Date(1970, 1, 1, 0, 0, 0);
//        return timestamp;
//    }
//
//    public Date timeStamp() {
//        Date timestamp = new Date();
//        return timestamp;
//    }
//
//    private String generateId(Commit commit) {
//        // Generates unique ID for the commit using SHA-1 hash
//        //tbh idk if i need this method bc of line 39/40 in init
//        return Utils.sha1(commit);
//}
//
//}

package gitlet;
import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;

import static gitlet.Utils.join;

// TODO: any imports you need here

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /**
     * The message of this Commit.
     */
    public String hash;
    public String message;
    public String timeStamp;
    public String nextCommit;
    public Commit HEADCommit;

    public String parentCommit;

    public ArrayList<String> files_tracked = new ArrayList<String>(); //Keeps track of the filename in the commit




    private static final SimpleDateFormat timeDate = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");

    private TreeMap<String, String> commit_tracker = new TreeMap<String, String>(); //Here, using a treeMap we can attach an blob SHAI and hand it to a file name


    public TreeMap<String, String> getCommit_tracker() {
        return commit_tracker;
    }

    public List<Commit> parent;



    public Commit() {
        //initial commit
        this.timeStamp = timeDate.format(new Date(0));
        this.message = "initial commit";
        HEADCommit = this;
        this.hash = generateId();

        //Setting the activebranch
        File activebfile = Utils.join(Repository.COMMITS_DIR,"activeBranch");
        Commit activebranch = this;
        Utils.writeObject(activebfile,activebranch);
        //Serializes this and sets it to activeBranch

        this.parent = null;
        this.SetupPersistenceforCommit();

    }


    public void updateHEAD(){ //resetting the head pointer to the active branch pointer
        File activebranchpointer = Utils.join(Repository.COMMITS_DIR,"activeBranch"); //Gets the current active pointer
        Commit activepoint = Utils.readObject(activebranchpointer, Commit.class); //Retrieves the current active pointer
        File HeadCommite = Utils.join(Repository.COMMITS_DIR, "HeadCommit"); //Gets the Head Pointer
        HEADCommit = activepoint; //Updates the Head Commit pointer to the active pointer
        Utils.writeObject(HeadCommite, HEADCommit); //Overwrites the headpointer to be the active pointer
    }

    public Commit loadCommit(String hashID){
        if (hashID == null){
            return null;
        }
        File commitfile = Utils.join(Repository.COMMITS_DIR, hashID);
        Commit commit = Utils.readObject(commitfile,Commit.class);
        return commit;
    }



    public Commit(String message) {
        //later commits
        this.message = message;
        this.timeStamp = timeDate.format(new Date());
        this.hash = generateId();
        SetupPersistenceforCommit();

        this.takeSnapshot(); //takes the snapshot and saves a treemap p
        updateAP();
        updateHEAD();
    }


    public void updateAP(){ //Serializes and updates the active pointer
        File activepfile = Utils.join(Repository.COMMITS_DIR,"activeBranch"); //gets the current (aka the old) active pointer
        Commit activepoint = Utils.readObject(activepfile,Commit.class); //declared the current active pointer
        this.parentCommit = activepoint.hash;//sets the previous commit of the current commit to the original active pointer
        activepoint.nextCommit = this.hash; //sets the next commit of the current active pointer to this
        activepoint = this; //sets the active pointer to the actual active pointer
        File updatedap = Utils.join(Repository.COMMITS_DIR, "activeBranch"); //Serialization of the active pointer
        Utils.writeObject(updatedap,activepoint);
    }

    public Date firstTimeStamp(
    ) {
        Date timestamp = new Date(1970, 1, 1, 0, 0, 0);
        return timestamp;
    }

    public Commit getParentCommitnotS(){
        return loadCommit(this.parentCommit);
    }

    public Date timeStamp() {
        Date timestamp = new Date();
        return timestamp;
    }

    private String generateId() {
        // Generates unique ID for the commit using SHA-1 hash
        byte[] commitObj = Utils.serialize(this);
        return Utils.sha1(commitObj);
    }

    public void SetupPersistenceforCommit(){
        File Commmited = Utils.join(Repository.COMMITS_DIR, this.hash);
        Utils.writeObject(Commmited,this);
    }



//    public void save() {
//        Utils.writeObject(file, this);
//    }

    public void SetupPersistenceforCT(){
        File CommitFiles = Utils.join(Repository.COMMITS_DIR,".treemap"+this.hash); //this.hash helps me with keeping track of what commit it is
        Utils.writeObject(CommitFiles,this.commit_tracker);
    }

    public void takeSnapshot(){
        File filesstagedTree = Utils.join(Repository.GITLET_DIR, "filesstagedTree");
        TreeMap<String,String> stagedFiles = Utils.readObject(filesstagedTree, TreeMap.class);
        for (String filename: stagedFiles.keySet()){
            Blobs blob = new Blobs(filename,this.hash);
            commit_tracker.put( filename,blob.getSHAIDofcommit()); //filename with the SHAID of the particular blob
            files_tracked.add(filename);
        }
        this.SetupPersistenceforCT();
    }




}