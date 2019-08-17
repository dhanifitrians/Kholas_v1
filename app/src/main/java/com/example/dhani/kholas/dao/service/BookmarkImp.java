package com.example.dhani.kholas.dao.service;

import com.example.dhani.kholas.dao.entity.Bookmark;

import java.util.List;

/**
 * Created by Muhammad Natsir on 15,August,2019
 * Jakarta, Indonesia.
 */
public interface BookmarkImp {
    Bookmark createBookmark(Bookmark bookmark);
    Bookmark deleteBookmark(Bookmark bookmark);
    List<Bookmark> findBookmark();
    List<Bookmark> updateBookmark(List<Bookmark> bookmarkList);
}
