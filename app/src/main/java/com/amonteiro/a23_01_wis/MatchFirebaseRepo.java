package com.amonteiro.a23_01_wis;

import com.amonteiro.a23_01_wis.beans.MatchBean;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MatchFirebaseRepo {

    private final static String TABLE_NAME = "matchs";

    //Pour éviter de réécrire tout à chaque fois
    private static CollectionReference getCollection() {
        return FirebaseFirestore.getInstance().collection(TABLE_NAME);
    }

    //Ajouter un match
    public static Task<DocumentReference> create(MatchBean data) {
        return getCollection().add(data);
    }

    public static Query getAllMatch() {
        return getCollection().orderBy("time", Query.Direction.DESCENDING);
    }

    public static void update(MatchBean data, String id) {
        getCollection().document(id).set(data);
    }
}
