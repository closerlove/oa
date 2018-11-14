package com.qf.oa.entity;

import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/7 17:55
 * @Version 1.0
 */
public class ResultInfo {

    private List<Info> suggestions;

    public List<Info> getSuggestions() {
        return suggestions;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "suggestion=" + suggestions +
                '}';
    }

    public void setSuggestions(List<Info> suggestions) {
        this.suggestions = suggestions;
    }

    public static class Info{
        private String value;

        private String data;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "value='" + value + '\'' +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
}
