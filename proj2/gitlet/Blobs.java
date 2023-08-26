package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;


//Note for Alyssa : The reason I am serializing this class is so that Blobs can be stored and eventually retrieved later when we do goofy things like restore (aka the clutch god of persistence.)
public class Blobs implements Serializable {

    private String filename;
    private String filecontents;
    private byte[] ByteForm;

    private String SHAIDofcommit;



    public Blobs (String filename, String SHAIDofcommit){
        this.filename = filename;

        File fileblobtreemap = Utils.join(Repository.GITLET_DIR,"filesstagedTree");
        TreeMap filesstaged = Utils.readObject(fileblobtreemap, TreeMap.class); //Reading the filesstagedtreemap
        File filei = (File) filesstaged.get(filename); //getsthefilenamefromthetreemap
        this.filecontents = Utils.readContentsAsString(filei); //gets the contents inside the file
        this.SHAIDofcommit = SHAIDofcommit; //gets the SHAID for the blob
        this.save();


    }

//    public Blobs (String fileblob, byte[] byteForm, String SHAID){
//        this.fileblob = fileblob;
//        this.ByteForm = byteForm;
//        this.SHAID = SHAID;
//    }

    public String getFileName() {
        return filename;
    }

    public String getFilecontents() {
        return filecontents;
    }

    public byte[] getByteForm() {
        return ByteForm;
    }

    public String getSHAIDofcommit() {
        return SHAIDofcommit;
    }

    public void save()  {
//        Blobs blob = new Blobs(this.fileblob, this.ByteForm, this.SHAID);
        File blobFile = Utils.join(Repository.BLOBS_DIR, this.filename+this.SHAIDofcommit);
        Utils.writeObject(blobFile, this);
    }

    public static gitlet.Blobs readBlob(File blobID) {
        return Utils.readObject(blobID, gitlet.Blobs.class);
    }







}