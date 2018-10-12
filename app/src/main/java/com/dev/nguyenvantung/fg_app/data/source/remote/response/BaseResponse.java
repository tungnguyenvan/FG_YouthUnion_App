package com.dev.nguyenvantung.fg_app.data.source.remote.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    private class Links {
        @SerializedName("first")
        @Expose
        private String first;
        @SerializedName("last")
        @Expose
        private String last;
        @SerializedName("prev")
        @Expose
        private Object prev;
        @SerializedName("next")
        @Expose
        private String next;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        public Object getPrev() {
            return prev;
        }

        public String getNext() {
            return next;
        }
    }

    private class Meta {
        @SerializedName("current_page")
        @Expose
        private Integer currentPage;
        @SerializedName("from")
        @Expose
        private Integer from;
        @SerializedName("last_page")
        @Expose
        private Integer lastPage;
        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("to")
        @Expose
        private Integer to;
        @SerializedName("total")
        @Expose
        private Integer total;

        public Integer getCurrentPage() {
            return currentPage;
        }

        public Integer getFrom() {
            return from;
        }

        public Integer getLastPage() {
            return lastPage;
        }

        public String getPath() {
            return path;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public Integer getTo() {
            return to;
        }

        public Integer getTotal() {
            return total;
        }
    }
}
