package com.awakers.stillalive.utill.api;

import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.ThemeDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;
import java8.util.function.Consumer;

public class FirebaseApi {
    public void set(String str, Object obj) {
        FirebaseDatabase.getInstance().getReference("User").child(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid()).child(str).setValue(obj);
    }

    public void get(String str, final Class<? extends BaseRepository> cls, final Consumer<BaseRepository> consumer) {
        FirebaseDatabase.getInstance().getReference("User").child(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid()).child(str).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                consumer.accept(dataSnapshot.getValue(cls));
            }
        });
    }

    public void getOnce(String str, final Class<? extends BaseRepository> cls, final Consumer<BaseRepository> consumer) {
        FirebaseDatabase.getInstance().getReference("User").child((String) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getUid())).child(str).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                consumer.accept(dataSnapshot.getValue(cls));
            }
        });
    }

    public void loadTheme(String str, final Consumer<BaseRepository> consumer) {
        FirebaseDatabase.getInstance().getReference("Theme").child(str).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                consumer.accept(dataSnapshot.getValue(ThemeDB.class));
            }
        });
    }
}
