package gitlet;
import org.antlr.v4.runtime.tree.Tree;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static gitlet.Utils.*;

public class Staging implements Serializable {
    public TreeMap<String, File> stagedFiles;
    public ArrayList<String> removedFiles;
 //TreehMap for the contents of the file and the filename


    public void SetupPersistenceforStagedFiles() {
        File filesstagedTree = Utils.join(Repository.GITLET_DIR, "filesstagedTree");
        Utils.writeObject(filesstagedTree,this.stagedFiles);
    }

    public void SetupPersistenceForRemovedFiles() {
        File filesRmTree = Utils.join(Repository.GITLET_DIR, "filesRmTree");
        Utils.writeObject(filesRmTree, this.removedFiles);
    }



    public Staging() {

        File StagedFiles=Utils.join(Repository.GITLET_DIR,"StagedFiles");

        if (!StagedFiles.exists()) {
            StagedFiles.mkdir();
        }
        stagedFiles = new TreeMap<>();
        removedFiles = new ArrayList<>();
        this.SetupPersistenceforStagedFiles();
        this.SetupPersistenceForRemovedFiles();
    }

    public void add(String fileName, File file) {
        stagedFiles.put(fileName, file);
    }

    public void remove(String fileName) {
        stagedFiles.remove(fileName);
        removedFiles.add(fileName);
    }
    public void rm (String filename) {
        Utils.restrictedDelete(filename);
    }

    public boolean isEmpty() {
        return stagedFiles.isEmpty();
    }

    public void clear() {
        stagedFiles.clear();
        removedFiles.clear();
    }

    public TreeMap<String, File> addedFiles() {
        return stagedFiles;
    }

    public ArrayList<String> removedFiles() {
        return removedFiles;
    }
}