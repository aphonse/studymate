package com.example.studymate.GUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studymate.DbConnectors.DbConn;
import com.example.studymate.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class UploadQuestions extends Fragment implements View.OnClickListener{
    ImageView iv;
    EditText question,topic;
    Button attach,upload;
    int GALLERY=0;
    int CAMERA=1;
    byte[] img;
    DbConn dbConn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.upload, container, false);
       dbConn=new DbConn(getActivity());
        question=rootView.findViewById(R.id.questionask);
        topic=rootView.findViewById(R.id.Topic);
        iv=rootView.findViewById(R.id.image);
        attach=rootView.findViewById(R.id.attachfile);
        attach.setOnClickListener(this);
        upload=rootView.findViewById(R.id.upload);
        upload.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.attachfile:
                showPictureDialog();
                break;
            case R.id.upload:
                String query=question.getText().toString();
                if (question.getText().toString().trim().length()>0){
                    if(topic.getText().toString().trim().length()>0){
                        String topics=topic.getText().toString();
                        boolean inserted=dbConn.AskingQuestions(query,topics,img);
                        if(inserted){
                            Toast.makeText(getActivity(),"UPLOADED",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getActivity(),"FAILED TO BE UPLOADED",Toast.LENGTH_LONG).show();

                        }

                    }
                    else{
                        Toast.makeText(getActivity(),"enter a topic to proceed",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getActivity(),"Enter a Question to upload!",Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }else if (resultCode==getActivity().RESULT_OK){
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    img=getBitmapAsByteArray(bitmap);
                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    iv.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            assert thumbnail != null;
            img=getBitmapAsByteArray(thumbnail);

            iv.setImageBitmap(thumbnail);
            Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }}}
            public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}