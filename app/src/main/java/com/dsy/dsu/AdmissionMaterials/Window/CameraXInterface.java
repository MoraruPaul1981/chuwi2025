package com.dsy.dsu.AdmissionMaterials.Window;

import android.graphics.Bitmap;

import org.jetbrains.annotations.NotNull;

public interface CameraXInterface {
    Bitmap onGetFinishEditDialogNewPhotos( ) ;

    void onSEtFinishEditDialogNewPhotos(@NotNull Bitmap bitmap) ;
}
