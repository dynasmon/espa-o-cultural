package com.example.espacocultural;

import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirestoreManager {

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final String TAG = "FirestoreManager";

    /**
     * Adiciona ou atualiza um documento na coleção especificada.
     * @param collectionName Nome da coleção.
     * @param documentId ID do documento (se nulo, será gerado automaticamente).
     * @param data Dados a serem salvos (mapa de chave e valor).
     * @param callback Callback de sucesso ou erro.
     */
    public void addOrUpdateDocument(String collectionName, String documentId, Map<String, Object> data, FirestoreCallback callback) {
        if (documentId == null || documentId.isEmpty()) {
            firestore.collection(collectionName)
                    .add(data)
                    .addOnSuccessListener(documentReference -> {
                        callback.onSuccess("Documento adicionado/atualizado com sucesso!");
                        Log.d(TAG, "Documento salvo com sucesso: " + data);
                    })
                    .addOnFailureListener(e -> {
                        callback.onFailure("Erro ao salvar documento: " + e.getMessage());
                        Log.e(TAG, "Erro ao salvar documento", e);
                    });
        } else {
            firestore.collection(collectionName)
                    .document(documentId)
                    .set(data)
                    .addOnSuccessListener(aVoid -> {
                        callback.onSuccess("Documento adicionado/atualizado com sucesso!");
                        Log.d(TAG, "Documento salvo com sucesso: " + data);
                    })
                    .addOnFailureListener(e -> {
                        callback.onFailure("Erro ao salvar documento: " + e.getMessage());
                        Log.e(TAG, "Erro ao salvar documento", e);
                    });
        }
    }

    /**
     * Busca um documento específico por ID.
     * @param collectionName Nome da coleção.
     * @param documentId ID do documento.
     * @param callback Callback com os dados ou erro.
     */
    public void getDocument(String collectionName, String documentId, FirestoreDocumentCallback callback) {
        firestore.collection(collectionName)
                .document(documentId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        callback.onSuccess(documentSnapshot.getData());
                        Log.d(TAG, "Documento encontrado: " + documentSnapshot.getData());
                    } else {
                        callback.onFailure("Documento não encontrado");
                        Log.d(TAG, "Documento não encontrado");
                    }
                })
                .addOnFailureListener(e -> {
                    callback.onFailure("Erro ao buscar documento: " + e.getMessage());
                    Log.e(TAG, "Erro ao buscar documento", e);
                });
    }

    /**
     * Busca todos os documentos de uma coleção.
     * @param collectionName Nome da coleção.
     * @param callback Callback com a lista de documentos ou erro.
     */
    public void getAllDocuments(String collectionName, FirestoreListCallback callback) {
        firestore.collection(collectionName)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    List<Map<String, Object>> documents = new ArrayList<>();
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        documents.add(document.getData());
                    }
                    callback.onSuccess(documents);
                    Log.d(TAG, "Documentos encontrados: " + documents);
                })
                .addOnFailureListener(e -> {
                    callback.onFailure("Erro ao buscar documentos: " + e.getMessage());
                    Log.e(TAG, "Erro ao buscar documentos", e);
                });
    }

    // Callback para operações simples (sucesso ou falha)
    public interface FirestoreCallback {
        void onSuccess(String message);

        void onFailure(String errorMessage);
    }

    // Callback para retornar um único documento
    public interface FirestoreDocumentCallback {
        void onSuccess(Map<String, Object> data);

        void onFailure(String errorMessage);
    }

    // Callback para retornar uma lista de documentos
    public interface FirestoreListCallback {
        void onSuccess(List<Map<String, Object>> data);

        void onFailure(String errorMessage);
    }
}
