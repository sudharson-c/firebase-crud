package com.example.firebasecrud.service;

import com.example.firebasecrud.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    public static final String COLLECTION_NAME = "users";
    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getId()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    public User getUser(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(userId);
        ApiFuture<com.google.cloud.firestore.DocumentSnapshot> future = documentReference.get();

        com.google.cloud.firestore.DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(User.class);
        } else {
            return null;
        }
    }

    public String updateUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getId()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(userId).delete();
        return "Document with User ID " + userId + " has been deleted";
    }
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> userList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            userList.add(document.toObject(User.class));
        }
        return userList;
    }
}
