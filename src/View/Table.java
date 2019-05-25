package View;

import Utils.StringBetter;

import java.util.List;

public class Table<T> implements ITable{
    private final List<String> linLabl;
    private final List<String> colLabl;
    private final List<List<T>> iT;
    private final StringBuilder builder;

    public Table(List<List<T>> iT, List<String> linLabel, List<String> colLabel) {
        this.linLabl = linLabel;
        this.colLabl = colLabel;
        this.iT = iT;
        this.builder = new StringBuilder();
    }

    private void printSeparatorLine(int[] sizeCols) {
        StringBetter sif = new StringBetter("-");
        for (int j = 0; j <= sizeCols.length - 1; j++)
            this.builder.append("+").append(sif.repeat(sizeCols[j]).toString());
        this.builder.append("+\n");
    }

    @Override
    public String toString() {
        builder.setLength(0);
        int col = this.colLabl.size();
        int lin = this.linLabl.size();
        StringBetter spac = new StringBetter(" ");

        /*find appropriate size for columns*/
        int[] sizeCols = new int[col + 1];
        int labelSize = 0;
        for (String s : this.linLabl) labelSize = (labelSize < s.length() ? s.length() : labelSize);
        sizeCols[0] = labelSize + 2;
        for (int j = 0; j < col; j++) {
            sizeCols[j + 1] = this.colLabl.get(j).length() + 2;
            for (int i = 0; i < lin; i++)
                sizeCols[j + 1] = (sizeCols[j + 1] < this.iT.get(i).get(j).toString().length() + 2) ?
                        this.iT.get(i).get(j).toString().length() + 2 : sizeCols[j + 1];
        }

        /*print label row*/
        this.printSeparatorLine(sizeCols);
        builder.append("|");
        builder.append(spac.repeat(sizeCols[0]));
        for (int j = 0; j < col; j++) {
            builder.append("| ").append(this.colLabl.get(j));
            builder.append(spac.repeat(sizeCols[j + 1] - this.colLabl.get(j).length() - 1));
        }
        builder.append("|\n");
        this.printSeparatorLine(sizeCols);

        /* print contents*/
        for (int i = 0; i < lin; i++) {
            builder.append("| ").append(this.linLabl.get(i));
            builder.append(spac.repeat(sizeCols[0] - this.linLabl.get(i).length() - 1));
            for (int j = 0; j < col; j++) {
                builder.append("| ").append(this.iT.get(i).get(j).toString());
                builder.append(spac.repeat(sizeCols[j + 1] - this.iT.get(i).get(j).toString().length() - 1));
            }
            builder.append("|\n");
            this.printSeparatorLine(sizeCols);
        }

        return builder.toString();
    }
}
