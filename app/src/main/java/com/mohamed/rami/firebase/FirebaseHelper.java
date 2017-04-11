package com.mohamed.rami.firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;



public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved,added;
    ArrayList<SettersGetters> settersGetterses = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db){
        this.db = db;
    }

    public boolean save(SettersGetters settersGetters){
        if(settersGetters ==null){
            saved = false;
        }else{
            try{
                db.child("User").push().setValue(settersGetters);
                saved = true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }


    public boolean addNICUData(NICUPosition nicuPosition){
        if(nicuPosition == null){
            added = false;
        }else{
            try{
                db.child("NICU").push().setValue(nicuPosition);
                added=true;
            }catch(DatabaseException e){
                e.printStackTrace();
                added = false;
            }
        }
        return added;
    }
    public boolean updateNICU(NICUPosition nicuPosition){
        if(nicuPosition == null){
            added = false;
        }else{
            try{
                db.child("User").child("nicuCount").setValue(nicuPosition.getNicuCount());
                added=true;
            }catch(DatabaseException e){
                e.printStackTrace();
                added = false;
            }
        }
        return added;
    }





    private void fetchData(DataSnapshot dataSnapshot){
        settersGetterses.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            SettersGetters settersGetters = ds.getValue(SettersGetters.class);
            settersGetterses.add(settersGetters);
        }
    }


    public ArrayList<SettersGetters> retreive(){
        db.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    fetchData(dataSnapshot);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    fetchData(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return settersGetterses;
        }
}
