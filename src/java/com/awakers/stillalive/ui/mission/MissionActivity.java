package com.awakers.stillalive.ui.mission;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Mission;
import com.awakers.stillalive.ui.main.MainActivity;
import com.awakers.stillalive.ui.main.home.HomeViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import es.dmoral.toasty.Toasty;
import java.io.File;

public class MissionActivity extends BaseActivity implements OnClickListener {
    private static final int CROP_FROM_IMAGE = 3;
    private static final int PICK_FROM_ALBUM = 2;
    private static final int PICK_FROM_CAMERA = 1;
    private String filePath;
    Button mAddBtn;
    private FirebaseAuth mAuth;
    EditText mContentEt;
    private DatabaseReference mDatabaseReference;
    ImageButton mImageBtn;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    TextView textView;

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mission_view);
        StrictMode.setVmPolicy(new Builder().build());
        this.mAuth = FirebaseAuth.getInstance();
        this.mStorageRef = FirebaseStorage.getInstance().getReference();
        this.mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        this.mImageBtn = (ImageButton) findViewById(R.id.submit_img);
        this.mContentEt = (EditText) findViewById(R.id.content);
        this.mAddBtn = (Button) findViewById(R.id.mission_add_btn);
        this.textView = (TextView) findViewById(R.id.text_view_mission);
        this.mImageBtn.setOnClickListener(this);
        this.mAddBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.mAddBtn) {
            uploadMission(this.mContentEt.getText().toString(), this.mImageUri);
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(17432576, 17432577);
            finish();
            overridePendingTransition(17432576, 17432577);
        } else if (view == this.mImageBtn) {
            if (VERSION.SDK_INT >= 23) {
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
            }
            MissionActivity$$Lambda$0 missionActivity$$Lambda$0 = new MissionActivity$$Lambda$0(this);
            MissionActivity$$Lambda$1 missionActivity$$Lambda$1 = new MissionActivity$$Lambda$1(this);
            new AlertDialog.Builder(this).setTitle("업로드할 방식 선택").setPositiveButton("사진 촬영", missionActivity$$Lambda$0).setNeutralButton("앨범에서 가져오기", missionActivity$$Lambda$1).setNegativeButton("취소", MissionActivity$$Lambda$2.$instance).show();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onClick$0$MissionActivity(DialogInterface dialogInterface, int i) {
        openCamera();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onClick$1$MissionActivity(DialogInterface dialogInterface, int i) {
        openAlbum();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (i == 2 || i == 1) {
                if (i == 2) {
                    this.mImageUri = intent.getData();
                    Log.d("mission", this.mImageUri.getPath().toString());
                }
                Intent intent2 = new Intent("com.android.camera.action.CROP");
                intent2.setDataAndType(this.mImageUri, "image/*");
                intent2.putExtra("outputX", 300);
                intent2.putExtra("outputY", 300);
                intent2.putExtra("aspectX", 1);
                intent2.putExtra("aspectY", 1);
                intent2.putExtra("scale", true);
                intent2.putExtra("return-data", true);
                startActivityForResult(intent2, 3);
            } else if (i == 3) {
                this.mImageBtn.setBackground(getResources().getDrawable(R.drawable.closedbox));
                this.textView.setText("미션 수행 사진이 업로드 되었습니다.");
                File file = new File(this.mImageUri.getPath());
            }
        }
    }

    public void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tmp_");
        stringBuilder.append(String.valueOf(System.currentTimeMillis()));
        stringBuilder.append(".jpg");
        this.mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), stringBuilder.toString()));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("openCamera: ");
        stringBuilder2.append(this.mImageUri);
        Log.d("mission", stringBuilder2.toString());
        intent.putExtra("output", this.mImageUri);
        startActivityForResult(intent, 1);
        overridePendingTransition(17432576, 17432577);
    }

    public void openAlbum() {
        startActivityForResult(new Intent("android.intent.action.PICK").setType("vnd.android.cursor.dir/image"), 2);
        overridePendingTransition(17432576, 17432577);
    }

    public void uploadMission(final String str, Uri uri) {
        StorageReference storageReference = this.mStorageRef;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User/");
        stringBuilder.append(this.mAuth.getCurrentUser().getUid());
        stringBuilder.append("/");
        stringBuilder.append(UserCapsule.get().getUserCapsule().size() - 1);
        stringBuilder.append("/");
        stringBuilder.append(app().getCurrentDate());
        stringBuilder.append(".jpg");
        storageReference = storageReference.child(stringBuilder.toString());
        if (str.trim().equals("")) {
            Toasty.warning(this, "생존일지 내용을 입력해주세요 !", 0).show();
        } else if (uri == null) {
            Toasty.warning(this, "사진도 함께 올려주세요 !", 0).show();
        } else {
            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
                public void onSuccess(TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Mission.get().setText(str);
                    Mission.get().setUrl(downloadUrl.toString());
                    MissionActivity.this.mDatabaseReference.child("User").child(MissionActivity.this.mAuth.getCurrentUser().getUid()).child("userCapsule").child("userCapsule").child(String.valueOf(UserCapsule.get().getUserCapsule().size() - 1)).child("mission").child(String.valueOf(MissionActivity.this.app().getCurrentDate())).setValue(Mission.get());
                    HomeViewModel.clickable.set(false);
                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(@NonNull Exception exception) {
                }
            });
        }
    }
}
