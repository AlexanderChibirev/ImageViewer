package com.example.omega.imageviewer.storage.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.BaseStorage;
import com.example.omega.imageviewer.utils.ImageLoadingUtils;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Alexander Chibirev on 5/4/2018.
 */
public class RealmDatabase extends BaseStorage implements Database {

    private final Realm mRealm;
    private final Context mContext;

    public RealmDatabase(@NonNull final Realm realm, Context context) {
        mRealm = realm;
        mContext = context;
    }

    @Override
    public void requestImages(int limit, int offSet) {
      /*  mRealm.executeTransactionAsync(bgRealm -> {
            mImages = mRealm.where(Image.class).findAll();//TODO add int limit, int offSet
        }, () -> {
            onRequestImagesEvent(RequestEvent.SUCCESS, mImages);
        }, error -> {
            onRequestImagesEvent(RequestEvent.ERROR, null);
        });*/
        mRealm.executeTransaction(realm -> {
            //realm.copyFromRealm(
            RealmResults<Image> imageRealmResults = realm.where(Image.class).findAll();//TODO add int limit, int offSet
            imageRealmResults.addChangeListener(new RealmChangeListener<RealmResults<Image>>() {
                @Override
                public void onChange(@NonNull RealmResults<Image> images) {
                    mImages = realm.copyFromRealm(images);
                    onRequestImagesEvent(RequestEvent.SUCCESS, mImages);
                }
            });
        });
    }

    @Override
    public void deleteImage(@NonNull Image image, final int position) {
        mRealm.executeTransaction(realm -> {
            realm.delete(image.getClass());

            onDeleteImageEvent(RequestEvent.SUCCESS, position);
        });
       /* mRealm.executeTransactionAsync(bgRealm -> {
            mRealm.delete(image.getClass());
        }, () -> {
            onDeleteImageEvent(RequestEvent.SUCCESS, position);
        }, error -> {
            onDeleteImageEvent(RequestEvent.ERROR, position);
        });*/
    }

    @Override
    public void saveImage(@NonNull final Image image) {
        mRealm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(image);//TODO check on isAlready
            ImageLoadingUtils.saveImageOnDisk(mContext, image);
            onSaveImageEvent(RequestEvent.SUCCESS, image, false);
        });
       /* mRealm.executeTransactionAsync(bgRealm -> {
            mRealm.copyToRealmOrUpdate(image);//TODO check on isAlready
        }, () -> {
            mImages.add(image);
            onSaveImageEvent(RequestEvent.SUCCESS, image, false);
        }, error -> {
            onSaveImageEvent(RequestEvent.ERROR, image, true);
        });*/
    }

    @Override
    public void close() {
        mRealm.close();
    }

}
