package com.itp341.singh.himmat.finalprojhimmatsinghitp341;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itp341.singh.himmat.finalprojhimmatsinghitp341.LazyList.LazyAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MessageBoardFragment extends Fragment {

    ListView mListMessages;
    Button addImage;
    Button camera;
    LazyAdapter lazyAdapter;

    public static int REQUEST_CAMERA = 1;
    public static int SELECT_FILE
            = 0;

    private ImageAdapter messageAdapter;

    public MessageBoardFragment() {
        // Required empty public constructor
    }


    public static MessageBoardFragment newInstance() {
        MessageBoardFragment fragment = new MessageBoardFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message_board, container, false);

        mListMessages = (ListView) v.findViewById(R.id.listViewMessages);
        addImage = (Button)v.findViewById(R.id.buttonPost);
        camera = (Button)v.findViewById(R.id.buttonCamera);

        mString.add("https://c1.staticflickr.com/1/364/19363034686_488f05e6b8_b.jpg");
        ArrayList<ImageView> y = Messages.images;
        messageAdapter = new ImageAdapter(getContext(), 0,y);
        lazyAdapter = new LazyAdapter(getActivity(), mString);
        mListMessages.setAdapter(lazyAdapter);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = Utility.checkPermission(getContext());
                if(result){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Camera c = new Camera();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_FILE);

            }

        });
        // Inflate the layout for this fragment
        return v;
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ivImage.setImageBitmap(thumbnail);
    }
    private ArrayList<String> mString= new ArrayList<>();

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ImageView ivImage = null;
       // ivImage.setImageBitmap(bm);
        mString.add("http://www.amtsystemsinc.com/wp-content/uploads/2014/01/USC-Catholic-Slider-2.jpg");
        mListMessages.deferNotifyDataSetChanged();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                if(requestCode == SELECT_FILE){
                    onSelectFromGalleryResult(data);
                }else if(requestCode == REQUEST_CAMERA){
                    onCaptureImageResult(data);
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
//        switch (requestCode) {
//            case 1:
//                if (resultCode == Activity.RESULT_OK) {
//                    ContentValues values = new ContentValues();
//                    values.put(MediaStore.Images.Media.TITLE, "title");
//                    values.put(MediaStore.Images.Media.BUCKET_ID, "test");
//                    values.put(MediaStore.Images.Media.DESCRIPTION, "test Image taken");
//                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//                    Uri uri = getActivity().getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
//                    Bitmap photo = (Bitmap) data.getExtras().get("data");
//                    //((ImageView) getActivity().findViewById(R.id.selectedimage)).setImageBitmap(photo);
//                    OutputStream outstream;
//                    try {
//                        outstream = getActivity().getContentResolver().openOutputStream(uri);
//                        photo.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
//                        outstream.close();
//                    } catch (FileNotFoundException e) {
//                    } catch (IOException e) {
//                    }
//                }
//                break;
//        }
    }
    public boolean hasPermissionInManifest(Context context, String permissionName) {
        final String packageName = context.getPackageName();
        try {
            final PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            final String[] declaredPermisisons = packageInfo.requestedPermissions;
            if (declaredPermisisons != null && declaredPermisisons.length > 0) {
                for (String p : declaredPermisisons) {
                    if (p.equals(permissionName)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return false;
    }
    private class ImageAdapter extends ArrayAdapter<ClipData.Item>{
        private ClipData.Item[] items;

        public ImageAdapter(Context c, int resId, ArrayList<ImageView>objects){

            super(c, 0);
            //ye= objects;

        }
        public View getView(int position, View convertView, ViewGroup parent) {
            //check if a row has already been instantiated
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.message_board_card, null
                );
            }

            ImageView image = (ImageView) convertView.findViewById(R.id.imageTemple);
            TextView text = (TextView)convertView.findViewById(R.id.templeName);

            int resID = getContext().getResources().getIdentifier("sikhgw", "drawable",
                    getContext().getPackageName());
            image.setImageResource(resID);
            text.setText("San Jose Sikh Gurdwara");
            return convertView;
        }
    }
//    private class ImageAdapter extends ArrayAdapter<ImageView>{
//        ArrayList<ImageView> ye;
//
//        public ImageAdapter(Context c, int resId, ArrayList<ImageView>objects){
//
//            super(c, 0);
//            ye= objects;
//
//        }
//        public View getView(int position, View convertView, ViewGroup parent) {
//            //check if a row has already been instantiated
//            if (convertView == null) {
//                convertView = getActivity().getLayoutInflater().inflate(
//                        R.layout.message_board_card, null
//                );
//            }
//
//            ImageView image = (ImageView) convertView.findViewById(R.id.imageTemple);
//            TextView text = (TextView)convertView.findViewById(R.id.templeName);
//
//            int resID = getContext().getResources().getIdentifier("sikhgw", "drawable",
//                    getContext().getPackageName());
//            image.setImageResource(resID);
//            text.setText("San Jose Sikh Gurdwara");
//            return convertView;
//        }
//    }

}
