package com.wdretzer.nasaprojetointegrador.bancodados;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wdretzer.nasaprojetointegrador.data.ItensData;
import com.wdretzer.nasaprojetointegrador.data.Links;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NasaDao_Impl implements NasaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NasaEntity> __insertionAdapterOfNasaEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<NasaEntity> __deletionAdapterOfNasaEntity;

  private final EntityDeletionOrUpdateAdapter<NasaEntity> __updateAdapterOfNasaEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByApiId;

  public NasaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNasaEntity = new EntityInsertionAdapter<NasaEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `nasaBD` (`id`,`href`,`data`,`links`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NasaEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getHref() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHref());
        }
        final String _tmp = __converters.listToJsonString(value.getData());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        final String _tmp_1 = __converters.listToJsonString2(value.getLinks());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfNasaEntity = new EntityDeletionOrUpdateAdapter<NasaEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `nasaBD` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NasaEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfNasaEntity = new EntityDeletionOrUpdateAdapter<NasaEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `nasaBD` SET `id` = ?,`href` = ?,`data` = ?,`links` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NasaEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getHref() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHref());
        }
        final String _tmp = __converters.listToJsonString(value.getData());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        final String _tmp_1 = __converters.listToJsonString2(value.getLinks());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteByApiId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM nasaBD WHERE data = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final NasaEntity... nasaEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNasaEntity.insert(nasaEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<NasaEntity> nasaEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNasaEntity.insert(nasaEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final NasaEntity... nasaEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNasaEntity.handleMultiple(nasaEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final NasaEntity... nasaEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNasaEntity.handleMultiple(nasaEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByApiId(final List<ItensData> apiData) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByApiId.acquire();
    int _argIndex = 1;
    final String _tmp = __converters.listToJsonString(apiData);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByApiId.release(_stmt);
    }
  }

  @Override
  public List<NasaEntity> listAll() {
    final String _sql = "SELECT * FROM nasaBD ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHref = CursorUtil.getColumnIndexOrThrow(_cursor, "href");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfLinks = CursorUtil.getColumnIndexOrThrow(_cursor, "links");
      final List<NasaEntity> _result = new ArrayList<NasaEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NasaEntity _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpHref;
        if (_cursor.isNull(_cursorIndexOfHref)) {
          _tmpHref = null;
        } else {
          _tmpHref = _cursor.getString(_cursorIndexOfHref);
        }
        final List<ItensData> _tmpData;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfData)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfData);
        }
        _tmpData = __converters.jsonStringToList(_tmp);
        final List<Links> _tmpLinks;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfLinks)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfLinks);
        }
        _tmpLinks = __converters.jsonStringToList2(_tmp_1);
        _item = new NasaEntity(_tmpId,_tmpHref,_tmpData,_tmpLinks);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public NasaEntity retrieveById(final int id) {
    final String _sql = "SELECT * FROM nasaBD WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHref = CursorUtil.getColumnIndexOrThrow(_cursor, "href");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfLinks = CursorUtil.getColumnIndexOrThrow(_cursor, "links");
      final NasaEntity _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpHref;
        if (_cursor.isNull(_cursorIndexOfHref)) {
          _tmpHref = null;
        } else {
          _tmpHref = _cursor.getString(_cursorIndexOfHref);
        }
        final List<ItensData> _tmpData;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfData)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfData);
        }
        _tmpData = __converters.jsonStringToList(_tmp);
        final List<Links> _tmpLinks;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfLinks)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfLinks);
        }
        _tmpLinks = __converters.jsonStringToList2(_tmp_1);
        _result = new NasaEntity(_tmpId,_tmpHref,_tmpData,_tmpLinks);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public NasaEntity retrieveByApiId(final List<ItensData> apiData) {
    final String _sql = "SELECT * FROM nasaBD WHERE data = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.listToJsonString(apiData);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHref = CursorUtil.getColumnIndexOrThrow(_cursor, "href");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfLinks = CursorUtil.getColumnIndexOrThrow(_cursor, "links");
      final NasaEntity _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpHref;
        if (_cursor.isNull(_cursorIndexOfHref)) {
          _tmpHref = null;
        } else {
          _tmpHref = _cursor.getString(_cursorIndexOfHref);
        }
        final List<ItensData> _tmpData;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfData)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfData);
        }
        _tmpData = __converters.jsonStringToList(_tmp_1);
        final List<Links> _tmpLinks;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfLinks)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfLinks);
        }
        _tmpLinks = __converters.jsonStringToList2(_tmp_2);
        _result = new NasaEntity(_tmpId,_tmpHref,_tmpData,_tmpLinks);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int countApiId(final List<ItensData> apiData) {
    final String _sql = "SELECT COUNT(data) FROM nasaBD WHERE data = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.listToJsonString(apiData);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
