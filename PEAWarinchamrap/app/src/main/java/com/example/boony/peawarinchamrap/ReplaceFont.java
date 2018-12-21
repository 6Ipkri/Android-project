package com.example.boony.peawarinchamrap;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class ReplaceFont {
    public static void replaceDefultFont(Context context,
                                         String nameOfFontBeingReplace,
                                         String nameOfFontInAsset){
        Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(),nameOfFontInAsset);
        replaceFont(nameOfFontBeingReplace,customFontTypeface);
    }
    private static void replaceFont(String nameOfFontBeingReplace , Typeface cusromFontTypeface){
        try
        {
            Field myfield = Typeface.class.getDeclaredField(nameOfFontBeingReplace);
            myfield.setAccessible(true);
            myfield.set(null,cusromFontTypeface);
        }
        catch (NoSuchFieldException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
