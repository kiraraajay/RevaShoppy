package com.reva.revashoppy.common;

import static androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class PermissionHelper {

    private static final String TAG = "PermissionHelper";
    private static int REQUEST_CODE;

    private Activity activity;
    private Fragment fragment;
    private String[] permissions;
    private PermissionCallback mPermissionCallback;
    private boolean showRational;

    private PermissionHelper(Activity activity, Fragment fragment, String[] permissions, int requestCode) {
        this.activity = activity;
        this.fragment = fragment;
        this.permissions = permissions;
        this.REQUEST_CODE = requestCode;
        checkIfPermissionPresentInAndroidManifest();
    }

    /*@RequiresApi(api = Build.VERSION_CODES.R)
     String[] getPermissionsToRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // For Android 10 (API level 29) and higher, request MANAGE_EXTERNAL_STORAGE permission
            return new String[]{android.Manifest.permission.MANAGE_EXTERNAL_STORAGE};
        } else {
            // For lower Android versions, request WRITE_EXTERNAL_STORAGE permission
            return new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
    }*/

    public PermissionHelper(Activity activity, String[] permissions, int requestCode) {
        this.activity = activity;
        this.permissions = permissions;
        this.REQUEST_CODE = requestCode;
        checkIfPermissionPresentInAndroidManifest();
    }

    public PermissionHelper(Fragment fragment, String[] permissions, int requestCode) {
        this.fragment = fragment;
        this.permissions = permissions;
        this.REQUEST_CODE = requestCode;
        checkIfPermissionPresentInAndroidManifest();
    }

    private void checkIfPermissionPresentInAndroidManifest() {
        for (String permission : permissions) {
            if (hasPermissionInManifest(permission) == false) {
                throw new RuntimeException("Permission (" + permission + ") Not Declared in manifest");
            }
        }
    }

    //=========Constructors- END=========
    public void request(PermissionCallback permissionCallback) {
        this.mPermissionCallback = permissionCallback;
        if (checkSelfPermission(permissions) == false) {
            showRational = shouldShowRational(permissions);
            if (activity != null)
                ActivityCompat.requestPermissions(activity, filterNotGrantedPermission(permissions), REQUEST_CODE);
            else
                fragment.requestPermissions(filterNotGrantedPermission(permissions), REQUEST_CODE);
        } else {
            Log.i(TAG, "PERMISSION: Permission Granted");
            if (mPermissionCallback != null)
                mPermissionCallback.onPermissionGranted(REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            boolean denied = false;
            int i = 0;
            ArrayList<String> grantedPermissions = new ArrayList<String>();
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    denied = true;
                } else {
                    grantedPermissions.add(permissions[i]);
                }
                i++;
            }

            if (denied) {
                boolean currentShowRational = shouldShowRational(permissions);
                if (showRational == false && currentShowRational == false) {
                    Log.d(TAG, "PERMISSION: Permission Denied By System");
                    if (mPermissionCallback != null)
                        mPermissionCallback.onPermissionDeniedBySystem(REQUEST_CODE);
                } else {
                    Log.i(TAG, "PERMISSION: Permission Denied");
                    //Checking if any single individual permission is granted then show user that permission
                    if (!grantedPermissions.isEmpty()) {
                        if (mPermissionCallback != null)
                            mPermissionCallback.onIndividualPermissionGranted(grantedPermissions.toArray(new String[grantedPermissions.size()]), REQUEST_CODE);
                    }
                    if (mPermissionCallback != null) {
                        mPermissionCallback.onPermissionDenied(REQUEST_CODE);
                    }
                }
            } else {
                Log.i(TAG, "PERMISSION: Permission Granted");
                if (mPermissionCallback != null)
                    mPermissionCallback.onPermissionGranted(REQUEST_CODE);
            }


        }
    }

    //====================================
    //====================================

    public interface PermissionCallback {
        public void onPermissionGranted(int requestCode);

        public void onIndividualPermissionGranted(String grantedPermission[], int requestCode);

        public void onPermissionDenied(int requestCode);

        public void onPermissionDeniedBySystem(int requestCode);
    }


    private <T extends Context> T getContext() {
        if (activity != null)
            return (T) activity;
        return (T) fragment.getContext();
    }

    /**
     * Return list that is not granted and we need to ask for permission
     *
     * @param permissions
     * @return
     */
    private String[] filterNotGrantedPermission(String[] permissions) {
        List<String> notGrantedPermission = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                notGrantedPermission.add(permission);
            }
        }
        return notGrantedPermission.toArray(new String[notGrantedPermission.size()]);
    }

    /**
     * Check permission is there or not for group of permissions
     *
     * @param permissions
     * @return
     */
    public boolean checkSelfPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checking if there is need to show rational for group of permissions
     *
     * @param permissions
     * @return
     */
    private boolean shouldShowRational(String[] permissions) {
        boolean currentShowRational = false;
        for (String permission : permissions) {

            if (activity != null) {
                if (shouldShowRequestPermissionRationale(activity, permission) == true) {
                    currentShowRational = true;
                    break;
                }
            } else {
                if (fragment.shouldShowRequestPermissionRationale(permission) == true) {
                    currentShowRational = true;
                    break;
                }
            }
        }
        return currentShowRational;
    }

    //===================
    private boolean hasPermissionInManifest(String permission) {
        try {
            Context context = activity != null ? activity : fragment.getActivity();
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Open current application detail activity so user can change permission manually.
     */
    public void openAppDetailsActivity() {
        if (getContext() == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + getContext().getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        getContext().startActivity(i);
    }
}