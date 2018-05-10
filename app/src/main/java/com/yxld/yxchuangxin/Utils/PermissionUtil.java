package com.yxld.yxchuangxin.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/7/4
 * @descprition:
 */

public class PermissionUtil {
    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void permission(Context context, int codeRequest, PermissionListener listeners, String... permissions){
        AndPermission.with(context)
                .requestCode(codeRequest)
                .permission(permissions)
                .callback(listeners)
                .start();
    }
}
