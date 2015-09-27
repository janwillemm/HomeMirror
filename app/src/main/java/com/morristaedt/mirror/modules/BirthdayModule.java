package com.morristaedt.mirror.modules;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by HannahMitt on 8/23/15.
 */
public class BirthdayModule {


    private static Cursor getContactsBirthdays(Context context) {
        Uri uri = ContactsContract.Data.CONTENT_URI;

        String[] projection = new String[] {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Event.CONTACT_ID,
                ContactsContract.CommonDataKinds.Event.START_DATE
        };

        String where =
                ContactsContract.Data.MIMETYPE + "= ? AND " +
                        ContactsContract.CommonDataKinds.Event.TYPE + "=" +
                        ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;
        String[] selectionArgs = new String[] {
                ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE
        };
        String sortOrder = null;
        return context.getContentResolver().query(uri, projection, where, selectionArgs, sortOrder);
    }

    public static List<String> getBirthday(Context context) {
        Cursor cursor = getContactsBirthdays(context);
        int bDayColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE);
        Map<String, List<String>> bdayNames = new HashMap<>();
        while (cursor.moveToNext()) {
            String bDay = cursor.getString(bDayColumn);
            String [] bdayParts =  bDay.split("-");
            String contactName = cursor.getString(0);
            String day = bdayParts[1]+"/"+bdayParts[2];
            List<String> contactWithThisBday = bdayNames.get(day);
            if(contactWithThisBday == null)   {
                contactWithThisBday = new ArrayList<>();
                bdayNames.put(day, contactWithThisBday);
            }
            contactWithThisBday.add(contactName);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/d", Locale.US);
        List<String> contactsWhoHaveABirthday = bdayNames.get(simpleDateFormat.format(new Date()));
        if(contactsWhoHaveABirthday == null)
        {
            return new ArrayList<>();
        }
        return contactsWhoHaveABirthday;
    }

}
