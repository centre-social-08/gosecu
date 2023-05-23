package com.amonteiro.a23_01_wis;

import com.amonteiro.a23_01_wis.beans.ToolBean;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ToolFirebaseRepo {

    private final static String TABLE_NAME = "tools";

    //Pour éviter de réécrire tout à chaque fois
    public static CollectionReference getCollection() {
        return FirebaseFirestore.getInstance().collection(TABLE_NAME);
    }

    //Ajouter un Tool
    public static Task<DocumentReference> create(ToolBean data) {
        return getCollection().add(data);
    }

    public static Query getAllTool() {
        return getCollection().orderBy("name", Query.Direction.DESCENDING);
    }

    public static void update(ToolBean data, String id) {
        getCollection().document(id).set(data);
    }
}
