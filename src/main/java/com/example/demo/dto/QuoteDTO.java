package com.example.demo.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;

@Data
public class QuoteDTO {
    private String type;
    private String value;

    //
    private List<String> values;

    /*
    TODO:
    This is DTO, It should be located on the DTO folder instead of the model to avoid confusion.
    It is better to prefix it with DTO name also to be more clear
It is better to use always list or queue or stack instead of array.
this can be replace with stream

     */
    public boolean isMatch(List<String> tags) {
//        boolean isThere = false;
//        for (String value : values) {
//            for (String tag : tags) {
//                if (tag.equals(value)) {
//                    isThere = true;
//                    break;
//                }
//            }
//            if (!isThere)
//                return false;
//            else
//                isThere = false;
//        }
//        return true;

        return new HashSet<>(tags).containsAll(this.values);
    }
}
