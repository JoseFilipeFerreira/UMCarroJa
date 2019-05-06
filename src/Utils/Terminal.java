package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal implements ITerminal{
    private int columns;
    private int lines;

    public Terminal() {
        this.update();
    }

    public void update() {
        StringBuilder rColumns = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(new String[] {
                    "bash", "-c", "tput cols 2> /dev/tty" });
            readResult(rColumns, p);

        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            this.columns = Integer.parseInt(rColumns.toString());
        }
        catch (NumberFormatException e)
        {
            this.columns = 130;
        }

        StringBuilder rLines = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(new String[] {
                    "bash", "-c", "tput lines 2> /dev/tty" });
            readResult(rLines, p);

        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            this.lines = Integer.parseInt(rLines.toString());
        }
        catch (NumberFormatException e)
        {
            this.lines = 40;
        }

    }

    private void readResult(StringBuilder rColumns, Process p) throws IOException {
        BufferedReader in =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            rColumns.append(inputLine);
        }
        in.close();
    }

    public int getColumns() {
        return columns;
    }

    public int getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.columns)
                .append("x")
                .append(this.lines)
                .toString();
    }
}
