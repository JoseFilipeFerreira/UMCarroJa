package View;

import Utils.StringBetter;
import Utils.Terminal;

import java.util.ArrayList;

public class Navigator<T> implements INavigator{
    private ArrayList<T> strings;
    private final StringBuilder builder;
    private int pageSize;
    private int nCols;
    private int nPages;
    private int page;
    private int separator;
    private int maxPrint;
    private Terminal term;

    public boolean isEmpty(){
        return this.strings.isEmpty();
    }

    public Navigator() {
        this.builder = new StringBuilder();
        this.strings = new ArrayList<>();
    }

    public Navigator(ArrayList<T> strings, int pageSize) {
        this.builder = new StringBuilder();
        this.strings = strings;
        this.separator = 7;
        this.term = new Terminal();
        this.maxPrint = 0;
        strings.forEach(string -> this.maxPrint = (string.toString().length() < this.maxPrint) ?
                this.maxPrint : string.toString().length());
        this.pageSize = pageSize;
        this.update();
        this.page = 0;
    }

    public void next() {
        this.page = (page + 1 <= nPages)?page + 1 : page;
    }

    public void previous() {
        this.page = (page - 1 >= 0)?page -1 : page;
    }

    private void update(){
        this.nCols = 1;
        while (true) {
            if (((this.nCols + 1) * (String.valueOf(strings.size() + 1).length() + this.maxPrint + this.separator + 1) < this.term.getColumns()))
                this.nCols++;
            else
                break;
        }
        this.nPages = (strings.size() / (pageSize * nCols))
                - ((strings.size() % (pageSize * nCols) != 0) ? 0 : 1);
        if(this.page > this.nPages)
            this.page = this.nPages;
    }

    @Override
    public String toString(){
        term.update();
        this.update();
        StringBetter spac = new StringBetter(" ");
        StringBetter senter = new StringBetter("\n");
        int pos, r = 0;
        builder.setLength(0);
        for(int i = this.pageSize * this.page; i < this.pageSize * (this.page + 1); i++){
            for(int j = 0; j < this.nCols && j + i * this.nCols < this.strings.size(); j++){
                pos = j + i * this.nCols;
                builder.append(pos + 1).append(".");
                builder.append(spac.repeate(
                        String.valueOf(this.strings.size()).length() - String.valueOf(pos + 1).length() + 1
                        ).toString());
                builder.append(this.strings.get(pos)).append(spac.repeate(this.separator));
                builder.append(spac.repeate(this.maxPrint - this.strings.get(pos).toString().length()));

            }
            r++;
            builder.append("\n");
        }
        builder.append(senter.repeate(this.pageSize - r  + 2));
        builder.append("\t\t").append(this.page + 1).append("/").append(this.nPages+1);
        return builder.toString();
    }
}
