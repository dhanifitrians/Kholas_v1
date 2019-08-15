package com.example.dhani.kholas.dao.service;

import com.example.dhani.kholas.base.BaseApplication;
import com.example.dhani.kholas.base.ObjectBox;
import com.example.dhani.kholas.dao.entity.Bookmark;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Muhammad Natsir on 15,August,2019
 * Jakarta, Indonesia.
 */
public class BookmarkService implements BookmarkImp {
    @Override
    public Bookmark createBookmark(Bookmark bookmark) {
        Box<Bookmark> bookmarkBox = ObjectBox.get().boxFor(Bookmark.class);
        bookmarkBox.put(bookmark);
        return bookmark;
    }

    @Override
    public Bookmark findBookmarkByPage(int page) {
        return null;
    }

    @Override
    public List<Bookmark> findBookmark() {
        return null;
    }
}
