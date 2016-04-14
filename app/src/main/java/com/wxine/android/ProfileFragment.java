package com.wxine.android;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import permissions.dispatcher.DeniedPermissions;
import permissions.dispatcher.NeedsPermissions;
import permissions.dispatcher.RuntimePermissions;
import permissions.dispatcher.ShowsRationales;

@RuntimePermissions
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private MyApplication app;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final ImageLoader imageLoader = ImageLoader.getInstance();
    static final int FROM_ALBUMS = 1;
    private ImageView iv_code_file;
    private Button btn_profile_submit;
    private OnFragmentInteractionListener mListener;

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication)getActivity().getApplication();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        iv_code_file = (ImageView)view.findViewById(R.id.iv_user_codefile);
        btn_profile_submit = (Button)view.findViewById(R.id.btn_profile_submit);

        iv_code_file.setOnClickListener(this);

        btn_profile_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    @NeedsPermissions({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showAlbums() {
        Log.v("==showAlbums==", "showAlbums");
        Intent intent = new Intent(this.getContext(), AlbumsActivity.class);
        startActivityForResult(intent, FROM_ALBUMS);
    }

    /*static final int PERMISSIONS_CODE = 200;
    private void requestPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        requestPermissions(perms, PERMISSIONS_CODE);
    }*/

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        ProfileFragmentPermissionsDispatcher.showAlbumsWithCheck(this);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("=====", requestCode + "===" + resultCode);
        if (requestCode == FROM_ALBUMS /*&& resultCode == 1*/) {
            Log.v("ProfileFragment:", requestCode+","+resultCode);
            if (data != null) {
                Uri uri = data.getData();
                imageLoader.displayImage(uri.toString(), iv_code_file);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ProfileFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @ShowsRationales({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationaleForCamera() {
        Snackbar.make(this.getView(), "OK", Snackbar.LENGTH_LONG).show();
    }

    @DeniedPermissions({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void deniedActionForCamera() {
        Snackbar.make(this.getView(), "要完成该操作，需要允许该权限请求。", Snackbar.LENGTH_LONG).show();
    }

    /*@Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        Log.v("Permissions:", String.valueOf(permsRequestCode)+":"+permissions+":"+grantResults[0]+":"+grantResults[1]);
        switch(permsRequestCode){
            case PERMISSIONS_CODE:
                boolean writeStorageAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[1]== PackageManager.PERMISSION_GRANTED;
                if (writeStorageAccepted) {
                    Intent intent = new Intent(this.getContext(), AlbumsActivity.class);
                    startActivityForResult(intent, FROM_ALBUMS);
                } else {
                    Snackbar.make(this.getView(), "要完成该操作，需要允许该权限请求。", Snackbar.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(permsRequestCode, permissions, grantResults);
        }
    }*/
}
